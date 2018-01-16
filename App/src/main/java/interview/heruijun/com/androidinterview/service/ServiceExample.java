package interview.heruijun.com.androidinterview.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by heruijun on 2018/1/13.
 */

public class ServiceExample extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.e("service", "service startDownload");
        }

        public void getProgress() {
            Log.e("service", "service getProgress");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("service", "service开启");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service", "service onStartCommand");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("service", "service onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("service", "service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("service", "service销毁");
    }
}
