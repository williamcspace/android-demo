package tequila.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.dao.models.Field;
import com.tangxinli.android.tequila.events.ToggleField;
import com.tangxinli.android.tequila.providers.EventBusProvider;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.List;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class FieldListAdapter extends BaseRecyclerViewAdapter {
    private static final String TAG = LogUtils.makeLogTag(FieldListAdapter.class);
    private List<Field> mFields;
    private Context mContext;

    public FieldListAdapter(Context context, List<Field> fields) {
        mFields = fields;
        mContext = context;
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
        return mFields.size();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_field_list, parent, false);
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
        Field field = mFields.get(position);
        cvh.setCurrentItem(field);
        cvh.vTitle.setText(field.getName());
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

        private Field mField;

        public ContentViewHolder(View v) {
            super(v);
            vKeyVisual = (ImageView) v.findViewById(R.id.field_icon);
            vTitle = (TextView) v.findViewById(R.id.field_title);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setCurrentItem(Field field) {
            this.mField = field;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item clicked");
            EventBusProvider.getInstance().post(new ToggleField(mField));
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "Log View item long clicked");
            return true;
        }
    }
}
