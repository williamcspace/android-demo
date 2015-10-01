package tequila.jobs;

import com.google.gson.JsonArray;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.tangxinli.android.tequila.events.OnFetchFieldsFinish;
import com.tangxinli.android.tequila.events.ToggleLoading;
import com.tangxinli.android.tequila.models.Helper.FieldListHelper;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by williamc1986 on 7/16/15.
 */
public class FetchFieldsJob extends Job {
    private final String TAG = FetchFieldsJob.class.getSimpleName();

    public FetchFieldsJob() {
        // requireNetwork() 需要联网,
        // groupBy("String"): 请求分类，同个请求不会重复请求
        // persist(): Serialize Job Object 并保存数据库；预设Java解析没办法Serialize当前Job
        super(new Params(Priority.HIGH).requireNetwork().groupBy("fields"));
    }

    @Override
    public void onAdded() {
        // 在这更新UI，Job 已经写入硬盘.
        EventBusProvider.getInstance().post(new ToggleLoading(true));
    }

    @Override
    public void onRun() throws Throwable {
        RestServiceProvider.getTangxlApi().fetchFields(new Callback<JsonArray>() {
            @Override
            public void success(final JsonArray jsonArray, Response response) {
                if (jsonArray.isJsonArray()) {
                    LogUtils.d(TAG, jsonArray.toString());
                    FieldListHelper.getInstance().setList(jsonArray);
                    EventBusProvider.getInstance().post(new OnFetchFieldsFinish(false, jsonArray.toString()));
                }else{
                    EventBusProvider.getInstance().post(new OnFetchFieldsFinish(true, "Server return error"));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                EventBusProvider.getInstance().post(new OnFetchFieldsFinish(true, "Login failure:" + error));
            }
        });
    }

    @Override
    protected void onCancel() {
        //Job超过重试次数或shouldReRunOnThrowable() 返回 false.
        EventBusProvider.getInstance().post(new OnFetchFieldsFinish(true, "job Cancelled"));
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        // onRun 出错. 返回 true 重试， 返回false 停止
        EventBusProvider.getInstance().post(new OnFetchFieldsFinish(true, "loginJobFailed"));
        return false;
    }
}
