package tequila.providers;

import android.util.Log;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;
import com.tangxinli.android.tequila.TequilaApplication;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class JobManagerProvider {
    private static JobManager jobManager;

    private JobManagerProvider() {}

    private static void configureJobManager() {
        Configuration configuration = new Configuration.Builder(TequilaApplication.getAppContext())
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";

                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        LogUtils.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();
        jobManager = new JobManager(TequilaApplication.getAppContext(), configuration);
    }

    public static synchronized JobManager getInstance() {
        if (jobManager == null) {
            configureJobManager();
        }
        return jobManager;
    }
}
