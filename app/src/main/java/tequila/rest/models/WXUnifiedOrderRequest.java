package tequila.rest.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by williamc1986 on 8/25/15.
 */
@Root(name = "xml")
public class WXUnifiedOrderRequest {

    @Element(name = "appid", required = false)
    public String appId;

    @Element(name = "mch_id", required = false)
    public String mchId;

    @Element(name = "device_info", required = false)
    public String deviceInfo;

    @Element(name = "nonce_str", required = false)
    public String nonceStr;

    @Element(name = "sign", required = false)
    public String sign;

    @Element(name = "body", required = false)
    public String body;

    @Element(name = "detail", required = false)
    public String detail;

    @Element(name = "attach", required = false)
    public String attach;

    @Element(name = "out_trade_no", required = false)
    public String outTradeNo;

    @Element(name = "fee_type", required = false)
    public String feeType;

    @Element(name = "total_fee", required = false)
    public String totalFee;

    @Element(name = "spbill_create_ip", required = false)
    public String spbillCreateIp;

    @Element(name = "time_start", required = false)
    public String timeStart;

    @Element(name = "time_expire", required = false)
    public String timeExpire;

    @Element(name = "goods_tag", required = false)
    public String goodsTag;

    @Element(name = "notify_url", required = false)
    public String notifyUrl;

    @Element(name = "trade_type", required = false)
    public String tradeType;

    @Element(name = "product_id", required = false)
    public String productId;

    @Element(name = "limit_pay", required = false)
    public String limitPay;

    @Element(name = "openid", required = false)
    public String openId;

    public WXUnifiedOrderRequest() {}
}
