package interview.heruijun.com.androidinterview.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by heruijun on 2018/1/18.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 根据Intent的不同进行不同的事物处理
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName) {
            case "task1":
                Log.e("MyIntentService", "do task1");
                break;
            case "task2":
                Log.e("MyIntentService", "do task2");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {
        Log.e("MyIntentService", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e("MyIntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("MyIntentService", "onDestroy");
        super.onDestroy();
    }
}
