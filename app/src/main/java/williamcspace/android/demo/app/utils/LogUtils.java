package williamcspace.android.demo.app.utils;

import android.util.Log;
import com.tangxinli.android.tequila.Config;

/**
 * Created by williamc1986 on 7/30/15.
 */
public class LogUtils {
    private static final String LOG_PREFIX = "tequila_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    private LogUtils() {
    }

    // 保证log tag的长度不会超出限制
    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    /**
     * 混淆危险!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void wtf(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.ASSERT >= Config.LOG_LEVEL) {
            Log.wtf(tag, message);
        }
    }

    public static void wtf(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.ASSERT >= Config.LOG_LEVEL) {
            Log.wtf(tag, message, cause);
        }
    }

    public static void e(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.ERROR >= Config.LOG_LEVEL) {
            Log.e(tag, message);
        }
    }

    public static void e(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.ERROR >= Config.LOG_LEVEL) {
            Log.e(tag, message, cause);
        }
    }

    public static void w(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.WARN >= Config.LOG_LEVEL) {
            Log.w(tag, message);
        }
    }

    public static void w(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.WARN >= Config.LOG_LEVEL) {
            Log.w(tag, message, cause);
        }
    }

    public static void i(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.INFO >= Config.LOG_LEVEL) {
            Log.i(tag, message);
        }
    }

    public static void i(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.INFO >= Config.LOG_LEVEL) {
            Log.i(tag, message, cause);
        }
    }


    public static void d(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.DEBUG >= Config.LOG_LEVEL) {
            Log.d(tag, message);
        }
    }

    public static void d(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.DEBUG >= Config.LOG_LEVEL) {
            Log.d(tag, message, cause);
        }
    }


    public static void v(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.VERBOSE >= Config.LOG_LEVEL) {
            Log.v(tag, message);
        }
    }

    public static void v(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (Log.VERBOSE >= Config.LOG_LEVEL) {
            Log.v(tag, message, cause);
        }
    }
}
