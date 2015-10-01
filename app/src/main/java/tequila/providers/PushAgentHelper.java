package tequila.providers;

import android.content.Context;
import com.umeng.message.PushAgent;

/**
 * Created by williamc1986 on 7/15/15.
 * 使用helper来统一在base activity中调用
 */
public class PushAgentHelper {
    private static final String TAG = PushAgentHelper.class.getSimpleName();

    public Boolean isEnable = false;
    private static PushAgentHelper sInstance;
    private PushAgent sPushAgent;

    private PushAgentHelper(Context context) {
        sPushAgent = PushAgent.getInstance(context);
    }

    public static synchronized PushAgentHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushAgentHelper(context);
        }
        return sInstance;
    }

    //如果你的应用继承了Application, 不要在Application onCreate() 中调用 mPushAgent.enable();. 由于SDK 设计的逻辑， 这会造成循环。
    //确保enable只被调用一次
    public synchronized void enable() {
        if (!isEnable) {
            sPushAgent.enable();

//            如果在测试或其他使用场景中，需要获取设备的Device Token，可以使用下面的方法
//            LogUtils.d(TAG, UmengRegistrar.getRegistrationId(this));
        }
    }

    public synchronized void disable() {
        if (isEnable) {
            sPushAgent.disable();
        }
    }

    //在所有的Activity 的onCreate 函数添加, 直接加在Base activity里
    public synchronized void onAppStart() {
        sPushAgent.onAppStart();
    }
}
