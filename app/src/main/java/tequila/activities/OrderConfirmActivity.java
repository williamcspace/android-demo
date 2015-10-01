package tequila.activities;

import android.os.Bundle;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.OrderConfirmFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/23/15.
 */
public class OrderConfirmActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(OrderConfirmActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        if (savedInstanceState == null) {
            OrderConfirmFragment fragment = new OrderConfirmFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, "OrderTopic")
                    .commit();
        }
    }
}
