package williamcspace.android.demo.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by williamc1986 on 8/18/15.
 */
public class MainFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDLDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private MyAdapter mMyAdapter;

    String TITLES[] = {"Home", "Events", "Mail", "Shop", "Travel"};
    int ICONS[] = {
            R.drawable.ic_home,
            R.drawable.ic_events,
            R.drawable.ic_mail,
            R.drawable.ic_shop,
            R.drawable.ic_travel};
    String NAME = "Akash Bangad";
    String EMAIL = "akash.bangad@android4devs.com";
    int PROFILE = R.drawable.ic_launcher;

    public MainFragment() {
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(getActivity(), mDLDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // 使用自定义 toolbar 取代传统 actionbar
        toolbar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.ColorAccent));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.ColorAccent));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mMyAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
        mRecyclerView.setAdapter(mMyAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDLDrawer = (DrawerLayout) rootView.findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDLDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDLDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        return rootView;
    }
}