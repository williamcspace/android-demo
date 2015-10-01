package tequila.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tangxinli.android.tequila.fragments.*;

/**
 * Created by williamc1986 on 8/7/15.
 */
public class SwipeToFinishAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;

    private Context mContext;

    public SwipeToFinishAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return SwipeToFinishFragment.newInstance();
        if (position == 1) return ArticleFragment.newInstance(((Activity) mContext).getIntent().getExtras());

        return new Fragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}