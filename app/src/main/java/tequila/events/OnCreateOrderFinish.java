package tequila.events;

import com.google.gson.JsonElement;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class OnCreateOrderFinish extends BaseOnJsonFinish {
    public OnCreateOrderFinish(Boolean err, String msg) {
        super(err, msg);
    }

    public OnCreateOrderFinish(Boolean err, String msg, JsonElement je) {
        super(err, msg, je);
    }
}