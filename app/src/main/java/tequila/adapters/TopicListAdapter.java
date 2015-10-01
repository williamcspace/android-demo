package tequila.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.TweetsActivity;
import com.tangxinli.android.tequila.dao.models.Channel;
import com.tangxinli.android.tequila.events.StartActivity;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.ToastUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class TopicListAdapter extends BaseRecyclerViewAdapter{
    private static final String TAG = LogUtils.makeLogTag(TopicListAdapter.class);
    private List<Channel> mChannels = Collections.emptyList();

    public TopicListAdapter(List<Channel> channels) {
        mChannels = channels;
    }

    @Override
    protected int getHeaderItemCount() {
        return 0;
    }

    @Override
    protected int getFooterItemCount() {
        return 0;
    }

    @Override
    protected int getContentItemCount() {
        return mChannels.size();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_list, parent, false);
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
        Channel channel = mChannels.get(position);
        cvh.setCurrentItem(channel);
        cvh.vTitle.setText(channel.getName());
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
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
        private static final String TAG = ContentViewHolder.class.getSimpleName();

        protected ImageView vKeyVisual;
        protected TextView vTitle;

        private Channel mTopic;

        public ContentViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.topic_list_title);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setCurrentItem(Channel channel) {
            mTopic = channel;
        }

        @Override
        public void onClick(View v) {
            ToastUtils.showShort(v.getContext(), "position = " + getAdapterPosition());
            LogUtils.d(TAG, "Log View item clicked");

            Gson gson = new Gson();
            String curItem = gson.toJson(mTopic);
            Intent intent = new Intent(v.getContext(), TweetsActivity.class);
            intent.putExtra("data", curItem);
            EventBusProvider.getInstance().post(new StartActivity(false, intent));
        }

        @Override
        public boolean onLongClick(View v) {
            ToastUtils.showShort(v.getContext(), "position = " + getAdapterPosition());
            LogUtils.d(TAG, "Log View item long clicked");
            return true;
        }
    }
}
