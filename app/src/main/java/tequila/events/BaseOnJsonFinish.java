package tequila.events;

import com.google.gson.JsonElement;

/**
 * Created by williamc1986 on 7/14/15.
 */

public class BaseOnJsonFinish {
    private Boolean hasError;
    private JsonElement mJsonElement;
    private String mMessage;

    public BaseOnJsonFinish(Boolean err, String msg) {
        hasError = err;
        mMessage = msg;
    }

    public BaseOnJsonFinish(Boolean err, String msg, JsonElement je){
        hasError = err;
        mMessage = msg;
        mJsonElement = je;
    }

    public JsonElement getExtra() {
        return mJsonElement;
    }
    public Boolean hasError() {
        return hasError;
    }
    public String getMessage() {
        return mMessage;
    }
}