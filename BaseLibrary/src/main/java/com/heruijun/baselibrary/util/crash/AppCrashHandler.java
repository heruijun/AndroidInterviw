package com.heruijun.baselibrary.util.crash;

import android.content.Context;

/**
 * Created by heruijun on 2018/2/27.
 */

public class AppCrashHandler {

    private String logFileSuffix = ".appcrashlog";
    private Context context;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private static AppCrashHandler instance;

    public AppCrashHandler(Context context) {
        this.context = context;

        // get default
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        // install
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                // save log
                saveException(ex, logFileSuffix, true);

                // uncaught
                uncaughtExceptionHandler.uncaughtException(thread, ex);
            }
        });
    }

    /**
     * 设置文件后缀
     *
     * @param logFileSuffix
     */
    public void setLogFileSuffix(String logFileSuffix) {
        this.logFileSuffix = logFileSuffix;
    }

    public static AppCrashHandler getInstance(Context context) {
        if (instance == null) {
            instance = new AppCrashHandler(context);
        }

        return instance;
    }

    public final void saveException(Throwable ex, String logFileSuffix, boolean uncaught) {
        CrashServer.save(context, ex, logFileSuffix, uncaught);
    }

    public void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler handler) {
        if (handler != null) {
            this.uncaughtExceptionHandler = handler;
        }
    }
}
