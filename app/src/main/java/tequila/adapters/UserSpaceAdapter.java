package tequila.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.dao.models.User;
import com.tangxinli.android.tequila.models.UserSpace;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class UserSpaceAdapter extends BaseRecyclerViewAdapter{
    private static final String TAG = LogUtils.makeLogTag(UserSpaceAdapter.class);
    private List<UserSpace> userSpaceList = Collections.emptyList();
    private List<User> userItem = Collections.emptyList();

    public UserSpaceAdapter(List<UserSpace> userSpace, List<User> user) {
        this.userSpaceList = userSpace;
        this.userItem = user;
    }
    @Override
    protected int getHeaderItemCount() {
        return 1;
    }

    @Override
    protected int getFooterItemCount() {
        return 1;
    }

    @Override
    protected int getContentItemCount() {
        return userSpaceList.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_space_header, parent, false);
        return new UserSpaceAdapter.HeaderViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_space_footer, parent, false);
        return new UserSpaceAdapter.FooterViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_space_content, parent, false);
        return new UserSpaceAdapter.ContentViewHolder(view);
    }

    @Override
    protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder, int position) {
        UserSpaceAdapter.HeaderViewHolder cvh = (UserSpaceAdapter.HeaderViewHolder) headerViewHolder;
        User user = userItem.get(position);
        cvh.vNickname.setText(user.getNickname());
        cvh.vEmail.setText(user.getEmail());
        cvh.vPhone.setText(user.getPhone());
    }

    @Override
    protected void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder, int position) {
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {
        UserSpaceAdapter.ContentViewHolder cvh = (UserSpaceAdapter.ContentViewHolder) contentViewHolder;
        UserSpace userSpaceItem = userSpaceList.get(position);
        cvh.setCurrentItem(userSpaceItem);
        cvh.vTitle.setText(userSpaceItem.mTitle);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        protected TextView vNickname;
        protected TextView vEmail;
        protected TextView vPhone;

        public HeaderViewHolder(View v) {
            super(v);
            vNickname = (TextView) v.findViewById(R.id.user_space_header_nickname);
            vEmail = (TextView) v.findViewById(R.id.user_space_header_email);
            vPhone = (TextView) v.findViewById(R.id.user_space_header_phone);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private static final String TAG = ContentViewHolder.class.getSimpleName();

        protected ImageView vIcon;
        protected TextView vTitle;

        private UserSpace mUserSpaceItem;

        public ContentViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.user_space_content_title);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setCurrentItem(UserSpace mUserSpaceItem) {
            this.mUserSpaceItem = mUserSpaceItem;
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
