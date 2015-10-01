package tequila.activities.test;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.activities.BaseActivity;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by williamc1986 on 8/27/15.
 */
public class RecyclerTestActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(RecyclerTestActivity.class);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<String> mLists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_test_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mArticleList = getDefaultList(20);
        // specify an adapter (see also next example)
        mLists = Arrays.asList("a1", "a2", "a3", "b4", "b5", "b6", "b7", "b8", "c9", "c10", "c11", "c12", "c13", "c14", "c15", "c16", "c17", "c18", "d19", "d20");
        mAdapter = new RecyclerTestAdapter(mLists);
        mRecyclerView.setAdapter(mAdapter);
    }

    public static class RecyclerTestAdapter extends RecyclerView.Adapter<RecyclerTestAdapter.ViewHolder> {
        private List<String> mStrings = new ArrayList<>();

        public RecyclerTestAdapter(List<String> strings) {
            this.mStrings = strings;
        }

        @Override
        public RecyclerTestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            switch(viewType){
                case 0:
                    LogUtils.d(TAG, "case 0 viewType: "+ viewType);
                    return new RecyclerTestAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_item_text, viewGroup, false));
                case 1:
                    LogUtils.d(TAG, "case 1 viewType: " + viewType);
                    return new RecyclerTestAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_item_text_header, viewGroup, false));
                default:
                    LogUtils.d(TAG, "deafult viewType: " + viewType);
                    return new RecyclerTestAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_item_text, viewGroup, false));
            }
        }

        @Override
        public int getItemViewType(int position){
            return (Math.random() <= 0.5)? 1 : 0;
        }

        @Override
        public void onBindViewHolder(RecyclerTestAdapter.ViewHolder holder, int position) {
            String string = mStrings.get(position);
            holder.mText.setText(string);
        }

        @Override
        public int getItemCount() { return mStrings.size(); }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mText;
            public ViewHolder(View v) { super(v); mText = (TextView) v.findViewById(R.id.test_recycler_view_item);}
        }
    }
}
