package williamcspace.android.demo.app.backup;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tangxinli.android.tequila.R;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = TabFragmentPagerAdapter.class.getSimpleName();

    final int PAGE_COUNT = 4;

    private String tabTitles[];

    public TabFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabTitles = new String[]{
                context.getString(R.string.title_section1),
                context.getString(R.string.title_section2),
                context.getString(R.string.title_section3),
                context.getString(R.string.title_section4)};
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt(TabPageFragment.POSITION_KEY, position + 1);
        return TabPageFragment.newInstance(args);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}