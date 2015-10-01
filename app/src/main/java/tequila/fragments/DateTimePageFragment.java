package tequila.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/24/15.
 */
public class DateTimePageFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(DateTimePageFragment.class);

    public static final String POSITION_KEY = "FragmentPositionKey";
    private int position;

    public static DateTimePageFragment newInstance(Bundle args) {
        DateTimePageFragment fragment = new DateTimePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        position = getArguments().getInt(POSITION_KEY);
        View view = inflater.inflate(R.layout.fragment_datetime_page, container, false);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
