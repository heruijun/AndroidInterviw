package interview.heruijun.com.modulewebview.normalwebview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.heruijun.baselibrary.util.Utils;

/**
 * Created by heruijun on 2018/1/15.
 */

public class BaseWebview extends WebView {

    private Context mContext;

    public BaseWebview(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public BaseWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        setDefaultWebSettings(this);
        this.setWebViewClient(new BaseWebviewClient(this));
        this.setWebChromeClient(new BaseWebChromeClient());
    }

    public void setDefaultWebSettings(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //允许js代码
        webSettings.setJavaScriptEnabled(true);
        //允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        //禁用放缩
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        //禁用文字缩放
        webSettings.setTextZoom(100);
        //10M缓存，api 18后，系统自动管理。
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(mContext.getDir("appcache", 0).getPath());
        //允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        //不保存密码
        webSettings.setSavePassword(false);
        //设置UA
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " myApp/" + Utils.getVersionName(mContext));
        //移除部分系统JavaScript接口
        BaseWebViewSecurity.removeJavascriptInterfaces(webView);
        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
    }
}
