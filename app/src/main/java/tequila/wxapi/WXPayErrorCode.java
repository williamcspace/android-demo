package tequila.wxapi;

/**
 * Created by williamc1986 on 8/25/15.
 */
public class WXPayErrorCode {
    //https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_1
    public static final String NOAUTH = "NOAUTH";
    public static final String NOTENOUGH = "NOTENOUGH";
    public static final String ORDERPAID = "ORDERPAID";
    public static final String ORDERCLOSED = "ORDERCLOSED";
    public static final String SYSTEMERROR = "SYSTEMERROR";
    public static final String APPID_NOT_EXIST = "APPID_NOT_EXIST";
    public static final String MCHID_NOT_EXIST = "MCHID_NOT_EXIST";
    public static final String APPID_MCHID_NOT_MATCH = "APPID_MCHID_NOT_MATCH";
    public static final String LACK_PARAMS = "LACK_PARAMS";
    public static final String OUT_TRADE_NO_USED = "OUT_TRADE_NO_USED";
    public static final String SIGNERROR = "SIGNERROR";
    public static final String XML_FORMAT_ERROR = "XML_FORMAT_ERROR";
    public static final String REQUIRE_POST_METHOD = "REQUIRE_POST_METHOD";
    public static final String POST_DATA_EMPTY = "POST_DATA_EMPTY";
    public static final String NOT_UTF8 = "NOT_UTF8";
}
