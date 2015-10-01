package tequila.jobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.tangxinli.android.tequila.dao.models.Post;
import com.tangxinli.android.tequila.dao.models.PostDao;
import com.tangxinli.android.tequila.events.OnFetchPostsFinish;
import com.tangxinli.android.tequila.events.ToggleLoading;
import com.tangxinli.android.tequila.providers.DaoProvider;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class FetchPostsJob extends Job {
    public final String TAG = LogUtils.makeLogTag(FetchPostsJob.class);

    private String mPostType;
    private String mPostTag;

    public FetchPostsJob(String postType, String postTag) {
        // requireNetwork() 需要联网,
        // groupBy("String"): 请求分类，同个请求不会重复请求
        // persist(): Serialize Job Object 并保存数据库；预设Java解析没办法Serialize当前Job
        super(new Params(Priority.HIGH).requireNetwork().groupBy("posts"));
        mPostType = postType;
        mPostTag = postTag;
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
        RestServiceProvider.getTangxlApi().fetchPosts(mPostType, mPostTag, new Callback<JsonArray>() {
            @Override
            public void success(final JsonArray jsonArray, Response response) {
                LogUtils.d(TAG, jsonArray.toString());

                if (jsonArray.size() > 0) {
                    PostDao postDao = DaoProvider.getSession().getPostDao();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    Type articleType = new TypeToken<List<Post>>() {
                    }.getType();
                    List<Post> mPosts = gson.fromJson(jsonArray, articleType);
                    postDao.insertOrReplaceInTx(mPosts);
                    EventBusProvider.getInstance().post(new OnFetchPostsFinish(false, "Fetch posts success", jsonArray));
                }
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