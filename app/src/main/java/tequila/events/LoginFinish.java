package tequila.events;

import com.tangxinli.android.tequila.models.UserModel;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class LoginFinish {
    private boolean hasError = false;
    private UserModel userModel;
    private String message;

    public LoginFinish(boolean hasError, String message) {
        this.hasError = hasError;
        this.message = message;
    }

    public LoginFinish(boolean hasError, UserModel userModel) {
        this.hasError = hasError;
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getMessage() {
        return message;
    }
}