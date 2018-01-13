package interview.heruijun.com.androidinterview.webview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSessionConfig;

import interview.heruijun.com.androidinterview.MainActivity;
import interview.heruijun.com.androidinterview.R;

public class WebviewActivity extends AppCompatActivity {

    public static final int MODE_DEFAULT = 0;
    public static final int MODE_SONIC = 1;
    public static final int MODE_SONIC_WITH_OFFLINE_CACHE = 2;
    private static final int PERMISSION_REQUEST_CODE_STORAGE = 1;
    private String DEMO_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_webview);

        // clean up cache btn
        Button btnReset = (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SonicEngine.getInstance().cleanCache();
            }
        });

        // default btn
        Button btnDefault = (Button) findViewById(R.id.btn_default_mode);
        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBrowserActivity(MODE_DEFAULT);
            }
        });

        // preload btn
        Button btnSonicPreload = (Button) findViewById(R.id.btn_sonic_preload);
        btnSonicPreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
                sessionConfigBuilder.setSupportLocalServer(true);

                // preload session
                boolean preloadSuccess = SonicEngine.getInstance().preCreateSession(DEMO_URL, sessionConfigBuilder.build());
                Toast.makeText(getApplicationContext(), preloadSuccess ? "Preload start up success!" : "Preload start up fail!", Toast.LENGTH_LONG).show();
            }
        });

        // sonic mode load btn
        Button btnSonic = (Button) findViewById(R.id.btn_sonic);
        btnSonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBrowserActivity(MODE_SONIC);
            }
        });

        // load sonic with offline cache
        Button btnSonicWithOfflineCache = (Button) findViewById(R.id.btn_sonic_with_offline);
        btnSonicWithOfflineCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBrowserActivity(MODE_SONIC_WITH_OFFLINE_CACHE);
            }
        });

        if (hasPermission()) {
            init();
        } else {
            requestPermission();
        }

        final UrlListAdapter urlListAdapter = new UrlListAdapter(WebviewActivity.this);

        FloatingActionButton btnFab = (FloatingActionButton) findViewById(R.id.btn_fab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UrlSelector.launch(WebviewActivity.this, urlListAdapter, new UrlSelector.OnUrlChangedListener() {
                    @Override
                    public void urlChanged(String url) {
                        DEMO_URL = url;
                    }
                });
            }
        });

        DEMO_URL = urlListAdapter.getCheckedUrl();
    }

    private void init() {
        // init sonic engine
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }
    }


    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_STORAGE == requestCode) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
            } else {
                init();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startBrowserActivity(int mode) {
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.putExtra(BrowserActivity.PARAM_URL, DEMO_URL);
        intent.putExtra(BrowserActivity.PARAM_MODE, mode);
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
        startActivityForResult(intent, -1);
    }
}
