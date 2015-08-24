package williamcspace.android.demo.app.backup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.BaseActivity;
import com.tangxinli.android.tequila.common.service.JobManagerProvider;
import com.tangxinli.android.tequila.events.LoginFinish;
import com.tangxinli.android.tequila.jobs.LoginJob;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.wxapi.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.message.PushAgent;

public class LoginTestActivity extends BaseActivity {
    private static final String TAG = LoginTestActivity.class.getSimpleName();

    protected EditText mEditUsername;
    protected EditText mEditPassword;
    protected TextView mErrorMessage;
    protected String mUsername;
    protected String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        Intent intent = getIntent();
        String value2 = intent.getStringExtra("testname2");
        LogUtils.d(TAG, "get intent extra: " + value2);

        PushAgent.getInstance(this).onAppStart();

        mErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);
        mEditUsername = (EditText) findViewById(R.id.etUsername);
        mEditPassword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG, "ApiTestActivity onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "ApiTestActivity onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "ApiTestActivity onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d(TAG, "ApiTestActivity onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d(TAG, "ApiTestActivity OnRestart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "ApiTestActivity onDestory");
    }

    public void login(View view) {
        mUsername = mEditUsername.getText().toString();
        mPassword = mEditPassword.getText().toString();
        if (TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)) {
            mErrorMessage.setText("请输入用户名和密码");
        } else {
            LogUtils.d(TAG, "Logging in");
            JsonObject jsonBody = new JsonObject();
            try {
                jsonBody.addProperty("username", mUsername);
                jsonBody.addProperty("password", mPassword);
            } catch (JsonParseException e) {
                e.printStackTrace();
            }

            JobManagerProvider.getInstance().addJobInBackground(new LoginJob(jsonBody));
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(LoginFinish event) {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
        if (!event.hasError()){
            startActivity(new Intent(LoginTestActivity.this, BackupMainActivity.class));
            LoginTestActivity.this.finish();
        }
    }

    public void registerAccount(View view) {
        Intent intent = new Intent(LoginTestActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void testApi(View view) {
        Intent intent = new Intent(LoginTestActivity.this, ApiTestActivity.class);
        startActivity(intent);
    }

    public void launchWeiXinLogin(View view) {
        // send oauth request
        IWXAPI api = WXAPIFactory.createWXAPI(this, Config.WX_APP_ID, true);
        api.registerApp(Config.WX_APP_ID);

        final SendAuth.Req req = new SendAuth.Req();
        req.scope = Constants.SNSAPI_USERINFO;
        req.state = "tequila_login";
        api.sendReq(req);
    }
}
