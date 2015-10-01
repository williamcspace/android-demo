package tequila.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pkmmte.view.CircularImageView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.models.ChatTest;

import java.util.List;

/**
 * Created by williamc1986 on 8/7/15.
 */
public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ChatTest> mChatTests;

    public ChatListAdapter(Context context, List<ChatTest> chatTests){
        mContext = context;
        mChatTests = chatTests;
    }

    @Override
    public int getItemViewType(int position) {
        ChatTest chatTest = mChatTests.get(position);
        return chatTest.getChatType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == ChatTest.CHAT_TO){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_to, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_from, parent, false);
        }
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatViewHolder cvh = (ChatViewHolder) holder;
        ChatTest chatTest = mChatTests.get(position);
//        cvh.mPortrait.setImageDrawable();
        cvh.mTextContent.setText(chatTest.getText());

        if(chatTest.isTimeShown()){
            cvh.mChatTime.setVisibility(View.VISIBLE);
            cvh.mChatTime.setText(chatTest.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mChatTests.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder{

        public CircularImageView mPortrait;
        public TextView mTextContent;
        public TextView mChatTime;

        public ChatViewHolder(View itemView) {
            super(itemView);

            mPortrait = (CircularImageView) itemView.findViewById(R.id.chat_portrait);
            mTextContent = (TextView) itemView.findViewById(R.id.chat_content);
            mChatTime = (TextView) itemView.findViewById(R.id.chat_time);
        }
    }
}
