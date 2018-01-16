package interview.heruijun.com.androidinterview.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heruijun.baselibrary.service.HotFixService;

import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/1/13.
 */

public class ServiceActivity extends AppCompatActivity {

    private Button mStartService;
    private Button mStopService;
    private Button mBindStartService;
    private Button mBindStopService;
    private ServiceExample.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (ServiceExample.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mStartService = findViewById(R.id.start);
        mStopService = findViewById(R.id.stop);

        mBindStartService = findViewById(R.id.bind_start);
        mBindStopService = findViewById(R.id.bind_stop);

        mStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, HotFixService.class);
                startService(intent);
            }
        });

        mStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, HotFixService.class);
                stopService(intent);
            }
        });

        mBindStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, ServiceExample.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        mBindStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

    }
}
