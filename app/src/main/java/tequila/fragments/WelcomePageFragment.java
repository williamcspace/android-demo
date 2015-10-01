package tequila.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.utils.LogUtils;

public class WelcomePageFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(WelcomePageFragment.class);

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static WelcomePageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        WelcomePageFragment fragment = new WelcomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        LogUtils.d(TAG, Integer.toString(mPage));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_welcome_page_1, container, false);
        ImageView mImageView = (ImageView) rootView.findViewById(R.id.welcome_img);
        switch (mPage) {
            case 1:
                mImageView.setImageResource(R.drawable.ic_one);
                break;
            case 2:
                mImageView.setImageResource(R.drawable.ic_two);
                break;
            case 3:
                mImageView.setImageResource(R.drawable.ic_three);
                break;
        }
        return rootView;
    }
}
