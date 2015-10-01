package tequila.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.OrderConfirmActivity;
import com.tangxinli.android.tequila.adapters.OrderTopicListAdapter;
import com.tangxinli.android.tequila.events.OnInitOrderFinish;
import com.tangxinli.android.tequila.jobs.InitOrderJob;
import com.tangxinli.android.tequila.models.Topic;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.JobManagerProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.PrefUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by williamc1986 on 8/11/15.
 */
public class OrderTopicFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(OrderTopicFragment.class);
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Topic> mTopics = new ArrayList<>();
    private EditText mEditText;
    private String userTopic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_order_topic, container, false);
        initToolbar(view);
        initFragmentView(view);
        return view;
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setNavigationIcon(R.drawable.icon_btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initFragmentView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);

//        mTopics = getTopic(10);
        mAdapter = new OrderTopicListAdapter(mTopics);
        mRecyclerView.setAdapter(mAdapter);

        mEditText = (EditText) view.findViewById(R.id.edit_order_topic);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    updateTopicList(getTopic(count));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                userTopic = s.toString();
            }
        });

        ImageView sendBtn = (ImageView) view.findViewById(R.id.btn_send);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobManagerProvider.getInstance().addJobInBackground(new InitOrderJob("测试我的问题", "2015-8-26", "14:00"));

            }
        });
    }


    private void updateTopicList(List<Topic> topics){
        mTopics.addAll(topics);
        mAdapter.notifyDataSetChanged();
    }

    private List<Topic> getTopic(Integer number){
        List<Topic> topics = new ArrayList<>();
        for (int i = 0; i < number; i++){
            Topic topic = new Topic();
            topics.add(topic);
        }
        return topics;
    }

    public void nextActivity() {
        //下一步选完时间后一起关闭当前activity
        getActivity().startActivity(new Intent(getActivity(), OrderConfirmActivity.class));
        getActivity().finish();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnInitOrderFinish event) {
//        EventBusProvider.getInstance().post(new ToggleLoading(false));

        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
        }

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String order = gson.toJson(event.getExtra());
        LogUtils.d(TAG, order);
        if(PrefUtils.setOrderContent(order)){
            nextActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume");
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "onPause");
        EventBusProvider.getInstance().unregister(this);
    }

}
