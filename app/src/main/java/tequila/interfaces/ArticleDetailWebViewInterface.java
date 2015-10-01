package tequila.interfaces;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Webview可以調用的方法
 * WebView webView = (WebView) findViewById(R.id.webview);
 * webView.addJavascriptInterface(new WebAppInterface(this), "Android");
 * <input type="button" value="Say hello" onClick="showAndroidToast('Hello Android!')" />
 * <p/>
 * <script type="text/javascript">
 * function showAndroidToast(toast) {
 * Android.showToast(toast);
 * }
 * </script>
 */
public class ArticleDetailWebViewInterface {
    private Context mContext;
    private String mArticle;

    public ArticleDetailWebViewInterface(Context context, String article) {
        mContext = context;
        mArticle = article;
    }

    @JavascriptInterface
    public String getArticle() {
        Toast.makeText(mContext, "getting article", Toast.LENGTH_SHORT).show();
        return mArticle;
    }
}
