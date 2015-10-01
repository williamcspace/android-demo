package tequila.utils;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

/**
 * Created by williamc1986 on 7/31/15.
 */
public class SnackbarUtils {
    public static final String TAG = SnackbarUtils.class.getSimpleName();

    public static void show(View view, CharSequence text, int duration) {
        try {
            Snackbar.make(view, text, duration).show();
        } catch (RuntimeException e) {
            Log.e(TAG, "Could not send crash Toast", e);
        }
    }

    public static void show(View view, int resId, int duration) {
        try {
            Snackbar.make(view, resId, duration).show();
        } catch (RuntimeException e) {
            Log.e(TAG, "Could not send crash Toast", e);
        }
    }

    public static void showLong(View view, CharSequence text) {
        show(view, text, Snackbar.LENGTH_LONG);
    }

    public static void showLong(View view, int resId) {
        show(view, resId, Snackbar.LENGTH_LONG);
    }

    public static void showShort(View view, CharSequence text) {
        show(view, text, Snackbar.LENGTH_SHORT);
    }

    public static void showShort(View view, int resId) {
        show(view, resId, Snackbar.LENGTH_SHORT);
    }
}
