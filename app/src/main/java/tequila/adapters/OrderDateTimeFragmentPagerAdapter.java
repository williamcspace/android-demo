package tequila.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tangxinli.android.tequila.fragments.DateTimePageFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

public class OrderDateTimeFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = LogUtils.makeLogTag(OrderDateTimeFragmentPagerAdapter.class);

    final int PAGE_COUNT = 5;

    private String tabTitles[] = new String[]{"8月1号", "8月2号", "8月3号", "8月4号", "8月5号",};

    public OrderDateTimeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt(DateTimePageFragment.POSITION_KEY, position + 1);
        return DateTimePageFragment.newInstance(args);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}