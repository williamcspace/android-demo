package tequila.events;

/**
 * Created by williamc1986 on 7/14/15.
 */

public abstract class BaseOnXmlFinish {
    private Boolean hasError;
    private String mMessage;

    public BaseOnXmlFinish(Boolean err, String msg) {
        hasError = err;
        mMessage = msg;
    }

    public Boolean hasError() {
        return hasError;
    }
    public String getMessage() {
        return mMessage;
    }
}