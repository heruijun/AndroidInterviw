package interview.heruijun.com.modulewebview.normalwebview;

import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by heruijun on 2018/1/15.
 */

public class BaseWebChromeClient extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return false;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        // js输入的Log内容，console.log(...)
        String msg = consoleMessage.message();
        return super.onConsoleMessage(consoleMessage);
    }
}