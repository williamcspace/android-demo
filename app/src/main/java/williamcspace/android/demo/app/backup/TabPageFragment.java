package williamcspace.android.demo.app.backup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.fragments.BaseFragment;

public class TabPageFragment extends BaseFragment {
    private static final String TAG = TabPageFragment.class.getSimpleName();

    public static final String POSITION_KEY = "FragmentPositionKey";
    private int position;

    public static TabPageFragment newInstance(Bundle args) {
        TabPageFragment fragment = new TabPageFragment();
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

        View view = inflater.inflate(R.layout.fragment_tab_page, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);;
        textView.setText("Fragment #" + position);
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

    /**
     * TODO: 每个页面需要实现该方法返回一个该页面所对应的资源ID
     *
     * @return 页面资源ID
     */
    @Override
    public int getLayoutId() {
        return 0;
    }
}