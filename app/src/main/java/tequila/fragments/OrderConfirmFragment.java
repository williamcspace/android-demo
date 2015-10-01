package tequila.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.OrderDateTimeActivity;
import com.tangxinli.android.tequila.events.OnCreateOrderFinish;
import com.tangxinli.android.tequila.jobs.CreateOrderJob;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.JobManagerProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.PrefUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;

/**
 * Created by williamc1986 on 8/11/15.
 */
public class OrderConfirmFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(OrderConfirmFragment.class);
    private Toolbar mToolbar;
    private CardView mNextBtn;
    private Button mChangeDateTimeBtn;

    private EditText mPhoneNumber;
    private EditText mContactName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_order_confirm, container, false);
        initToolbar(view);
        initFragmentView(view);
        return view;
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setNavigationIcon(R.drawable.icon_btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initFragmentView(View view) {
        mNextBtn = (CardView) view.findViewById(R.id.btn_confirm);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPaymentDialog();
            }
        });

        mPhoneNumber = (EditText) view.findViewById(R.id.edit_phone_number);
        mContactName = (EditText) view.findViewById(R.id.edit_contact_name);

        mChangeDateTimeBtn = (Button) view.findViewById(R.id.change_datetime);
        mChangeDateTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDateTime();
            }
        });
    }

    public void nextActivity() {
        //下一步选完时间后一起关闭当前activity
//        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    public void changeDateTime(){
        //下一步选完时间后一起关闭当前activity
        getActivity().startActivity(new Intent(getActivity(), OrderDateTimeActivity.class));
        getActivity().finish();
    }

    public void showPaymentDialog() {
        String phoneNumber = mPhoneNumber.getText().toString();
        String contactName = mContactName.getText().toString();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        JsonObject order = gson.fromJson(PrefUtils.getOrderContent(), JsonObject.class);
        order.addProperty("contactName", contactName);
        order.addProperty("contactPhone", phoneNumber);

        JobManagerProvider.getInstance().addJobInBackground(new CreateOrderJob(order));

//        JobManagerProvider.getInstance().addJobInBackground(new createOrderJob());

        PaymentDialogFragment newFragment = new PaymentDialogFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "payment");
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnCreateOrderFinish event) {
        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume");
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "onPause");
        EventBusProvider.getInstance().unregister(this);
    }
}
