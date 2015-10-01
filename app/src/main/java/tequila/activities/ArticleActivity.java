package tequila.activities;

import android.os.Bundle;
import android.view.View;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.ViewPagers.BaseViewPager;
import com.tangxinli.android.tequila.ViewPagers.SwipeToFinishViewPager;
import com.tangxinli.android.tequila.adapters.SwipeToFinishAdapter;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/16/15.
 */
public class ArticleActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(ArticleActivity.class);

    private SwipeToFinishAdapter mSwipeAdapter;
    private BaseViewPager mSwipeViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

//        ArticleFragment fragment = new ArticleFragment();
//        fragment.setArguments(getIntent().getExtras());

        mSwipeViewPager = (BaseViewPager) findViewById(R.id.swipe_to_finish_view_pager);
        mSwipeAdapter = new SwipeToFinishAdapter(this, getSupportFragmentManager());
        mSwipeViewPager.setAdapter(mSwipeAdapter);
        mSwipeViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        if (savedInstanceState == null) {
//            ArticleDetailFragment fragment = new ArticleDetailFragment();
//            fragment.setArguments(getIntent().getExtras());
//
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fragment,"ArticleDetail")
//                    .commit();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mSwipeViewPager.setCurrentItem(1);
        mSwipeViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSwipeViewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    private SwipeToFinishViewPager.SimpleOnPageChangeListener onPageChangeListener = new SwipeToFinishViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    finish();
                    break;
            }
        }
    };
}
