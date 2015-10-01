package tequila.helpers;

import android.app.Activity;
import android.content.Context;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by williamc1986 on 8/21/15.
 */
public class MethodInvoke {
    private static final String TAG = "ECSDK_Demo.MethodInvoke";

    public static int getTransitionValue(String componentName) {
        Class<?> clazz = getTransitionClass(componentName);
        if (clazz != null) {
            return getAnnotationValue(clazz);
        }
        return 0;
    }

    private static Class<?> getTransitionClass(String componentName) {
        try {
            Class clazz = Class.forName(componentName);
            return clazz;
        } catch (ClassNotFoundException e) {
            LogUtils.e(TAG, "Class " + componentName + " not found in dex");
        }
        return null;
    }

    public static int getAnnotationValue(Class<?> clazz) {
        ActivityTransition clazzAnnotation = clazz.getAnnotation(ActivityTransition.class);
        if (clazzAnnotation != null) {
            return clazzAnnotation.value();
        }
        while (clazz.getSuperclass() != null) {
            clazz = clazz.getSuperclass();
            clazzAnnotation = clazz.getAnnotation(ActivityTransition.class);
            if (clazzAnnotation != null) {
                return clazzAnnotation.value();
            }
        }
        return 0;
    }

    public static void startTransitionPopin(Context context) {
        if ((context == null) || (!(context instanceof Activity))) {
            return;
        }
        ((Activity) context).overridePendingTransition(R.anim.pop_in, R.anim.anim_not_change);
    }

    public static void startTransitionPopout(Context context) {
        if ((context == null) || (!(context instanceof Activity))) {
            return;
        }
        ((Activity) context).overridePendingTransition(R.anim.anim_not_change, R.anim.pop_out);
    }

    public static void startTransitionNotChange(Context context) {
        if ((context == null) || (!(context instanceof Activity))) {
            return;
        }
        ((Activity) context).overridePendingTransition(R.anim.anim_not_change, R.anim.anim_not_change);
    }


    public interface OnSwipeInvokeResultListener {
        void onSwipeInvoke(boolean success);
    }

    public static class SwipeInvocationHandler implements InvocationHandler {

        public WeakReference<OnSwipeInvokeResultListener> mWeakReference;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (mWeakReference == null) {
                LogUtils.i(TAG, "swipe invoke fail, callbackRef NULL!");
                return null;
            }
            OnSwipeInvokeResultListener onSwipeInvokeResultListener = mWeakReference.get();
            if (onSwipeInvokeResultListener == null) {
                LogUtils.i(TAG, "swipe invoke fail, callback NULL!");
                return null;
            }
            boolean result = false;
            if (args != null) {
                if (args.length > 0) {
                    boolean isBoolArgs = (args[0] instanceof Boolean);
                    if (isBoolArgs) {
                        result = ((Boolean) args[0]).booleanValue();
                    }
                }
            }
            onSwipeInvokeResultListener.onSwipeInvoke(result);
            return null;
        }
    }

}
