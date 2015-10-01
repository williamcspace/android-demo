package tequila.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.ChatActivity;
import com.tangxinli.android.tequila.activities.MainActivity;
import com.tangxinli.android.tequila.events.OnFetchOrdersFinish;
import com.tangxinli.android.tequila.jobs.FetchOrdersJob;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.JobManagerProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;

public class TabConvoFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(TabConvoFragment.class);

    private TextView text;
    private Button mChatButton;

    public TabConvoFragment() {
        // TODO Auto-generated constructor stub
    }

    public static TabConvoFragment newInstance() {
        return new TabConvoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        JobManagerProvider.getInstance().addJobInBackground(new FetchOrdersJob());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab_convo, null);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity mActivity = ((MainActivity) getActivity());
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        mChatButton = (Button) view.findViewById(R.id.btnChatActivity);
        mChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TabConvoFragment.this.getActivity(), ChatActivity.class));
            }
        });

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
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "onPause");
        EventBusProvider.getInstance().unregister(this);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnFetchOrdersFinish event) {
        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
        }
    }

}
