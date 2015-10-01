package tequila.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.OrderDateTimeFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/23/15.
 */
public class OrderDateTimeActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(OrderDateTimeActivity.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_datetime);

        if (savedInstanceState == null) {
            OrderDateTimeFragment fragment = new OrderDateTimeFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.order_datetime_container, fragment, "OrderDateTime")
                    .commit();
        }
    }

    public void nextActivity(View v) {
//        setResult(2);
        startActivity(new Intent(this, OrderConfirmActivity.class));
        finish();
    }
}
