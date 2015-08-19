package williamcspace.android.demo.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import com.tangxinli.android.tequila.TequilaApplication;

/**
 * Created by williamc1986 on 8/5/15.
 */
public class NetworkUtils {
    public static final String TAG = LogUtils.makeLogTag(SnackbarUtils.class);

    private NetworkUtils(){}

    public static Boolean isOnline(){
        Context mContext = TequilaApplication.getAppContext();
        ConnectivityManager cm = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
