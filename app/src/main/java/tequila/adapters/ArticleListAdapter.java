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
import com.tangxinli.android.tequila.activities.ArticleActivity;
import com.tangxinli.android.tequila.models.PostModel;
import com.tangxinli.android.tequila.utils.LogUtils;
import com.tangxinli.android.tequila.utils.SnackbarUtils;

import java.util.Collections;
import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {
    private static final String TAG = LogUtils.makeLogTag(ArticleListAdapter.class);
    private List<PostModel> posts = Collections.emptyList();

    public ArticleListAdapter(List<PostModel> posts) {
        this.posts = posts;
    }

    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article_list, viewGroup, false);
        return new ArticleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleListAdapter.ViewHolder holder, int position) {
        PostModel post = posts.get(position);
        holder.setCurrentItem(post);
        holder.vTitle.setText(post.title);
        holder.vDate.setText(post.createdAt.toString());
        holder.vDescription.setText(post.summary);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        protected ImageView vKeyVisual;
        protected TextView vTitle;
        protected TextView vDate;
        protected TextView vDescription;

        private PostModel mPost;

        public ViewHolder(View v) {
            super(v);
            vKeyVisual = (ImageView) v.findViewById(R.id.article_key_visual);
            vTitle = (TextView) v.findViewById(R.id.article_title);
            vDate = (TextView) v.findViewById(R.id.article_date);
            vDescription = (TextView) v.findViewById(R.id.article_description);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setCurrentItem(PostModel post) {
            this.mPost = post;
        }

        @Override
        public void onClick(View v) {
            SnackbarUtils.showShort(v, "position = " + getAdapterPosition());
//            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item clicked");

            Gson gson = new Gson();
            String curItem = gson.toJson(mPost);
            Intent intent = new Intent(v.getContext(), ArticleActivity.class);
            intent.putExtra("data", curItem);
            v.getContext().startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            SnackbarUtils.showShort(v, "position = " + getAdapterPosition());
//            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item long clicked");
            return true;
        }
    }
}
