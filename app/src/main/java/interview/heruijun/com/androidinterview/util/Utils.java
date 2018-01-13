package interview.heruijun.com.androidinterview.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.UUID;

/**
 * Created by heruijun on 2018/1/6.
 */

public class Utils {

    public static ActivityManager.RunningAppProcessInfo getRunningProcess(Context context) {
        int myPid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningAppProcessInfo myProcess = null;
        for (ActivityManager.RunningAppProcessInfo process : activityManager.getRunningAppProcesses()) {
            if (process.pid == myPid) {
                myProcess = process;
                break;
            }
        }
        if (myProcess == null) {
            Log.e("AndroidUtils", "Could not find running process for " + myPid);
            return null;
        }
        return myProcess;
    }

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

}
