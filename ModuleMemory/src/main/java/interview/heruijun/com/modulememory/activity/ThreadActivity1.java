/**
 * 当在开启一个子线程用于执行一个耗时操作后，此时如果改变配置（例如横竖屏切换）导致了Activity重新创建，一般来说旧Activity就将交给GC进行回收。但如果创建的线程被声明为非静态内部类或者匿名类，那么线程会保持有旧Activity的隐式引用。当线程的run()方法还没有执行结束时，线程是不会被销毁的，因此导致所引用的旧的Activity也不会被销毁，并且与该Activity相关的所有资源文件也不会被回收，因此造成严重的内存泄露。
 * 因此总结来看， 线程产生内存泄露的主要原因有两点：
 * 线程生命周期的不可控。Activity中的Thread和AsyncTask并不会因为Activity销毁而销毁，Thread会一直等到run()执行结束才会停止，AsyncTask的doInBackground()方法同理
 * 非静态的内部类和匿名类会隐式地持有一个外部类的引用
 * <p>
 * 想要避免因为Thread造成内存泄漏，可以在Activity退出后主动停止Thread 例如，可以为Thread设置一个布尔变量threadSwitch来控制线程的启动与停止
 */
package interview.heruijun.com.modulememory.activity;

import android.util.Log;

import com.heruijun.baselibrary.activity.BaseActivity;

import interview.heruijun.com.modulememory.R;

/**
 * Created by heruijun on 2018/3/3.
 */
public class ThreadActivity1 extends BaseActivity {

    private final String TAG = "ThreadActivity1";
    private int threadIndex;
    private boolean threadSwitch = true;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_memory;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        threadIndex++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int j = threadIndex;
                while (threadSwitch) {
                    Log.e(TAG, "Hi--" + j);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 防止内存泄漏
        threadSwitch = false;
    }
}