package interview.heruijun.com.androidinterview.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.widget.Toast;

import cn.hikyson.android.godeye.toolbox.StartupTracer;
import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/1/16.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        StartupTracer.get().onSplashCreate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED) {
            Toast.makeText(this, "grant " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " permission.", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        } else {
            Toast.makeText(this, "permission " + Manifest.permission.WRITE_EXTERNAL_STORAGE + " need!!!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
