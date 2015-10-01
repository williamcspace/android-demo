package tequila.jobs;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.tangxinli.android.tequila.events.OnRequestUnifiedOrderFinish;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.rest.models.WXUnifiedOrderRequest;
import com.tangxinli.android.tequila.rest.models.WXUnifiedOrderResponse;
import com.tangxinli.android.tequila.utils.LogUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class RequestWXUnifiedOrderJob extends Job {
    public final String TAG = LogUtils.makeLogTag(RequestWXUnifiedOrderJob.class);

    private WXUnifiedOrderRequest mBody;
    public RequestWXUnifiedOrderJob(WXUnifiedOrderRequest body) {
        super(new Params(Priority.HIGH).requireNetwork().groupBy("posts"));
        mBody = body;
    }

    @Override
    public void onAdded() {
        // 在这更新UI，Job 已经写入硬盘.
        LogUtils.d(TAG, "Fetch Posts Job Added");
    }

    @Override
    public void onRun() throws Throwable {
        LogUtils.d(TAG, "Run Fetch Posts request now");
        RestServiceProvider.getWXPayApi().requestWXUnifiedOrder(mBody, new Callback<WXUnifiedOrderResponse>() {
            @Override
            public void success(final WXUnifiedOrderResponse WXUnifiedOrderResponse, Response response) {
                LogUtils.d(TAG, WXUnifiedOrderResponse.toString());
                EventBusProvider.getInstance().post(new OnRequestUnifiedOrderFinish(false, "Fetch posts success", WXUnifiedOrderResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtils.d(TAG, "Fetch Posts failure: " + error);
                EventBusProvider.getInstance().post(new OnRequestUnifiedOrderFinish(true, "Fetch Posts failure:" + error));
            }
        });
    }

    @Override
    protected void onCancel() {
        //Job超过重试次数或shouldReRunOnThrowable() 返回 false.
        LogUtils.d(TAG, "Fetch Posts Cancelled");
        EventBusProvider.getInstance().post(new OnRequestUnifiedOrderFinish(true, "job Cancelled"));
    }
}