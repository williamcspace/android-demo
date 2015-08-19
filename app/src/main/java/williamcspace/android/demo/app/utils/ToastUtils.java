package williamcspace.android.demo.app.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by williamc1986 on 7/30/15.
 */
public class ToastUtils {
    public static final String TAG = ToastUtils.class.getSimpleName();

    public static void show(Context context, CharSequence text, int duration) {
        try {
            Toast.makeText(context, text, duration).show();
        } catch (RuntimeException e) {
              Log.e(TAG, "Could not send crash Toast", e);
        }
    }

    public static void show(Context context, int resId, int duration){
        try {
            Toast.makeText(context, resId, duration).show();
        } catch (RuntimeException e) {
            Log.e(TAG, "Could not send crash Toast", e);
        }
    }

    public static void showLong(Context context, CharSequence text){
        show(context, text, Toast.LENGTH_LONG);
    }

    public static void showLong(Context context, int resId) {
        show(context, resId, Toast.LENGTH_LONG);
    }

    public static void showShort(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void showShort(Context context, int resId) {
        show(context, resId, Toast.LENGTH_SHORT);
    }
}
