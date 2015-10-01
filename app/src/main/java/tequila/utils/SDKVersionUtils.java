package tequila.utils;

import android.os.Build;

/**
 * Created by williamc1986 on 8/21/15.
 */
public class SDKVersionUtils {

    public static boolean isSmallerVersion(int version) {
        return (Build.VERSION.SDK_INT < version);
    }

    public static boolean isGreaterorEqual(int version) {
        return (Build.VERSION.SDK_INT >= version);
    }

    public static boolean isSmallerorEqual(int version) {
        return (Build.VERSION.SDK_INT <= version);
    }
}
