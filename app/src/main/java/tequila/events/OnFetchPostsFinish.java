package tequila.events;

import com.google.gson.JsonElement;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class OnFetchPostsFinish extends BaseOnJsonFinish {
    public OnFetchPostsFinish(Boolean err, String msg) {
        super(err, msg);
    }
    public OnFetchPostsFinish(Boolean err, String msg, JsonElement je) {
        super(err, msg, je);
    }
}