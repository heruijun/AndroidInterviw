package com.heruijun.baselibrary;

import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by heruijun on 2018/1/6.
 */

public class MyApplication extends MultiDexApplication {

    private static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        BlockCanary.install(this, new AppBlockCanaryContext()).start();

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        // 完成andFix模块的初始化
        initAndFix();
//        StartupTracer.get().onApplicationCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static Handler getHandler() {
        if (sHandler == null) {
            sHandler = new Handler();
        }
        return sHandler;
    }

    private void initAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }
}
