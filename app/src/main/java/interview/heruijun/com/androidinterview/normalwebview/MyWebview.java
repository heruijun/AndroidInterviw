package interview.heruijun.com.androidinterview.normalwebview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by heruijun on 2018/1/15.
 */

public class MyWebview extends WebView {

    public MyWebview(Context context) {
        super(context);
        init();
    }

    public MyWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        WebSettings wSet = this.getSettings();
        wSet.setJavaScriptEnabled(true);
        this.setWebViewClient(new MyWebviewClient(this));
        this.setWebChromeClient(new MyWebChromeClient());
    }
}
