package tequila.utils;

import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.wxapi.MD5;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by williamc1986 on 8/25/15.
 */
public class WXPayUtils {

    /**
     * 生成签名
     */
    public static String genSign(TreeMap<String, String> values) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : values.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            sb.append(key);
            sb.append('=');
            sb.append(value);
            sb.append('&');
        }

        sb.append("key=");
        sb.append(Config.WX_MCH_KEY);

        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return packageSign;
    }

    /**
     * 生成随机MD5
     */
    public static String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    /**
     * 生成当前时间戳
     */
    public static long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * TODO: 生成随机订单号，需要删除
     * 生成随机MD5
     */
    public static String genOutTradNo() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
}
