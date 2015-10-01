package tequila.activities;

import android.os.Bundle;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.TweetFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/23/15.
 */
public class CallConfirmActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(CallConfirmActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        if (savedInstanceState == null) {
            TweetFragment fragment = new TweetFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, "TopicDetail")
                    .commit();
        }
    }
}
