package interview.heruijun.com.modulewebview.activity;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heruijun.baselibrary.activity.BaseActivity;
import com.heruijun.baselibrary.config.RouterPath;

import butterknife.BindView;
import interview.heruijun.com.modulewebview.R;
import interview.heruijun.com.modulewebview.R2;
import interview.heruijun.com.modulewebview.normalwebview.BaseWebview;

@Route(path = RouterPath.PATH_WEBVIEW)
public class NormalwebviewActivity extends BaseActivity {

    @BindView(R2.id.mWebviewExample)
    BaseWebview mWebview;

    @BindView(R2.id.nativeCallJs)
    Button mNativeCallJsBtn;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_normalwebview;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mWebview.loadUrl("file:///android_asset/index.html");

        mNativeCallJsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebview.post(new Runnable() {
                    @Override
                    public void run() {
                        NativeCallJs.call(mWebview, "callJS()");
                    }
                });
            }
        });
    }
}
