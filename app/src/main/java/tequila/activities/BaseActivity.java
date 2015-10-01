package tequila.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import com.tangxinli.android.tequila.TequilaApplication;
import com.tangxinli.android.tequila.events.StartActivity;
import com.tangxinli.android.tequila.events.ToggleLoading;
import com.tangxinli.android.tequila.fragments.LoadingDialogFragment;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.PushAgentHelper;
import com.tangxinli.android.tequila.providers.RefWatcherProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.PrefUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class BaseActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    private static final String TAG = LogUtils.makeLogTag(BaseActivity.class);

    private State state;
    private DialogFragment dialog;

    private enum State {
        CONSTRUCTED,
        CREATED,
        STARTED,
        RESUMED,
        PAUSED,
        STOPPED,
        RESTARTED,
        DESTROYED
    }

    public BaseActivity() {
        state = State.CONSTRUCTED;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(this);
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setDisplayHomeAsUpEnabled(true);
//        }
        state = State.CREATED;
        PushAgentHelper.getInstance(this).enable();
        PushAgentHelper.getInstance(this).onAppStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG, "onStart: " + this.getClass().getSimpleName());
        state = State.STARTED;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume: " + this.getClass().getSimpleName());
        state = State.RESUMED;
        MobclickAgent.onResume(this);
        EventBusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = State.PAUSED;
        MobclickAgent.onPause(this);
        EventBusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        state = State.STOPPED;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        state = State.RESTARTED;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state = State.DESTROYED;
        RefWatcherProvider.getInstance(TequilaApplication.getAppContext()).watch(this);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PrefUtils.PREF_APP_VERSION)) {
            LogUtils.d(TAG, "数据库重置");
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(ToggleLoading event) {
        if (event.isShown()) {
            dialog = new LoadingDialogFragment();
            dialog.show(getSupportFragmentManager(), "LoadingDialogFragment");
        } else if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(StartActivity event) {
        EventBusProvider.getInstance().post(new ToggleLoading(false));

        if (event.hasError()) {
            ToastUtils.showLong(this, "error");
        }

        startActivity(event.getIntent());
    }
}
