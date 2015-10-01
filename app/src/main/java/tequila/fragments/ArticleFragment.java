package tequila.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.Gson;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.dao.models.Post;
import com.tangxinli.android.tequila.interfaces.ArticleDetailWebViewInterface;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 7/21/15.
 */
public class ArticleFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(ArticleFragment.class);

    private Post mPost;
    private Toolbar mToolbar;
    private WebView mWebView;

    public static ArticleFragment newInstance(Bundle args){
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPost = new Gson().fromJson(getArguments().getString("data"), Post.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        initToolbar(view);
        initWebView(view);
        return view;
    }

    private void initToolbar(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setNavigationIcon(R.drawable.icon_btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
//                getActivity().finish();
//                getActivity().overridePendingTransition(R.anim.common_slide_right_in, R.anim.common_slide_right_out);
            }
        });
    }

    private void initWebView(View view){
        mWebView = (WebView) view.findViewById(R.id.webview_article_detail);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollContainer(false);
        mWebView.addJavascriptInterface(new ArticleDetailWebViewInterface(this.getActivity(), new Gson().toJson(mPost)), "ArticleDetailApi");
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("file:///android_asset/www/index.html");
    }
}

