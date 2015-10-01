package tequila.events;

import com.google.gson.JsonElement;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class OnFetchFieldsFinish extends BaseOnJsonFinish {
    public OnFetchFieldsFinish(Boolean err, String msg) {
        super(err, msg);
    }

    public OnFetchFieldsFinish(Boolean err, String msg, JsonElement je) {
        super(err, msg, je);
    }
}