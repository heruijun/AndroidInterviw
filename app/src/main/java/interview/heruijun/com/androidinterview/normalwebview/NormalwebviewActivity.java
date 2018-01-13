package interview.heruijun.com.androidinterview.normalwebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import interview.heruijun.com.androidinterview.R;

public class NormalwebviewActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normalwebview);

        mWebview = findViewById(R.id.mWebviewExample);
        mWebview.loadUrl("http://www.baidu.com");
        WebSettings wSet = mWebview.getSettings();
        wSet.setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                view.loadUrl("javascript:alert('js')");
                return true;
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return false;
            }
        });
    }
}
