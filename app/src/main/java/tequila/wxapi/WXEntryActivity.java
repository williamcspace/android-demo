package tequila.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.rest.services.WechatApi;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.weixin.view.WXCallbackActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.Timer;
import java.util.TimerTask;

public class WXEntryActivity extends WXCallbackActivity {

    public static final String TAG = "WXEntryActivity";

    private WechatApi mWechatApi;

    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    private Button gotoBtn, regBtn, launchBtn, checkBtn;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    //TODO: 测试refreshToken
    private String refreshToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Config.WX_APP_ID, false);

        regBtn = (Button) findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 将该app注册到微信
                api.registerApp(Config.WX_APP_ID);
            }
        });

        launchBtn = (Button) findViewById(R.id.launch_wx_btn);
        launchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(WXEntryActivity.this, "launch result = " + api.openWXApp(), Toast.LENGTH_LONG).show();
            }
        });

        checkBtn = (Button) findViewById(R.id.check_timeline_supported_btn);
        checkBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int wxSdkVersion = api.getWXAppSupportAPI();
                if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
                    Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline supported", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline not supported", Toast.LENGTH_LONG).show();
                }
            }
        });

        api.handleIntent(getIntent(), this);

        mWechatApi = RestServiceProvider.getWechatApi();
    }
//
//    @Override
//    protected IWXAPI getWXApi() {
//        return this.mWxHandler != null ? this.mWxHandler.getWXApi() : WXAPIFactory.createWXAPI(this, Config.WX_APP_ID, false);
//    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        LogUtils.d(TAG, "onReq");
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            default:
                break;
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                getWXAccessToken(((SendAuth.Resp) resp).code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }

        //微信支付回调
        LogUtils.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_tip);
            builder.setMessage(getString(R.string.pay_result_callback_msg, resp.errStr + ";code=" + String.valueOf(resp.errCode)));
            builder.show();
        }
    }

    //TODO: 已成功获取到 access token, 但应该放上服务器做中转请求
    private void getWXAccessToken(String code) {
//        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//        Toast.makeText(WXEntryActivity.this, "onResp result = " + code, Toast.LENGTH_LONG).show();

        mWechatApi.requestWXAccessToken(Config.WX_APP_ID, Config.WX_APP_SECRET, code,
                new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject response, Response arg1) {
                        Toast.makeText(WXEntryActivity.this, "Response is: " + response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            refreshToken = response.get("refresh_token").toString();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }

                        //TODO: 修改转跳逻辑
                        Timer time = new Timer();
                        time.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                WXEntryActivity.this.finish();
                            }
                        }, 1000);
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Toast.makeText(WXEntryActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
                    }
                });
    }

    //TODO: 已成功refresh token, 但应该放上服务器做中转请求
    public void refreshWXAccessToken(View view) {
//        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//        Toast.makeText(WXEntryActivity.this, "onResp result = " + code, Toast.LENGTH_LONG).show();

        mWechatApi.refreshWXAccessToken(Config.WX_APP_ID, refreshToken,
                new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject response, Response arg1) {
                        Toast.makeText(WXEntryActivity.this, "Response is: " + response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            refreshToken = response.get("refresh_token").toString();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Toast.makeText(WXEntryActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void goToGetMsg() {
        Intent intent = new Intent(this, GetFromWXActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
        finish();
    }

    private void goToShowMsg(ShowMessageFromWX.Req showReq) {
        WXMediaMessage wxMsg = showReq.message;
        WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;

        StringBuffer msg = new StringBuffer(); // 组织一个待显示的消息内容
        msg.append("description: ");
        msg.append(wxMsg.description);
        msg.append("\n");
        msg.append("extInfo: ");
        msg.append(obj.extInfo);
        msg.append("\n");
        msg.append("filePath: ");
        msg.append(obj.filePath);

        Intent intent = new Intent(this, ShowFromWXActivity.class);
        intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
        intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
        intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}