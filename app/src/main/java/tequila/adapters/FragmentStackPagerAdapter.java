package tequila.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.List;

/**
 * Created by williamc1986 on 8/7/15.
 */
public class FragmentStackPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = LogUtils.makeLogTag(FragmentStackPagerAdapter.class);

    private List<Fragment> mFragments;
    private Context mContext;

    public FragmentStackPagerAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mContext = context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void push(Fragment fragment){
        mFragments.add(fragment);
    }

    public void pop(){
        if(mFragments.size() > 1){
            mFragments.remove(mFragments.size()-1);
        }
    }
}