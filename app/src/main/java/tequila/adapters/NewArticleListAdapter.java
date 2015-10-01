package tequila.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.ArticleActivity;
import com.tangxinli.android.tequila.dao.models.Post;
import com.tangxinli.android.tequila.events.StartActivity;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class NewArticleListAdapter extends BaseRecyclerViewAdapter {
    private static final String TAG = LogUtils.makeLogTag(ArticleListAdapter.class);
    private List<Post> posts = Collections.emptyList();
    private Context mContext;

    public NewArticleListAdapter(Context context, List<Post> posts) {
        this.posts = posts;
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
        return posts.size();
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
        Post post = posts.get(position);
        cvh.setCurrentItem(post);
        cvh.vTitle.setText(post.getTitle());
        cvh.vDate.setText(post.getCreatedAt().toString());
        cvh.vDescription.setText(post.getSummary());
        Picasso.with(mContext)
                .load(Config.TXL_API_URL + post.getTitleImg())
                .placeholder(R.drawable.ic_one)
                .error(R.drawable.ic_two)
                .into(cvh.vKeyVisual);
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

        protected ImageView vKeyVisual;
        protected TextView vTitle;
        protected TextView vDate;
        protected TextView vDescription;

        private Post mPost;

        public ContentViewHolder(View v) {
            super(v);
            vKeyVisual = (ImageView) v.findViewById(R.id.article_key_visual);
            vTitle = (TextView) v.findViewById(R.id.article_title);
            vDate = (TextView) v.findViewById(R.id.article_date);
            vDescription = (TextView) v.findViewById(R.id.article_description);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setCurrentItem(Post post) {
            this.mPost = post;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item clicked");

            Gson gson = new Gson();
            String curItem = gson.toJson(mPost);
            Intent intent = new Intent(v.getContext(), ArticleActivity.class);
            intent.putExtra("data", curItem);
            EventBusProvider.getInstance().post(new StartActivity(false, intent));
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item long clicked");
            return true;
        }
    }
}
