package tequila.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.MainActivity;
import com.tangxinli.android.tequila.adapters.TopicListAdapter;
import com.tangxinli.android.tequila.dao.models.Channel;
import com.tangxinli.android.tequila.dao.models.ChannelDao;
import com.tangxinli.android.tequila.events.OnFetchChannelsFinish;
import com.tangxinli.android.tequila.events.ToggleLoading;
import com.tangxinli.android.tequila.interfaces.EndlessRecyclerOnScrollListener;
import com.tangxinli.android.tequila.jobs.FetchChannelsJob;
import com.tangxinli.android.tequila.providers.DaoProvider;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.JobManagerProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;
import com.tangxinli.android.tequila.widgets.DrawShadowFrameLayout;
import de.greenrobot.dao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class TabTopicFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(TabTopicFragment.class);

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Channel> mChannels = new ArrayList<>();
    private String mChannelTag = "";

    private EndlessRecyclerOnScrollListener onScrollListener;
    private DrawShadowFrameLayout mDrawShadowFrameLayout;

    public TabTopicFragment() {
        // TODO Auto-generated constructor stub
    }

    public static TabTopicFragment newInstance() {
        return new TabTopicFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_tab_topic, container, false);

        mDrawShadowFrameLayout = (DrawShadowFrameLayout) view.findViewById(R.id.main_content);
        mDrawShadowFrameLayout.setShadowVisible(false, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.field_btn).setVisibility(View.VISIBLE);
        MainActivity mActivity = ((MainActivity) getActivity());
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        LogUtils.d(TAG, "onCreateView");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.topic_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                JobManagerProvider.getInstance().addJobInBackground(new FetchChannelsJob());
            }
        });

        onScrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                Toast.makeText(getActivity(), "start loading more", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    mDrawShadowFrameLayout.setShadowVisible(false, false);
                } else {
                    mDrawShadowFrameLayout.setShadowVisible(true, false);
                }
            }
        };

        JobManagerProvider.getInstance().addJobInBackground(new FetchChannelsJob());
//        mChannels = getContentList(11);

        mAdapter = new TopicListAdapter(mChannels);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume");
        mRecyclerView.addOnScrollListener(onScrollListener);
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "onPause");
        mRecyclerView.removeOnScrollListener(onScrollListener);
        EventBusProvider.getInstance().unregister(this);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnFetchChannelsFinish event) {
        EventBusProvider.getInstance().post(new ToggleLoading(false));

        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch channel error,will load local data");
        }

        refreshChannels();
    }

    private void refreshChannels() {
        ChannelDao channelDao = DaoProvider.getSession().getChannelDao();
        QueryBuilder<Channel> qb = channelDao.queryBuilder();

        if (!mChannelTag.isEmpty()) {
            qb.where(ChannelDao.Properties.Deleted.eq(false));
        } else {
            qb.where(ChannelDao.Properties.Deleted.eq(false));
        }

        qb.orderDesc(ChannelDao.Properties.Id);
        qb.limit(Config.PAGER_SIZE);

        mChannels.clear();
        mChannels.addAll(qb.list());
        mAdapter.notifyDataSetChanged();

        ToastUtils.showLong(this.getActivity(), "channels should refresh");
    }


}
