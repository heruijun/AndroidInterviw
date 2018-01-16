package com.heruijun.baselibrary;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.github.moduth.blockcanary.BlockCanary;

import cn.hikyson.android.godeye.toolbox.StartupTracer;

/**
 * Created by heruijun on 2018/1/6.
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        BlockCanary.install(this, new AppBlockCanaryContext()).start();

        // 完成andFix模块的初始化
        initAndFix();
        StartupTracer.get().onApplicationCreate();
    }

    private void initAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }
}
