package tequila.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.JsonObject;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.TweetActivity;
import com.tangxinli.android.tequila.events.OnCreateTweetFinish;
import com.tangxinli.android.tequila.events.OnFetchTweetsFinish;
import com.tangxinli.android.tequila.jobs.CreateTweetJob;
import com.tangxinli.android.tequila.jobs.FetchTweetsJob;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.providers.JobManagerProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;

/**
 * Created by williamc1986 on 7/30/15.
 */
public class TweetsFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(TweetsFragment.class);

    private Toolbar mToolbar;

    private TextView mTextView;
    private EditText mEditText;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JobManagerProvider.getInstance().addJobInBackground(new FetchTweetsJob("1"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tweets, container, false);
        initToolbar(view);

        mTextView = (TextView) view.findViewById(R.id.tweet_content);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TweetActivity.class));
            }
        });

        mEditText = (EditText) view.findViewById(R.id.edit_tweet);

        mImageView = (ImageView) view.findViewById(R.id.btn_send_tweet);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTweet();
            }
        });

        return view;
    }

    private void createNewTweet(){
        String tweetContent = mEditText.getText().toString();
        JsonObject data = new JsonObject();
        data.addProperty("message", tweetContent);
        JobManagerProvider.getInstance().addJobInBackground(new CreateTweetJob("1", data));
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

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnCreateTweetFinish event) {
        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(OnFetchTweetsFinish event) {
        if (event.hasError()) {
            ToastUtils.showLong(this.getActivity(), "fetch post error,will load local data");
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
