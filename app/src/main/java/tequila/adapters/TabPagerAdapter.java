package tequila.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tangxinli.android.tequila.fragments.TabArticleFragment;
import com.tangxinli.android.tequila.fragments.TabConvoFragment;
import com.tangxinli.android.tequila.fragments.TabPersonalFragment;
import com.tangxinli.android.tequila.fragments.TabTopicFragment;

/**
 * Created by williamc1986 on 8/7/15.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 4;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return TabArticleFragment.newInstance();
        if (position == 1) return TabTopicFragment.newInstance();
        if (position == 2) return TabConvoFragment.newInstance();
        if (position == 3) return TabPersonalFragment.newInstance();
        return new Fragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}