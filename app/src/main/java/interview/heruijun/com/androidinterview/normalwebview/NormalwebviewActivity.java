package interview.heruijun.com.androidinterview.normalwebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import interview.heruijun.com.androidinterview.R;

public class NormalwebviewActivity extends AppCompatActivity {

    private WebView mWebview;
    private static final String SCHEMA = "wkjs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normalwebview);

        mWebview = findViewById(R.id.mWebviewExample);
        mWebview.loadUrl("file:///android_asset/index.html");
        WebSettings wSet = mWebview.getSettings();
        wSet.setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new MyWebviewClient());
        mWebview.setWebChromeClient(new MyWebChromeClient());
    }

    class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(SCHEMA)) {
                Toast.makeText(NormalwebviewActivity.this, "拦截到schema" + url, Toast.LENGTH_SHORT).show();
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return false;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }
}
