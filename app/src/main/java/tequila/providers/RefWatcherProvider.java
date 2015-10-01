package tequila.providers;

import android.content.Context;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tangxinli.android.tequila.TequilaApplication;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class RefWatcherProvider {
    private static RefWatcher refWatcher;

    private RefWatcherProvider() {}

    public static synchronized RefWatcher getInstance(Context context) {
        //RefWatcher refWatcher = {...};
        //We expect schrodingerCat to be gone soon (or not), let's watch it.
        //refWatcher.watch(schrodingerCat);

        if (refWatcher == null) {
            refWatcher = LeakCanary.install((TequilaApplication) context.getApplicationContext());
        }
        return refWatcher;
    }
}
