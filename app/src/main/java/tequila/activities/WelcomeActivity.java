package tequila.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.adapters.WelcomeFragmentPagerAdapter;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.PrefUtils;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = LogUtils.makeLogTag(WelcomeActivity.class);

    private static final int NUM_PAGES = 3;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    public final static Integer[] imageResIds = new Integer[]{
            R.drawable.ic_one, R.drawable.ic_two, R.drawable.ic_three
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mPager = (ViewPager) findViewById(R.id.welcome_viewpager);
        mPagerAdapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager(), WelcomeActivity.this);
        mPager.setAdapter(mPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int pageNumber) {
                LogUtils.d(TAG, "Welcome Page " + Integer.toString(pageNumber) + " Selected");

                if (pageNumber == 2) {
                    PrefUtils.markWelcomeDone(WelcomeActivity.this);

                    mPager.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startLauncherActivity();
                        }
                    }, 1000);
                }
            }
        });
    }

    private void startLauncherActivity(){
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        WelcomeActivity.this.finish();
    }
}
