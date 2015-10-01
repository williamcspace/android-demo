package tequila.events;

import android.content.Intent;

/**
 * Created by williamc1986 on 8/19/15.
 */
public class StartActivity {
    private boolean hasError = false;
    private Intent mIntent;

    public StartActivity(boolean hasError, Intent intent) {
        this.hasError = hasError;
        this.mIntent = intent;
    }

    public boolean hasError() {
        return hasError;
    }
    public Intent getIntent() {
        return mIntent;
    }
}
