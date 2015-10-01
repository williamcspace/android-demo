package tequila.events;

import com.tangxinli.android.tequila.rest.models.WXUnifiedOrderResponse;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class OnRequestUnifiedOrderFinish {
    private Boolean hasError;
    private String mMessage;
    private WXUnifiedOrderResponse mWXUnifiedOrderResponse;

    public OnRequestUnifiedOrderFinish(Boolean err, String msg) {
        this.hasError = err;
        this.mMessage = msg;
    }

    public OnRequestUnifiedOrderFinish(Boolean err, String msg, WXUnifiedOrderResponse uor) {
        this.hasError = err;
        this.mMessage = msg;
        this.mWXUnifiedOrderResponse = uor;
    }

    public WXUnifiedOrderResponse getExtra(){
        return mWXUnifiedOrderResponse;
    }

    public Boolean hasError() {
        return hasError;
    }

    public String getMessage() {
        return mMessage;
    }
}