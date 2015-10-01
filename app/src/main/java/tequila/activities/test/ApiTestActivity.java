package tequila.activities.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.BaseActivity;
import com.tangxinli.android.tequila.events.LoginFinish;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiTestActivity extends BaseActivity {
    private static final String TAG = ApiTestActivity.class.getSimpleName();

    private UMSocialService mController;

    public void setQQShare() {
        //参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, Config.QQ_APP_ID, Config.QQ_APP_KEY);
        qqSsoHandler.addToSocialSDK();

        QQShareContent qqShareContent = new QQShareContent();
        //设置分享文字
        qqShareContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QQ");
        //设置分享title
        qqShareContent.setTitle("hello, title");
        //设置分享图片
        qqShareContent.setShareImage(new UMImage(this, R.drawable.ic_one));
        //设置点击分享内容的跳转链接
        qqShareContent.setTargetUrl("http://www.tangxinli.com");
        mController.setShareMedia(qqShareContent);
    }

    public void setQZoneShare(){
        //参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, Config.QQ_APP_ID, Config.QQ_APP_KEY);
        qZoneSsoHandler.addToSocialSDK();

        QZoneShareContent qzone = new QZoneShareContent();
        //设置分享文字
        qzone.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QZone");
        //设置点击消息的跳转URL
        qzone.setTargetUrl("http://www.tangxinli.com");
        //设置分享内容的标题
        qzone.setTitle("QZone title");
        //设置分享图片
        qzone.setShareImage(new UMImage(this, R.drawable.ic_one));
        mController.setShareMedia(qzone);
    }

    public void setWechatShare() {
// 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(this, Config.WX_APP_ID, Config.WX_APP_SECRET);
        wxHandler.addToSocialSDK();

        //设置微信好友分享内容
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        //设置分享文字
        weixinContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，微信");
        //设置title
        weixinContent.setTitle("友盟社会化分享组件-微信");
        //设置分享内容跳转URL
        weixinContent.setTargetUrl("http://www.tangxinli.com");
        //设置分享图片
        weixinContent.setShareImage(new UMImage(this, R.drawable.ic_one));
        mController.setShareMedia(weixinContent);
    }

    public void setWechatCircleShare(){
        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(this, Config.WX_APP_ID, Config.WX_APP_SECRET);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();

        //设置微信朋友圈分享内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，朋友圈");
//设置朋友圈title
        circleMedia.setTitle("友盟社会化分享组件-朋友圈");
        circleMedia.setShareImage(new UMImage(this, R.drawable.ic_one));
        circleMedia.setTargetUrl("你的URL链接");
        mController.setShareMedia(circleMedia);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);
        LogUtils.d(TAG, "ApiTestActivity onCeate");

        // 首先在您的Activity中添加如下成员变量
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
        mController.setShareMedia(new UMImage(this, "http://www.umeng.com/images/pic/banner_module_social.png"));
        mController.getConfig().removePlatform(SHARE_MEDIA.TENCENT);

        //分享回调
        mController.registerListener(new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int stCode, SocializeEntity entity) {
                if (stCode == 200) {
                    Toast.makeText(ApiTestActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ApiTestActivity.this, "分享失败 : error code : " + stCode, Toast.LENGTH_SHORT).show();
                }
            }
        });

        setQQShare();
        setQZoneShare();
        setWechatShare();
        setWechatCircleShare();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG, "ApiTestActivity onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "ApiTestActivity onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "ApiTestActivity onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d(TAG, "ApiTestActivity onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d(TAG, "ApiTestActivity OnRestart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "ApiTestActivity onDestory");
    }

    public void testAuth(View v) {
        JsonObject jsonBody = new JsonObject();
        try {
            jsonBody.addProperty("key", "value");
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

        RestServiceProvider.getTangxlApi().auth(jsonBody, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject responseBody, Response response) {
                Toast.makeText(ApiTestActivity.this, "Response is: " + responseBody.toString(), Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, responseBody.toString());
                LogUtils.d(TAG, response.getHeaders().toString());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(ApiTestActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, retrofitError.toString());
            }
        });
    }

    public void testLogin(View v) {
        JsonObject jsonBody = new JsonObject();
        try {
            jsonBody.addProperty("username", "alvan");
            jsonBody.addProperty("password", "123456");
            jsonBody.addProperty("openid", "alvan");
            jsonBody.addProperty("access_token", "123456");
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

//        final User user = new User(this);
//        user.login(jsonBody, new Callback<JsonObject>() {
//            @Override
//            public void success(JsonObject responseBody, Response response) {
//                Toast.makeText(ApiTestActivity.this, "Response is: " + responseBody.toString(), Toast.LENGTH_LONG).show();
//                LogUtils.d(TAG, responseBody.toString());
//                LogUtils.d(TAG, response.getHeaders().toString());
//
//                if (responseBody.has("error")) {
//                    return;
//                }
//
//                if (responseBody.has("user")) {
//                    user.setUser(responseBody.getAsJsonObject("user"));
//                    LogUtils.d(TAG, user.getUser().toString());
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError retrofitError) {
//                Toast.makeText(ApiTestActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
//                LogUtils.d(TAG, retrofitError.toString());
//            }
//        });
    }

    public void testUsers(View v) {
        JsonObject jsonBody = new JsonObject();
        try {
            jsonBody.addProperty("username", "alvan");
            jsonBody.addProperty("password", "123456");
            jsonBody.addProperty("openid", "alvan");
            jsonBody.addProperty("access_token", "123456");
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

        RestServiceProvider.getTangxlApi().getUsers(jsonBody, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject responseBody, Response response) {
                Toast.makeText(ApiTestActivity.this, "Response is: " + responseBody.toString(), Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, responseBody.toString());
                LogUtils.d(TAG, response.getHeaders().toString());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(ApiTestActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, retrofitError.toString());
            }
        });
    }

    public void testUser(View v) {

        String userId = "6467";

        JsonObject jsonBody = new JsonObject();
        try {
            jsonBody.addProperty("username", "alvan");
            jsonBody.addProperty("password", "123456");
            jsonBody.addProperty("openid", "alvan");
            jsonBody.addProperty("access_token", "123456");
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

        RestServiceProvider.getTangxlApi().getUser(userId, jsonBody, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject responseBody, Response response) {
                Toast.makeText(ApiTestActivity.this, "Response is: " + responseBody.toString(), Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, responseBody.toString());
                LogUtils.d(TAG, response.getHeaders().toString());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(ApiTestActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, retrofitError.toString());
            }
        });
    }

    public void testBindWechat(View v) {
        JsonObject jsonBody = new JsonObject();
        try {
            jsonBody.addProperty("username", "alvan");
            jsonBody.addProperty("password", "123456");
            jsonBody.addProperty("openid", "alvan");
            jsonBody.addProperty("access_token", "123456");
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

        RestServiceProvider.getTangxlApi().bindWechat(jsonBody, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject responseBody, Response response) {
                Toast.makeText(ApiTestActivity.this, "Response is: " + responseBody.toString(), Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, responseBody.toString());
                LogUtils.d(TAG, response.getHeaders().toString());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(ApiTestActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, retrofitError.toString());
            }
        });
    }

    public void testGetFields(View v) {
        RestServiceProvider.getTangxlApi().fetchFields(new Callback<JsonArray>() {
            @Override
            public void success(JsonArray responseBody, Response response) {
                Toast.makeText(ApiTestActivity.this, "Response is: " + responseBody.toString(), Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, responseBody.toString());
                LogUtils.d(TAG, response.getHeaders().toString());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(ApiTestActivity.this, "请求出错啦", Toast.LENGTH_LONG).show();
                LogUtils.d(TAG, retrofitError.toString());
            }
        });
    }

    public void testShare(View view){
        mController.openShare(this, false);
    }

    public void testService(View view){

    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(LoginFinish event) {
        String name = event.getMessage();
        LogUtils.d(TAG, name);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}
