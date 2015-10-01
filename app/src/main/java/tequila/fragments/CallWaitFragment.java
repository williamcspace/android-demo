package tequila.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class CallWaitFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(CallWaitFragment.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_call_wait, container, false);
        return view;
    }
}

