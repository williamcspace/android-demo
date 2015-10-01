package tequila.activities;

import android.os.Bundle;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.ChatFragment;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/17/15.
 */
public class ChatActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(ChatActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if (savedInstanceState == null) {
            ChatFragment fragment = new ChatFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, "Chat")
                    .commit();
        }
    }
}
