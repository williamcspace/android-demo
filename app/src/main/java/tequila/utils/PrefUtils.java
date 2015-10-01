package tequila.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.TequilaApplication;

/**
 * Created by williamc1986 on 7/29/15.
 */
public class PrefUtils {
    private static final String TAG = PrefUtils.class.getSimpleName();

    public static final String COOKIE_KEY = "Set-Cookie";
    public static final String SESSION_KEY = "express:sess";
    public static final String SESSION_KEY_SIG = "express:sess.sig";

    public static final String PREF_APP_VERSION = "pref_app_version";
    public static final String PREF_WELCOME_DONE = "pref_welcome_done";
    public static final String PREF_ORDER_CONTENT = "pref_order_content";
    public static final String PREF_LOGIN = "pref_login";
    public static final String PREF_USER_CACHE = "pref_user";

    public static void init(final Context context) {
        // Check what year we're configured for
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String appVersion = sp.getString(PREF_APP_VERSION, "");
        if (!Config.APP_VERSION.equals(appVersion)) {
            LogUtils.d(TAG, "App版本不一致, 重置数据.");
            sp.edit().clear().putString(PREF_APP_VERSION, Config.APP_VERSION).apply();
        }
    }

    public static Boolean isWelcomeDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_WELCOME_DONE, false);
    }

    public static void markWelcomeDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_WELCOME_DONE, true).apply();
    }

    public static boolean isLogin(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_LOGIN, false);
    }

    public static boolean markLogin(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.edit().putBoolean(PREF_LOGIN, true).commit();
    }

    public static boolean unmarkLogin(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.edit().putBoolean(PREF_LOGIN, false).commit();
    }

    public static boolean setSessionCookie(String cookie) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TequilaApplication.getAppContext());
        return sp.edit().putString(COOKIE_KEY, cookie).commit();
    }

    public static String getSessionCookie() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TequilaApplication.getAppContext());
        return sp.getString(COOKIE_KEY, "");
    }

    public static boolean setOrderContent(String order) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TequilaApplication.getAppContext());
        return sp.edit().putString(PREF_ORDER_CONTENT, order).commit();
    }

    public static String getOrderContent() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TequilaApplication.getAppContext());
        return sp.getString(PREF_ORDER_CONTENT, "");
    }

    public static boolean setUserCache(String cookie) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TequilaApplication.getAppContext());
        return sp.edit().putString(PREF_USER_CACHE, cookie).commit();
    }

    public static String getUserCache() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TequilaApplication.getAppContext());
        return sp.getString(PREF_USER_CACHE, "");
    }
}
