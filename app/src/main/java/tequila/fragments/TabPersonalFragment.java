package tequila.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alipay.sdk.app.PayTask;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.test.LoginTestActivity;
import com.tangxinli.android.tequila.activities.test.RecyclerTestActivity;
import com.tangxinli.android.tequila.events.OnRequestUnifiedOrderFinish;
import com.tangxinli.android.tequila.jobs.RequestWXUnifiedOrderJob;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.JobManagerProvider;
import com.tangxinli.android.tequila.rest.models.WXUnifiedOrderRequest;
import com.tangxinli.android.tequila.rest.models.WXUnifiedOrderResponse;
import com.tangxinli.android.tequila.utils.*;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TreeMap;


/**
 * Created by williamc1986 on 7/15/15.
 */
public class TabPersonalFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(TabPersonalFragment.class);

    IWXAPI msgApi;
    PayReq req;

    public TabPersonalFragment() {
    }

    public static TabPersonalFragment newInstance() {
        return new TabPersonalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        msgApi = WXAPIFactory.createWXAPI(getActivity(), null);
        req = new PayReq();
        msgApi.registerApp(Config.WX_APP_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_personal, container, false);

        View loginView = view.findViewById(R.id.login_btn);
        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginTestActivity.class));
            }
        });

        View payTest = view.findViewById(R.id.pay_test);
        payTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXPay();
            }
        });

        View aliPayTest = view.findViewById(R.id.alipay_test);
        aliPayTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aliPay(getActivity());
            }
        });

        View testListAdapter = view.findViewById(R.id.recycler_test);
        testListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RecyclerTestActivity.class));
            }
        });

        LogUtils.d(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //TODO:TEST PAY, NEED TO REMOVE
    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume");
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "onPause");
        EventBusProvider.getInstance().unregister(this);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnRequestUnifiedOrderFinish event) {
        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
        }

        sendPayReq(event.getExtra());
        LogUtils.d(TAG, event.getExtra().prepayId);
    }


    private void sendPayReq(WXUnifiedOrderResponse body) {

        req.appId = Config.WX_APP_ID;
        req.partnerId = Config.WX_MCH_ID;
        req.prepayId = body.prepayId;
        req.packageValue = "Sign=WXPay";
        req.nonceStr = WXPayUtils.genNonceStr();
        req.timeStamp = String.valueOf(WXPayUtils.genTimeStamp());

        TreeMap<String, String> values = new TreeMap<>();
        values.put("appid", req.appId);
        values.put("noncestr", req.nonceStr);
        values.put("package", req.packageValue);
        values.put("partnerid", req.partnerId);
        values.put("prepayid", req.prepayId);
        values.put("timestamp", req.timeStamp);

        req.sign = WXPayUtils.genSign(values);

        msgApi.sendReq(req);
    }


    private void WXPay() {
        WXUnifiedOrderRequest order = genOrderBody();
        JobManagerProvider.getInstance().addJobInBackground(new RequestWXUnifiedOrderJob(order));
    }

    private WXUnifiedOrderRequest genOrderBody() {
        String nonceStr = WXPayUtils.genNonceStr();
        String outTradeNo = WXPayUtils.genOutTradNo();
        String deviceIp = NetworkUtils.getIPAddress(true);

        TreeMap<String, String> values = new TreeMap<>();
        values.put("appid", Config.WX_APP_ID);
        values.put("body", "测试微信支付");
        values.put("mch_id", Config.WX_MCH_ID);
        values.put("nonce_str", nonceStr);
        values.put("notify_url", "http://www.tangxinli.com");
        values.put("out_trade_no", outTradeNo);
        values.put("spbill_create_ip", deviceIp);
        values.put("total_fee", "1");
        values.put("trade_type", "APP");

        String sign = WXPayUtils.genSign(values);
        values.put("sign", sign);

        WXUnifiedOrderRequest body = new WXUnifiedOrderRequest();
        body.appId = Config.WX_APP_ID;
        body.body = "测试微信支付";
        body.mchId = Config.WX_MCH_ID;
        body.nonceStr = nonceStr;
        body.notifyUrl = "http://www.tangxinli.com";
        body.outTradeNo = outTradeNo;
        body.spbillCreateIp = deviceIp;
        body.totalFee = "1";
        body.tradeType = "APP";
        body.sign = sign;

        return body;
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void aliPay(final Activity activity) {
        if (TextUtils.isEmpty(Config.ALIPAY_PARTNER) || TextUtils.isEmpty(Config.ALIPAY_RSA_PRIVATE) || TextUtils.isEmpty(Config.ALIPAY_SELLER)) {
            //TODO: "警告: 需要配置PARTNER | RSA_PRIVATE| SELLER"
            return;
        }
        // 订单
        String orderInfo = AliPayUtils.getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
        // 对订单做RSA 签名
        String sign = AliPayUtils.sign(orderInfo);

        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + AliPayUtils.getSignType();

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = AliPayUtils.SDK_PAY_FLAG;
                msg.obj = result;
                AliPayUtils.handleMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
