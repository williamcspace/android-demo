package williamcspace.android.demo.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.models.PostModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by williamc1986 on 7/29/15.
 */
public class PrefUtils {
    private static final String TAG = PrefUtils.class.getSimpleName();

    public static final String PREF_APP_VERSION = "pref_app_version";
    public static final String PREF_WELCOME_DONE = "pref_welcome_done";
    public static final String PREF_ARTICLE_CACHE = "pref_articles_cache";

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

    public static List<PostModel> getArticles(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String data = sp.getString(PREF_ARTICLE_CACHE, "");
        Gson gson = new Gson();
        Type articleType = new TypeToken<List<PostModel>>() {}.getType();
        return gson.fromJson(data, articleType);
    }

    public static void setArticles(final Context context, List<PostModel> posts) {
        Gson gson = new Gson();
        Type articleType = new TypeToken<List<PostModel>>() {}.getType();
        String data = gson.toJson(posts, articleType);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_ARTICLE_CACHE, data).apply();
    }
}
