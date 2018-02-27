package com.heruijun.baselibrary.util;

import android.app.Activity;
import android.content.Context;

/**
 * Created by heruijun on 2018/2/27.
 */

public class AppCache {

    private static Context context;

    private static Activity visibleActivity; //处于 onResume~onPause生命周期内的Activity

    // 云信服务 token
    private static String token;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AppCache.context = context.getApplicationContext();
    }

    public static Activity getVisibleActivity() {
        return visibleActivity;
    }

    public static void setVisibleActivity(Activity visibleActivity) {
        AppCache.visibleActivity = visibleActivity;
    }
}
