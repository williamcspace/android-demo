package tequila.activities;

import android.os.Bundle;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.TweetsFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/23/15.
 */
public class TweetsActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(TweetsActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        if (savedInstanceState == null) {
            TweetsFragment fragment = new TweetsFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, "Tweets")
                    .commit();
        }
    }
}
