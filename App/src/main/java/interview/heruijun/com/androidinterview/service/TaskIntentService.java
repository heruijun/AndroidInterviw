package interview.heruijun.com.androidinterview.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by heruijun on 2018/1/18.
 */

public class TaskIntentService extends IntentService {

    private static Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isTimerRunning = false;

    public TaskIntentService() {
        super("TaskIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 根据Intent的不同进行不同的事物处理
        String taskName = intent.getExtras().getString("task");
        switch (taskName) {
            case "start":
                Log.e("TaskIntentService", "任务开始");
                cancelTimer();
                break;
            case "stop":
                mTimer = new Timer();
                mTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (!isTimerRunning) {
                            Log.e("TaskIntentService", "任务结束");
                            isTimerRunning = true;
                            cancelTimer();
                        }
                    }
                };
                mTimer.schedule(mTimerTask, 10000);
                break;
            default:
                break;
        }
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    private void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    @Override
    public void onCreate() {
        Log.e("TaskIntentService", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e("TaskIntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("TaskIntentService", "onDestroy");
        super.onDestroy();
    }
}
