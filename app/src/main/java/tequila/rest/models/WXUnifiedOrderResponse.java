package tequila.rest.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by williamc1986 on 8/25/15.
 */
@Root(name = "xml")
public class WXUnifiedOrderResponse {

    @Element(name = "return_code", required = false)
    public String returnCode;

    @Element(name = "return_msg", required = false)
    public String returnMsg;

    //以下字段在return_code为SUCCESS的时候有返回
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

    @Element(name = "result_code", required = false)
    public String resultCode;

    @Element(name = "err_code", required = false)
    public String errCode;

    @Element(name = "err_code_des", required = false)
    public String errCodeDes;

    //以下字段在return_code 和result_code都为SUCCESS的时候有返回
    @Element(name = "trade_type", required = false)
    public String tradeType;

    @Element(name = "prepay_id", required = false)
    public String prepayId;

    @Element(name = "code_url", required = false)
    public String codeUrl;

    public WXUnifiedOrderResponse() {}
}
