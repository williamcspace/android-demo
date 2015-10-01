package tequila.jobs;

import com.google.gson.JsonObject;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.tangxinli.android.tequila.events.OnFetchTweetFinish;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class FetchTweetJob extends Job {
    public final String TAG = LogUtils.makeLogTag(FetchTweetJob.class);

    private Integer mTweetId;
    public FetchTweetJob(Integer tid) {
        // requireNetwork() 需要联网,
        // groupBy("String"): 请求分类，同个请求不会重复请求
        // persist(): Serialize Job Object 并保存数据库；预设Java解析没办法Serialize当前Job
        super(new Params(Priority.HIGH).requireNetwork().groupBy("posts"));
        mTweetId = tid;
    }

    @Override
    public void onAdded() {
        // 在这更新UI，Job 已经写入硬盘.
        LogUtils.d(TAG, "Fetch Posts Job Added");
//        EventBusProvider.getInstance().post(new ToggleLoading(true));
    }

    @Override
    public void onRun() throws Throwable {
        LogUtils.d(TAG, "Run Fetch Posts request now");
        RestServiceProvider.getTangxlApi().fetchTweet(mTweetId.toString(), new Callback<JsonObject>() {
            @Override
            public void success(final JsonObject jsonObject, Response response) {
                LogUtils.d(TAG, jsonObject.toString());
                EventBusProvider.getInstance().post(new OnFetchTweetFinish(false, "Fetch posts success", jsonObject));
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtils.d(TAG, "Fetch Posts failure: " + error);
                EventBusProvider.getInstance().post(new OnFetchTweetFinish(true, "Fetch Posts failure:" + error));
            }
        });
    }

    @Override
    protected void onCancel() {
        //Job超过重试次数或shouldReRunOnThrowable() 返回 false.
        LogUtils.d(TAG, "Fetch Posts Cancelled");
        EventBusProvider.getInstance().post(new OnFetchTweetFinish(true, "job Cancelled"));
    }
}