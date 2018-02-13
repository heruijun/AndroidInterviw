package interview.heruijun.com.androidinterview.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heruijun.baselibrary.service.HotFixService;

import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/1/13.
 */

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = ServiceActivity.class.getSimpleName();

    private Button mStartService;
    private Button mStopService;
    private Button mBindStartService;
    private Button mBindStopService;
    private Button mStartIntentService;
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
        Log.e(TAG, "onCreate");

        mStartService = findViewById(R.id.start);
        mStopService = findViewById(R.id.stop);

        mBindStartService = findViewById(R.id.bind_start);
        mBindStopService = findViewById(R.id.bind_stop);

        mStartIntentService = findViewById(R.id.start_intentservice);

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

        mStartIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceActivity.this, MyIntentService.class);
                Bundle bundle = new Bundle();
                bundle.putString("taskName", "task1");
                i.putExtras(bundle);
                startService(i);

                Intent i2 = new Intent(ServiceActivity.this, MyIntentService.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("taskName", "task2");
                i2.putExtras(bundle2);
                startService(i2);

                startService(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
        Intent intent = new Intent(ServiceActivity.this, TaskIntentService.class);
        intent.putExtra("task", "stop");
        startService(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
        Intent intent = new Intent(ServiceActivity.this, TaskIntentService.class);
        intent.putExtra("task", "start");
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestory");
    }
}
