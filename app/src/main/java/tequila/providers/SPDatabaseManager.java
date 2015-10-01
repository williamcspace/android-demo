package tequila.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*
 *  SPDatabaseManager的目的是使用SharedPreference来提供一个简单轻量存储实现，
 *  和提供一个getInstance() 接口给其他自定义类使用，无需传入android context
 */
public class SPDatabaseManager {
    private static final String TAG = SPDatabaseManager.class.getSimpleName();
    private static SPDatabaseManager sInstance;
    private static final String DB_VERSION = "v1.0.0";
    private static SharedPreferences mSharedPreferences;

    private String getDBKey(String key){
        return TAG + "-" + DB_VERSION + "-" + key;
    }

    private SPDatabaseManager(Context context){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public static synchronized SPDatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SPDatabaseManager(context);
        }
        return sInstance;
    }

    public static synchronized SPDatabaseManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(TAG + " 尚未实例化，请先使用getInstance(Context context)调用");
        }
        return sInstance;
    }


    public String getDBVersion() {
        return DB_VERSION;
    }

    public boolean set(String key, String value) {
        return mSharedPreferences.edit().putString(getDBKey(key), value).commit();
    }

    public boolean setAsJsonObject(String key, JsonObject value) {
        return mSharedPreferences.edit().putString(getDBKey(key), value.toString()).commit();
    }

    public String getAsString(String key) {
        return mSharedPreferences.getString(getDBKey(key), "");
    }

    public JsonObject getAsJsonObject(String key) {
        JsonElement jo = new Gson().fromJson(mSharedPreferences.getString(getDBKey(key), ""), JsonObject.class);
        return jo.getAsJsonObject();
    }

    public boolean remove(String key) {
        return mSharedPreferences.edit().remove(getDBKey(key)).commit();
    }

    public boolean clearAll() {
        return mSharedPreferences.edit().clear().commit();
    }
}