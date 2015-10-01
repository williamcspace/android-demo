package tequila.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tangxinli.android.tequila.TequilaApplication;
import com.tangxinli.android.tequila.providers.RefWatcherProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by williamc1986 on 7/14/15.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = LogUtils.makeLogTag(BaseFragment.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcherProvider.getInstance(TequilaApplication.getAppContext()).watch(this);
    }
}
