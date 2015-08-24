package williamcspace.android.demo.app.backup;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.BaseFragment;

public class TabHomePagerFragment extends BaseFragment {
    public static final String TAG = TabHomePagerFragment.class.getSimpleName();

    private TextView text;

    public TabHomePagerFragment() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_tab_home_pager, container,false);

        //获取ViewPager和绑定PagerAdapter来显示page item
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getChildFragmentManager(), this.getActivity()));

        //绑定ViewPager在TabLayout上
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * TODO: 每个页面需要实现该方法返回一个该页面所对应的资源ID
     *
     * @return 页面资源ID
     */
    @Override
    public int getLayoutId() {
        return 0;
    }

}
