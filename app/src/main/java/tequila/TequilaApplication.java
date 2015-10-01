package tequila;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.tangxinli.android.tequila.providers.*;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.PrefUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

public class TequilaApplication extends Application {
    private static final String TAG = LogUtils.makeLogTag(TequilaApplication.class);

    private static Context mAppContext;

    private static TequilaApplication sInstance;

    /**
     * 单例，返回一个实例
     *
     * @return
     */
    public static TequilaApplication getInstance() {
        if (sInstance == null) {
            LogUtils.w(TAG, "[TequilaApplication] instance is null.");
        }
        return sInstance;
    }

    /*
     * 让自定义类能方便获取App Context
     */
    public static Context getAppContext() {
        return mAppContext;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PrefUtils.init(this);

        DaoProvider.init(getApplicationContext());

        mAppContext = getApplicationContext();

        //更新统计发送策略
        MobclickAgent.updateOnlineConfig(this);
        //启动友盟日志加密
        AnalyticsConfig.enableEncrypt(true);
        //友盟自动更新
        UmengUpdateAgent.update(this);

        //LeakCanary
        RefWatcherProvider.getInstance(this);

        SPDatabaseManager.getInstance(getApplicationContext());
        EventBusProvider.getInstance();
        JobManagerProvider.getInstance();

        //IM
        sInstance = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
