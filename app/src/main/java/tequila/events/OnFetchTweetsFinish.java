package tequila.events;

import com.google.gson.JsonElement;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class OnFetchTweetsFinish extends BaseOnJsonFinish {
    public OnFetchTweetsFinish(Boolean err, String msg) {
        super(err, msg);
    }

    public OnFetchTweetsFinish(Boolean err, String msg, JsonElement je) {
        super(err, msg, je);
    }
}