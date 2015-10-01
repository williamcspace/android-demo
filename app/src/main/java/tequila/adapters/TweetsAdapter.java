package tequila.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.dao.models.Tweet;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class TweetsAdapter extends BaseRecyclerViewAdapter {
    private static final String TAG = LogUtils.makeLogTag(TweetsAdapter.class);
    private List<Tweet> mTweets = Collections.emptyList();
    private Context mContext;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.mTweets = tweets;
        this.mContext = context;
    }

    @Override
    protected int getHeaderItemCount() {
        return 0;
    }

    @Override
    protected int getFooterItemCount() {
        return 1;
    }

    @Override
    protected int getContentItemCount() {
        return mTweets.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_header, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_loadmore_footer, parent, false);
        return new FooterViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_list, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder, int position) {
    }

    @Override
    protected void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder, int position) {
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {
        ContentViewHolder cvh = (ContentViewHolder) contentViewHolder;
        Tweet tweet = mTweets.get(position);
        cvh.setCurrentItem(tweet);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        protected TextView vDescription;

        private Tweet tweet;

        public ContentViewHolder(View v) {
            super(v);
            vDescription = (TextView) v.findViewById(R.id.article_description);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setCurrentItem(Tweet tweet) {
            this.tweet = tweet;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item clicked");
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item long clicked");
            return true;
        }
    }
}
