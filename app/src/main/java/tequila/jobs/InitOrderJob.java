package tequila.jobs;

import com.google.gson.JsonObject;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.tangxinli.android.tequila.events.OnFetchPostsFinish;
import com.tangxinli.android.tequila.events.OnInitOrderFinish;
import com.tangxinli.android.tequila.events.ToggleLoading;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class InitOrderJob extends Job {
    public final String TAG = LogUtils.makeLogTag(InitOrderJob.class);

    private String mTopic;
    private String mDate;
    private String mTime;


    public InitOrderJob(String topic, String date, String time) {
        // requireNetwork() 需要联网,
        // groupBy("String"): 请求分类，同个请求不会重复请求
        // persist(): Serialize Job Object 并保存数据库；预设Java解析没办法Serialize当前Job
        super(new Params(Priority.HIGH).requireNetwork().groupBy("posts"));
        mTopic = topic;
        mDate = date;
        mTime = time;
    }

    @Override
    public void onAdded() {
        // 在这更新UI，Job 已经写入硬盘.
        LogUtils.d(TAG, "Fetch Posts Job Added");
        EventBusProvider.getInstance().post(new ToggleLoading(true));
    }

    @Override
    public void onRun() throws Throwable {
        LogUtils.d(TAG, "Run Fetch Posts request now");
        RestServiceProvider.getTangxlApi().initOrder(mTopic, mDate, mTime, new Callback<JsonObject>() {
            @Override
            public void success(final JsonObject jsonObject, Response response) {
                LogUtils.d(TAG, jsonObject.toString());
                EventBusProvider.getInstance().post(new OnInitOrderFinish(false, "Fetch posts success", jsonObject));
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtils.d(TAG, "Fetch Posts failure: " + error);
                EventBusProvider.getInstance().post(new OnFetchPostsFinish(true, "Fetch Posts failure:" + error));
            }
        });
    }

    @Override
    protected void onCancel() {
        //Job超过重试次数或shouldReRunOnThrowable() 返回 false.
        LogUtils.d(TAG, "Fetch Posts Cancelled");
        EventBusProvider.getInstance().post(new OnFetchPostsFinish(true, "job Cancelled"));
    }
}