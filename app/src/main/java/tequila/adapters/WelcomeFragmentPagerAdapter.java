package tequila.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tangxinli.android.tequila.fragments.WelcomePageFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

public class WelcomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = LogUtils.makeLogTag(WelcomeFragmentPagerAdapter.class);

    final int PAGE_COUNT = 3;
    private Context context;

    public WelcomeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return WelcomePageFragment.newInstance(position + 1);
    }
}
