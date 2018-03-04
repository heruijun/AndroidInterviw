package interview.heruijun.com.modulewebview.normalwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by heruijun on 2018/1/15.
 */

public class BaseWebviewClient extends WebViewClient {

    private BaseWebview myWebview;
    private static final String SCHEMA = "wkjs";

    public BaseWebviewClient(BaseWebview myWebview) {
        this.myWebview = myWebview;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(SCHEMA)) {
            Toast.makeText(myWebview.getContext(), "拦截到schema" + url, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }
}