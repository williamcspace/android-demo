package tequila.jobs;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.tangxinli.android.tequila.dao.models.Channel;
import com.tangxinli.android.tequila.dao.models.ChannelDao;
import com.tangxinli.android.tequila.events.OnFetchChannelsFinish;
import com.tangxinli.android.tequila.providers.DaoProvider;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * Created by williamc1986 on 7/16/15.
 */
public class FetchChannelsJob extends Job {
    private final String TAG = FetchChannelsJob.class.getSimpleName();

    public FetchChannelsJob() {
        // requireNetwork() 需要联网,
        // groupBy("String"): 请求分类，同个请求不会重复请求
        // persist(): Serialize Job Object 并保存数据库；预设Java解析没办法Serialize当前Job
        super(new Params(Priority.HIGH).requireNetwork().groupBy("channels"));
    }

    @Override
    public void onAdded() {
        // 在这更新UI，Job 已经写入硬盘.
    }

    @Override
    public void onRun() throws Throwable {
        RestServiceProvider.getTangxlApi().fetchChannels(new Callback<List<Channel>>() {
            @Override
            public void success(List<Channel> channels, Response response) {
                LogUtils.d(TAG, channels.toString());
                for (Channel channel : channels) {
                    LogUtils.d(TAG, channel.getName());
                }

                if (channels.size() > 0) {
                    ChannelDao channelDao = DaoProvider.getSession().getChannelDao();
                    channelDao.insertOrReplaceInTx(channels);
                    EventBusProvider.getInstance().post(new OnFetchChannelsFinish(false, "Fetch posts success"));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtils.d(TAG, "error: " + error);
                EventBusProvider.getInstance().post(new OnFetchChannelsFinish(true, "Login failure:" + error));
            }
        });
    }

    @Override
    protected void onCancel() {
        //Job超过重试次数或shouldReRunOnThrowable() 返回 false.
        LogUtils.d(TAG, "JobCancelled");
        EventBusProvider.getInstance().post(new OnFetchChannelsFinish(true, "job Cancelled"));
    }
}
