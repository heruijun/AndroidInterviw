package interview.heruijun.com.androidinterview.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.heruijun.baselibrary.fragment.BaseFragment;

import butterknife.BindView;
import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.modulewebview.normalwebview.BaseWebChromeClient;
import interview.heruijun.com.modulewebview.normalwebview.BaseWebview;

/**
 * Created by heruijun on 2018/3/3.
 */

public class WebViewExampleFragment extends BaseFragment {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    BaseWebview mWebView;

    public static WebViewExampleFragment newInstance() {
        WebViewExampleFragment webViewExampleFragment = new WebViewExampleFragment();
        return webViewExampleFragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        mWebView = new BaseWebview(getActivity().getApplicationContext());
        LinearLayout layout = root.findViewById(R.id.webview_container);
        layout.addView(mWebView);

        mWebView.loadUrl("https://www.oppo.com/cn/");
        mWebView.setWebChromeClient(new BaseWebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e("newProgress", newProgress + "");
                mProgressBar.setProgress(newProgress);
                if (newProgress >= 80) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            mWebView.destroy();

        }
        super.onDestroy();
    }
}
