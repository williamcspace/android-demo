package tequila;

import android.util.Log;

public class Config {
    public static final String APP_VERSION = "v0.0.8";

    // (MIN) assert -> error -> warn -> info -> debug -> verbose (MAX)
    public static final int LOG_LEVEL = Log.VERBOSE;

//    public static final String TXL_API_URL = "http://192.168.1.4:3000";
    public static final String TXL_API_URL = "http://192.168.0.110:3000";
//    public static final String TXL_API_URL = "http://test.tangxinli.com";
    public static final String WX_API_URL = "https://api.weixin.qq.com";
    public static final String WX_PAY_API_URL = "https://api.mch.weixin.qq.com";

    public static final String RECT_MD_IMG = "/rect/md?q=";

    public static final Integer PAGER_SIZE = 15;

    public static final String COOKIE_KEY = "Set-Cookie";
    public static final String SESSION_KEY = "express:sess";
    public static final String SESSION_KEY_SIG = "express:sess.sig";

    public static final String DB_VERSION = "v0.0.8";
    public static final Integer SQL_SCHEMA_VERSION = 1;
    public static final String FIELD_TABLE = "tequila_field_table";


    public static final String TXL_NOTIFY_URL = "http://www.tangxinli.com";

    //微信开放ID
    public static final String WX_APP_ID = "wxd6ea79a9cfe9a6c2";
    public static final String WX_APP_SECRET = "2372e90f6967c4c992ddf76b8893ea13";
    public static final String WX_MCH_ID = "1252795601";
    public static final String WX_MCH_KEY = "6d9c547cf146054a5a720606a7694467";

    //支付宝
    // 商户PID
    public static final String ALIPAY_PARTNER = "2088811650935840";
    public static final String ALIPAY_KEY = "xd72x235azqe9ciqwax86u4ywntn8igf";
    // 商户收款账号
    public static final String ALIPAY_SELLER = "pay@tangxinli.com";
    // 商户私钥，pkcs8格式
    public static final String ALIPAY_RSA_PRIVATE = "MIICXQIBAAKBgQDZBh3v5gxcSQ77MfW9Q6JNuZwwrNMO5cOfXQnS/Ewa6HFKHwCZZNHYWrZfTu+HNtW2QhxJYtRuuUUvovT2mTeUupdnYmtkCGBjTMN0weHP9cNdnf/aF+GNjnfI+Rf3nfFCWdSmi6gGHaFZnqO52GOezcQrnLDbje7cWxG8CUOCLwIDAQABAoGARjJSUJxqdxcyf1GjmLpdryJQQPZFtlXfNpbTbKkqvLFeo4jvmq4fqgl9X8qac3PrIG61047iA4ZtuIDPF0xry1krp3V6SAqZ0yxdjDECb4p1TKyyavORdTgd8mGq7DEJa3lLwunvqCs0WL0S+WlFIRzWtNOUZIgPrCIIR9Tc5ZECQQDsiEC1IErNGnxWyJztbm49mBLDfsK20EEBVlFPMLGv4drmNj4aMZL/UkMq1cLY9xAovMBozpwEoxQbreDR65gNAkEA6uLSg3Bfby4Q0OuMIIBq9ZSik/zhflYaH98i/LWE7Ak296x2bnwmCPm07bvFh2ygAt3Oippwdxsu/73HyH7YKwJBANHNja/bY94H1zBXI7MK1+f0gvg68NWdZXudJ+QZKhL7P+IhTPaI8H1eZ0uQbhNrGj84JWcRlJwcVtKyaY9p7OUCQQCcbonjLfDxO83JRkah0sTpX59TTgTjvfZSKarEgSPQi2aHVt3dQWOXcK6V8gtg4PBEWHLZDng+auVHP56RoiDXAkAmxQ6gz6Glc2IA1feUgq/LbhLx2X/txlmPAaB1LLOI6OWotACr0I5QUBdD//2L9SXsrk6datkrBdBUzKIWgI1G";
    // 支付宝公钥
    public static final String ALIPAY_RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB-----END PUBLIC KEY-----";


    //QQ开放ID
    public static final String QQ_APP_ID = "100424468";
    public static final String QQ_APP_KEY = "c7394704798a158208a74ab60104f0ba";
}
