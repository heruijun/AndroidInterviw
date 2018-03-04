package interview.heruijun.com.modulewebview.activity;

import android.os.Build;
import android.webkit.WebView;

/**
 * Created by heruijun on 2018/1/15.
 */

public class NativeCallJs {

    public static void call(WebView webview, String js) {
        if (webview != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webview.evaluateJavascript(js, null);
            } else {
                webview.loadUrl("javacript:" + js);
            }
        }
    }

}
