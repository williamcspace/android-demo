package tequila.models.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.TequilaApplication;
import com.tangxinli.android.tequila.dao.models.Field;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by williamc1986 on 7/16/15.
 */
public class FieldListHelper {
    private static final String TAG = FieldListHelper.class.getSimpleName();

    private static FieldListHelper sInstance;
    private List<Field> fields;
    private static SharedPreferences mSharedPreferences;

    protected FieldListHelper() {
        mSharedPreferences = TequilaApplication.getAppContext().getSharedPreferences(Config.FIELD_TABLE, Context.MODE_PRIVATE);
    }

    public static synchronized FieldListHelper getInstance() {
        if (sInstance == null) {
            sInstance = new FieldListHelper();
        }
        return sInstance;
    }

    public void setList(List<Field> fields) {
        this.fields = fields;
        Gson gson = new Gson();
        String value = gson.toJson(this.fields);
        mSharedPreferences.edit().putString(Config.DB_VERSION, value).apply();
        LogUtils.d(TAG, "setList(LIST<>): " + value);
    }

    public void setList(JsonArray ja) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Field>>() {}.getType();
        this.fields = gson.fromJson(ja, listType);
        String value = gson.toJson(this.fields);
        mSharedPreferences.edit().putString(Config.DB_VERSION, value).apply();
        LogUtils.d(TAG, "setList(JsonArray): " + value);
    }

    public List<Field> getList() {
        if (fields == null || fields.isEmpty()) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Field>>() {}.getType();
            fields = gson.fromJson(mSharedPreferences.getString(Config.DB_VERSION, ""), listType);
        }
        return fields;
    }
}
