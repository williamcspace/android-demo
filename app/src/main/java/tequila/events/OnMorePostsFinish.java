package tequila.events;

import com.google.gson.JsonElement;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class OnMorePostsFinish extends BaseOnJsonFinish {
    public OnMorePostsFinish(Boolean err, String msg) {
        super(err, msg);
    }
    public OnMorePostsFinish(Boolean err, String msg, JsonElement je) {
        super(err, msg, je);
    }
}