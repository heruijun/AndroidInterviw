package com.heruijun.baselibrary;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.moduth.blockcanary.BlockCanary;
import com.heruijun.baselibrary.util.Utils;
import com.squareup.leakcanary.LeakCanary;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * Created by heruijun on 2018/1/6.
 */

public class MyApplication extends MultiDexApplication {

    protected static Application instance;
    private static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        instance = this;

        // 替换漂亮的字体
        Utils.FontsOverride.replace(this, "FZLanTingHeiS-L-GB-Regular.TTF",
                "FZLanTingHeiS-DB1-GB-Regular.TTF");

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

    public static Application getInstance() {
        return instance;
    }

    public static void showToast(final String msg) {
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                Toast.makeText(instance, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToast(@StringRes int msgId) {
        showToast(instance.getString(msgId));
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
