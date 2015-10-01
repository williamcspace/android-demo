package tequila.activities;

import android.os.Bundle;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.OrderTopicFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/23/15.
 */
public class OrderTopicActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(OrderTopicActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_topic);

        if (savedInstanceState == null) {
            OrderTopicFragment fragment = new OrderTopicFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, "OrderTopic")
                    .commit();
        }
    }
}
