package tequila.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.adapters.OrderDateTimeFragmentPagerAdapter;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/24/15.
 */
public class OrderDateTimeFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(OrderDateTimeFragment.class);

    private TextView text;
    private Toolbar mToolbar;

    public OrderDateTimeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_datetime, container, false);

        initToolbar(view);

        //获取ViewPager和绑定PagerAdapter来显示page item
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.datetime_viewpager);
        viewPager.setAdapter(new OrderDateTimeFragmentPagerAdapter(getChildFragmentManager(), this.getActivity()));

        //绑定ViewPager在TabLayout上
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_date);
        tabLayout.setupWithViewPager(viewPager);

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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }
}
