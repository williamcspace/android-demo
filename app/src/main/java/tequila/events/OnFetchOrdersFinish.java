package tequila.events;

import com.google.gson.JsonElement;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class OnFetchOrdersFinish extends BaseOnJsonFinish {
    public OnFetchOrdersFinish(Boolean err, String msg) {
        super(err, msg);
    }

    public OnFetchOrdersFinish(Boolean err, String msg, JsonElement je) {
        super(err, msg, je);
    }
}