package com.heruijun.baselibrary.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import net.qiujuer.genius.kit.reflect.Reflector;
import net.qiujuer.genius.ui.Ui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by heruijun on 2018/1/6.
 */

public class Utils {

    /**
     * 修改系统字体
     */
    public static class FontsOverride {
        @SuppressWarnings("ConstantConditions")
        public static void replace(Context context, final String defFacePath, final String boldFacePath) {
            Typeface defFace = Ui.getFont(context, defFacePath);
            Typeface boldFace = Ui.getFont(context, boldFacePath);

            if (defFace == null)
                return;
            if (boldFace == null)
                boldFace = defFace;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Map<String, Typeface> newMap = new HashMap<String, Typeface>();
                newMap.put("sans-serif", defFace);
                newMap.put("sans", defFace);
                newMap.put("default", defFace);
                newMap.put("default-bold", boldFace);
                newMap.put("monospace", defFace);

                try {
                    Map<String, Typeface> map = Reflector.with(Typeface.class)
                            .field("sSystemFontMap")
                            .get();
                    map.putAll(newMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Reflector.with(Typeface.class)
                            .set("sDefaultTypeface", defFace);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

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

    /**
     * 获取UUID
     *
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取应用程序versionname
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "1.0.0";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

}
