package tequila.models;

import android.content.Context;
import com.google.gson.JsonObject;
import com.tangxinli.android.tequila.providers.SPDatabaseManager;

public class UserModel extends BaseModel implements Savable {
    private static final String TAG = UserModel.class.getSimpleName();

    public UserModel(){
        this.mData = SPDatabaseManager.getInstance().getAsJsonObject(TAG);
    }

    public UserModel(JsonObject user) {
        this.mData = user;
    }

    public UserModel(Context context, JsonObject user){
        this.mContext = context;
        this.mData = user;
    }

    public JsonObject getUser(){
        return this.mData;
    }

    public Boolean save() {
        return SPDatabaseManager.getInstance().setAsJsonObject(TAG, this.mData);
    }
}