package tequila.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.models.Topic;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.List;

public class OrderTopicListAdapter extends RecyclerView.Adapter<OrderTopicListAdapter.ViewHolder> {
    private static final String TAG = LogUtils.makeLogTag(OrderTopicListAdapter.class);
    private List<Topic> mTopics;

    public OrderTopicListAdapter(List<Topic> topics) {
        this.mTopics = topics;
    }

    @Override
    public OrderTopicListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_topic, viewGroup, false);
        return new OrderTopicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderTopicListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }
}
