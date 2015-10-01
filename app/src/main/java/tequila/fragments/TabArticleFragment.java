package tequila.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.MainActivity;
import com.tangxinli.android.tequila.adapters.NewArticleListAdapter;
import com.tangxinli.android.tequila.dao.models.Field;
import com.tangxinli.android.tequila.dao.models.Post;
import com.tangxinli.android.tequila.dao.models.PostDao;
import com.tangxinli.android.tequila.events.*;
import com.tangxinli.android.tequila.interfaces.EndlessRecyclerOnScrollListener;
import com.tangxinli.android.tequila.jobs.FetchPostsJob;
import com.tangxinli.android.tequila.models.Helper.FieldListHelper;
import com.tangxinli.android.tequila.providers.DaoProvider;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.JobManagerProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.NetworkUtils;
import com.tangxinli.android.tequila.utils.SnackbarUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;
import com.tangxinli.android.tequila.widgets.DrawShadowFrameLayout;
import de.greenrobot.dao.query.QueryBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TabArticleFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = LogUtils.makeLogTag(TabArticleFragment.class);

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FieldDialogFragment mFieldDialogFragment;
    private List<Post> mPosts = new ArrayList<>();
    private String mPostType = "article";
    private String mPostTag = "";
    private Field mField;
    private ImageButton mFieldBtn;

    private DrawShadowFrameLayout mDrawShadowFrameLayout;


    private EndlessRecyclerOnScrollListener onScrollListener;

    public TabArticleFragment() {
        // TODO Auto-generated constructor stub
    }

    public static TabArticleFragment newInstance() {
        return new TabArticleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogUtils.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_tab_article, container, false);

        mDrawShadowFrameLayout = (DrawShadowFrameLayout) view.findViewById(R.id.main_content);
        mDrawShadowFrameLayout.setShadowVisible(false, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.field_btn).setVisibility(View.VISIBLE);
        MainActivity mActivity = ((MainActivity) getActivity());
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);


        mFieldBtn = (ImageButton) toolbar.findViewById(R.id.field_btn);
        mFieldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFieldDialogFragment = new FieldDialogFragment();
                mFieldDialogFragment.show(getActivity().getSupportFragmentManager(), "fields");
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.cardList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        onScrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreContent();
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

        mRecyclerView.setLayoutManager(mLayoutManager);

//        mArticleList = getDefaultList(20);
        // specify an adapter (see also next example)
        mPosts = loadPosts(loadDataStatus.REFRESH);
        mAdapter = new NewArticleListAdapter(this.getActivity(), mPosts);
        mRecyclerView.setAdapter(mAdapter);

        if (NetworkUtils.isOnline()) {
           ToastUtils.showShort(this.getActivity(), "Network is online");
        }

        fetchRemotePostData();

//        bindActionMenu(view);
//        JobManagerProvider.getInstance().addJobInBackground(new FetchFieldsJob());

//        getArticleList();

        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    private List<Post> getDefaultList(int size) {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Post post = new Post();
            post.setTitle("文章标题-预设-" + i);
            post.setCreatedAt(new Date(2015, 7, 16));
            post.setSummary("近年来，越来越多的人会说自己是“强迫症”，这其实算是一件好事。当大家不再忌讳说一个词，说明对它的偏见越来越少。大家忌讳说...");
            post.setTitleImg("http://www.tangxinli.com/rect/md?q=/cache/b08bd6416ae011746626eb869816b12e.jpg");
            result.add(post);
        }
        return result;
    }

    private List<Post> getLoadmoreList(int size) {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Post post = new Post();
            post.setTitle("文章标题-加载更多-" + i);
            post.setCreatedAt(new Date(2015, 7, 16));
            post.setSummary("近年来，越来越多的人会说自己是“强迫症”，这其实算是一件好事。当大家不再忌讳说一个词，说明对它的偏见越来越少。大家忌讳说...");
            post.setTitleImg("http://www.tangxinli.com/rect/md?q=/cache/b08bd6416ae011746626eb869816b12e.jpg");
            result.add(post);
        }
        return result;
    }

    private List<Post> getRefreshList(int size) {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Post post = new Post();
            post.setTitle("文章标题-下拉刷新-" + i);
            post.setCreatedAt(new Date(2015, 7, 16));
            post.setSummary("近年来，越来越多的人会说自己是“强迫症”，这其实算是一件好事。当大家不再忌讳说一个词，说明对它的偏见越来越少。大家忌讳说...");
            post.setTitleImg("http://www.tangxinli.com/rect/md?q=/cache/b08bd6416ae011746626eb869816b12e.jpg");
            result.add(post);
        }
        return result;
    }

    @Override
    public void onRefresh() {
        refreshContent();
    }

    private void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                mPosts.addAll(0, getRefreshList(20));
                LogUtils.d(TAG, mPosts.toString());
                mAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    private void loadMoreContent() {
        LogUtils.d(TAG, "LoadMore start!!");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtils.d(TAG, "LoadMore Finished!!");
                mPosts.addAll(getLoadmoreList(20));
                LogUtils.d(TAG, mPosts.toString());
                mAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(OnFetchFieldsFinish event) {
        EventBusProvider.getInstance().post(new ToggleLoading(false));
        SnackbarUtils.showLong(getView(), event.getMessage());
//        Toast.makeText(this.getActivity(), event.getMessage(), Toast.LENGTH_LONG).show();
        LogUtils.d(TAG, FieldListHelper.getInstance().getList().toString());
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(ToggleField event) {
        mField = event.getField();
        ToastUtils.showLong(this.getActivity(), "set Field:" + event.getField().getName());
        mFieldDialogFragment.dismiss();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnFetchPostsFinish event) {
        EventBusProvider.getInstance().post(new ToggleLoading(false));

        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
        }

        mPosts = loadPosts(loadDataStatus.REFRESH);
        mAdapter.notifyDataSetChanged();
        ToastUtils.showLong(this.getActivity(), "posts should refresh");
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnMorePostsFinish event) {
        EventBusProvider.getInstance().post(new ToggleLoading(false));

        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
        }

        mPosts.addAll(loadPosts(loadDataStatus.LOADMORE));
        mAdapter.notifyDataSetChanged();

        ToastUtils.showLong(this.getActivity(), "posts should update");
    }

    private List<Post> loadPosts(loadDataStatus status) {
        PostDao postDao = DaoProvider.getSession().getPostDao();
        QueryBuilder<Post> qb = postDao.queryBuilder();

        switch (status) {
            case REFRESH:
                if (!mPostTag.isEmpty()) {
                    qb.where(PostDao.Properties.Type.eq(mPostType),
                            PostDao.Properties.Tags.eq(mPostTag),
                            PostDao.Properties.Deleted.eq(false));
                } else {
                    qb.where(PostDao.Properties.Type.eq(mPostType),
                            PostDao.Properties.Deleted.eq(false));
                }
                break;
            case LOADMORE:
                if (!mPostTag.isEmpty()) {
                    qb.where(PostDao.Properties.Type.eq(mPostType),
                            PostDao.Properties.Tags.eq(mPostTag),
                            PostDao.Properties.Deleted.eq(false),
                            PostDao.Properties.Type.lt(mPosts.get(mPosts.size() - 1).getId()));
                } else {
                    qb.where(PostDao.Properties.Type.eq(mPostType),
                            PostDao.Properties.Deleted.eq(false),
                            PostDao.Properties.Type.lt(mPosts.get(mPosts.size() - 1).getId()));
                }
                break;
        }
        qb.orderDesc(PostDao.Properties.Id);
        qb.limit(Config.PAGER_SIZE);
        return qb.list();
    }

    private void fetchRemotePostData() {
        JobManagerProvider.getInstance().addJobInBackground(new FetchPostsJob(mPostType, mPostTag));
    }

    private enum loadDataStatus {
        REFRESH, REFRESH_TAG, LOADMORE, LOADMORE_TAG
    }
}
