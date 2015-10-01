package tequila.common.utils;

import com.tangxinli.android.tequila.utils.PrefUtils;

import java.util.List;

public class CookieHandler {
    private static final String TAG = CookieHandler.class.getSimpleName();

    public static void processCookie(List<String> cookieHeaders) {
        String cookieStr = "";
        for (String cookieHeader : cookieHeaders) {
            String[] splitCookieStr = cookieHeader.split(";");
            cookieStr += splitCookieStr[0] + ";";
        }
        setSessionCookie(cookieStr);
    }

    public static void setSessionCookie(String cookie) {
        if(!cookie.isEmpty()){
            PrefUtils.setSessionCookie(cookie);
        }
    }

    public static String getSessionCookie() {
        return PrefUtils.getSessionCookie();
    }
}
