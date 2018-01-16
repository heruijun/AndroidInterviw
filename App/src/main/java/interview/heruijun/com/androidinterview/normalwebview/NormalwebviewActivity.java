package interview.heruijun.com.androidinterview.normalwebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;

import interview.heruijun.com.androidinterview.R;

public class NormalwebviewActivity extends AppCompatActivity {

    private MyWebview mWebview;
    private Button mNativeCallJsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normalwebview);

        mNativeCallJsBtn = findViewById(R.id.nativeCallJs);
        mWebview = findViewById(R.id.mWebviewExample);
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
