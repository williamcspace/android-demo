package tequila.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.adapters.ChatListAdapter;
import com.tangxinli.android.tequila.models.ChatTest;
import com.tangxinli.android.tequila.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class ChatFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(ChatFragment.class);

    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FieldDialogFragment mFieldDialogFragment;
    private List<ChatTest> mChatTests = new ArrayList<>();

//    private CCPChattingFooter2 mChattingFooter;
    private String mRecipients;
//    private OnChattingFooterImpl mChattingFooterImpl = new OnChattingFooterImpl();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecipients = "test";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        initToolbar(view);
        initChatView(view);

        return view;
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

    private void initChatView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.chatList);
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                mChatTests.addAll(0, fetchChats(20));
                mAdapter.notifyDataSetChanged();
            }
        });
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mChatTests.addAll(fetchChats(20));
        mAdapter = new ChatListAdapter(this.getActivity(), mChatTests);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initFooter(View view) {
//        // 初始化聊天功能面板
//        mChattingFooter = (CCPChattingFooter2) view.findViewById(R.id.nav_footer);
//        // 注册一个聊天面板文本输入框改变监听
//        mChattingFooter.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        // 注册聊天面板状态回调通知、包含录音按钮按钮下放开等录音操作
////        mChattingFooter.setOnChattingFooterLinstener(mChattingFooterImpl);
//        // 注册聊天面板附加功能（图片、拍照、文件）被点击回调通知
////        mChattingFooter.setOnChattingPanelClickListener(mChattingPanelImpl);
//        mChattingFooter.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    public List<ChatTest> fetchChats(int size) {
        List<ChatTest> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            ChatTest chatTest = new ChatTest();
            int temp = (Math.random() <= 0.5) ? 1 : 2;
            boolean temp2 = (Math.random() <= 0.5);
            chatTest.setTimeShown(temp2);
            chatTest.setChatType(temp);
            chatTest.setText("Message " + i + ": " + "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laudantium quod, a ex. Debitis aspernatur sit ullam, placeat ipsa veritatis, perspiciatis neque soluta in sequi architecto, sapiente modi nostrum obcaecati tenetur!");
            chatTest.setTime("2015年1月1日");
            result.add(chatTest);
        }
        return result;
    }

    /**
     * 聊天功能面板（发送、录音、切换输入选项）
     */
//    private class OnChattingFooterImpl implements CCPChattingFooter2.OnChattingFooterLinstener {
//
//        ChatActivity mActivity;
//        protected String mAmrPathName;
//        private ECHandlerHelper mHandlerHelper = new ECHandlerHelper();
//
//        private boolean debugeTest = false;
//        /**
//         * 待发的ECMessage消息
//         */
//        private ECMessage mPreMessage;
//        /**
//         * 同步锁
//         */
//        Object mLock = new Object();
//
//        public OnChattingFooterImpl(ChatActivity ctx) {
//            mActivity = ctx;
//        }
//
//        @Override
//        public void OnVoiceRcdInitReuqest() {
//
//        }
//
//        @Override
//        public void OnVoiceRcdStartRequest() {
//
//        }
//
//        @Override
//        public void OnVoiceRcdCancelRequest() {
//
//        }
//
//        @Override
//        public void OnVoiceRcdStopRequest() {
//
//        }
//
//        @Override
//        public void OnSendTextMessageRequest(CharSequence text) {
//            if (text != null && text.toString().trim().startsWith("starttest://")) {
//
//                handleTest(text.toString().substring("starttest://".length()));
//                return;
//            } else if (text != null && text.toString().trim().startsWith("endtest://")) {
//                debugeTest = false;
//                return;
//            } else if (text != null && text.toString().trim().startsWith("startmcmmessage://")) {
//                handleSendeMcmMsgTest(text.toString().substring("startmcmmessage://".length()));
//            }
//            handleSendTextMessage(text);
//        }
//
//        @Override
//        public void OnUpdateTextOutBoxRequest(CharSequence text) {
//
//        }
//
//        @Override
//        public void OnSendCustomEmojiRequest(int emojiid, String emojiName) {
//
//        }
//
//        @Override
//        public void OnEmojiDelRequest() {
//
//        }
//
//        @Override
//        public void OnInEditMode() {
////            scrollListViewToBottom
//        }
//
//        @Override
//        public void onPause() {
//
//        }
//
//        @Override
//        public void onResume() {
//
//        }
//
//        @Override
//        public void release() {
//            mActivity = null;
//            mPreMessage = null;
//            mLock = null;
//        }
//
//        /*******************************************
//         * DEBUGE START
//         *********************************************/
//        private void handleTest(final String count) {
//            if (TextUtils.isEmpty(count) || count.trim().length() == 0) {
//                ToastUtils.showShort(getActivity(),"测试协议失败，测试消息条数必须大于0");
//                return;
//            }
//            final String text = getString(R.string.app_test_message);
//            // final String text = getTestText();
//        }
//
//        /*******************************************
//         * DEBUGE END
//         *********************************************/
//
//
//        private void handleSendeMcmMsgTest(String text) {
////            ECDeskManager deskManager = SDKCoreHelper.getECDeskManager();
////            deskManager.startConsultation("18600668603", new ECDeskManager.OnStartConsultationListener() {
////                @Override
////                public void onStartConsultation(ECError e, String agent) {
////                    ToastUtils.showShort(getActivity(),"agent:" + agent + " ,code:" + e.errorCode);
////                }
////
////                @Override
////                public void onComplete(ECError error) {
////
////                }
////            });
//        }
//
//        /**
//         * 处理文本发送方法事件通知
//         *
//         * @param text
//         */
//        private void handleSendTextMessage(CharSequence text) {
//            if (text == null) {
//                return;
//            }
//            if (text.toString().trim().length() <= 0) {
////                canotSendEmptyMessage();
//                return;
//            }
//            // 组建一个待发送的ECMessage
//            ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
//            //设置消息的属性：发出者，接受者，发送时间等
////            msg.setForm(getUserId());
//            msg.setMsgTime(System.currentTimeMillis());
//            // 设置消息接收者
//            msg.setTo(mRecipients);
//            msg.setSessionId(mRecipients);
//            // 设置消息发送类型（发送或者接收）
//            msg.setDirection(ECMessage.Direction.SEND);
//            // 创建一个文本消息体，并添加到消息对象中
//            ECTextMessageBody msgBody = new ECTextMessageBody(text.toString());
//            msg.setBody(msgBody);
//            try {
//                // 发送消息，该函数见上
////                long rowId = sendECMessage(msg);
//                // 通知列表刷新
////                msg.setId(rowId);
////                notifyIMessageListView(msg);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}

