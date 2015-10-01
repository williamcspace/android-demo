package tequila.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.ViewPagers.BaseViewPager;
import com.tangxinli.android.tequila.adapters.TabPagerAdapter;
import com.tangxinli.android.tequila.dao.models.Field;
import com.tangxinli.android.tequila.dao.models.FieldDao;
import com.tangxinli.android.tequila.fragments.TabArticleFragment;
import com.tangxinli.android.tequila.providers.DaoProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.PrefUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(MainActivity.class);

    private ImageButton tabHome;
    private ImageButton tabTopic;
    private ImageButton tabOrder;
    private ImageButton tabConvo;
    private ImageButton tabPersonal;
    private Boolean mInit = false;

    private TabPagerAdapter mTabPagerAdapter;
    private BaseViewPager mViewPager;

    private Handler mHandler = new Handler();
    private Runnable initMainView;

    //TODO: remove test code
    private View mSplashView;


    private void initSplashScreen(final View view) {
        if (!mInit) {
            mInit = true;
            mSplashView = getLayoutInflater().inflate(R.layout.splash_activity, null);
            setContentView(mSplashView);

            //TODO: remove test code
            initMainView = new Runnable() {
                @Override
                public void run() {
                    mInit = false;
                    initTabView(view);
                }
            };

            if (!PrefUtils.isWelcomeDone(this)) {
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }

            mHandler.postDelayed(initMainView, 3000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDBData();

        View mLauncherView = getLayoutInflater().inflate(R.layout.activity_launcher, null);
        mTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager = (BaseViewPager) mLauncherView.findViewById(R.id.tab_view_pager);
        mViewPager.setAdapter(mTabPagerAdapter);
//        mViewPager.setPageTransformer(false, new DepthPageTransformer());
//        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mViewPager.setPagingEnabled(false);

        tabHome = (ImageButton) mLauncherView.findViewById(R.id.tab_home_btn);
        tabTopic = (ImageButton) mLauncherView.findViewById(R.id.tab_topic_btn);
        tabOrder = (ImageButton) mLauncherView.findViewById(R.id.tab_order_btn);
        tabConvo = (ImageButton) mLauncherView.findViewById(R.id.tab_convo_btn);
        tabPersonal = (ImageButton) mLauncherView.findViewById(R.id.tab_personal_btn);

        if (savedInstanceState == null){
            initSplashScreen(mLauncherView);
        }else{
            initTabView(mLauncherView);
        }

    }

    public void initDBData() {
        String defaultFields = "[{\"name\":\"全部\",\"migrationId\":0,\"id\":0},{\"name\":\"婚姻家庭\",\"migrationId\":1,\"id\":1},{\"name\":\"亲子教育\",\"migrationId\":2,\"id\":2},{\"name\":\"学习工作\",\"migrationId\":3,\"id\":3},{\"name\":\"情绪情感\",\"migrationId\":4,\"id\":4},{\"name\":\"自我成长\",\"migrationId\":5,\"id\":5},{\"name\":\"心理问题\",\"migrationId\":6,\"id\":6},{\"name\":\"其他\",\"migrationId\":7,\"id\":7}]";

        FieldDao fieldDao = DaoProvider.getSession().getFieldDao();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        Type fieldType = new TypeToken<List<Field>>() {
        }.getType();
        List<Field> fields = gson.fromJson(defaultFields, fieldType);
        fieldDao.insertOrReplaceInTx(fields);
//        EventBusProvider.getInstance().post(new initFieldFinish(false, "init field finished"));
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.addOnPageChangeListener(onPageChangeListener);
        setCurrentTab(mViewPager.getCurrentItem());
    }

    @Override
    public void onPause(){
        super.onPause();
        mViewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //TODO: remove test code
        mHandler.removeCallbacks(initMainView);
    }

    private void initTabView(View view) {
        tabHome.setOnClickListener(onTabClickListener);
        tabTopic.setOnClickListener(onTabClickListener);
        tabConvo.setOnClickListener(onTabClickListener);
        tabPersonal.setOnClickListener(onTabClickListener);

        tabOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), OrderTopicActivity.class));
            }
        });

        setTabInactive();
        tabHome.setSelected(true);

//        loadDefaultContent();
        setContentView(view);
    }

    private void setTabInactive() {
        tabHome.setSelected(false);
        tabTopic.setSelected(false);
        tabConvo.setSelected(false);
        tabPersonal.setSelected(false);
    }

    private void loadDefaultContent() {
        setTabInactive();
        tabHome.setSelected(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.realtabcontent, new TabArticleFragment())
                .commitAllowingStateLoss();

    }

    private void setCurrentTab(int position){
        LogUtils.d(TAG, "position: "+position);
        setTabInactive();
        switch (position) {
            case 0:
                tabHome.setSelected(true);
                break;
            case 1:
                tabTopic.setSelected(true);
                break;
            case 2:
                tabConvo.setSelected(true);
                break;
            case 3:
                tabPersonal.setSelected(true);
                break;
        }
    }


    private ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            setTabInactive();
            switch (position){
                case 0:
                    tabHome.setSelected(true);
                    break;
                case 1:
                    tabTopic.setSelected(true);
                    break;
                case 2:
                    tabConvo.setSelected(true);
                    break;
                case 3:
                    tabPersonal.setSelected(true);
                    break;
            }
        }
    };

    private View.OnClickListener onTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setTabInactive();
            view.setSelected(true);
            switch (view.getId()) {
                case R.id.tab_home_btn:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.realtabcontent, new TabArticleFragment())
//                            .commitAllowingStateLoss();
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.tab_topic_btn:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.realtabcontent, new TabTopicFragment())
//                            .commitAllowingStateLoss();
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.tab_convo_btn:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.realtabcontent, new TabConvoFragment())
//                            .commitAllowingStateLoss();
                    mViewPager.setCurrentItem(2);
                    break;
                case R.id.tab_personal_btn:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.realtabcontent, new TabPersonalFragment())
//                            .commitAllowingStateLoss();
                    mViewPager.setCurrentItem(3);
                    break;
            }
        }
    };
}