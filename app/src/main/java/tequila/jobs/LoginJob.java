package tequila.jobs;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.tangxinli.android.tequila.dao.models.User;
import com.tangxinli.android.tequila.events.LoginFinish;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.rest.RestServiceProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class LoginJob extends Job {

    private final String TAG = "LoginJob";
    private JsonObject loginInfo;

    public LoginJob(JsonObject loginInfo) {
        // requireNetwork() 需要联网,
        // groupBy("String"): 请求分类，同个请求不会重复请求
        // persist(): Serialize Job Object 并保存数据库；预设Java解析没办法Serialize当前Job
        super(new Params(Priority.HIGH).requireNetwork().groupBy("login"));
        this.loginInfo = loginInfo;
    }

    @Override
    public void onAdded() {
        // 在这更新UI，Job 已经写入硬盘.
        LogUtils.d(TAG, "Login Job Added");
    }

    @Override
    public void onRun() throws Throwable {
        LogUtils.d(TAG, "Run login request now");
        RestServiceProvider.getTangxlApi().login(loginInfo, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                if (jsonObject.has("error")) {
                    EventBusProvider.getInstance().post(new LoginFinish(true, "Login failure"));
                    LogUtils.d(TAG, "Login response has error");
                    return;
                }

//                if (jsonObject.has("user")) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    User user = gson.fromJson(jsonObject.getAsJsonObject("user"), User.class);
//                    LogUtils.d(TAG,"Gson serializes object: " + user.getNickname());
//
//                    UserModel userModel = new UserModel(jsonObject.getAsJsonObject("user"));
//                    userModel.save();
//                    LogUtils.d(TAG, userModel.getUser().toString());
                    EventBusProvider.getInstance().post(new LoginFinish(false, "success"));
////                    LogUtils.d(TAG, "Login response success");
//                }
                }

                @Override
                public void failure (RetrofitError error){
                    LogUtils.d(TAG, "LoginTask failure: " + error);
                    EventBusProvider.getInstance().post(new LoginFinish(true, "Login failure:" + error));
                }
            }

            );
        }

        @Override
        protected void onCancel () {
            //Job超过重试次数或shouldReRunOnThrowable() 返回 false.
            LogUtils.d(TAG, "Login Job Cancelled");
            EventBusProvider.getInstance().post(new LoginFinish(true, "job Cancelled"));
        }
    }