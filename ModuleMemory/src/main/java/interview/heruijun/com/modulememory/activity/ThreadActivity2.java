/**
 * 当在开启一个子线程用于执行一个耗时操作后，此时如果改变配置（例如横竖屏切换）导致了Activity重新创建，一般来说旧Activity就将交给GC进行回收。但如果创建的线程被声明为非静态内部类或者匿名类，那么线程会保持有旧Activity的隐式引用。当线程的run()方法还没有执行结束时，线程是不会被销毁的，因此导致所引用的旧的Activity也不会被销毁，并且与该Activity相关的所有资源文件也不会被回收，因此造成严重的内存泄露。
 * 因此总结来看， 线程产生内存泄露的主要原因有两点：
 * 线程生命周期的不可控。Activity中的Thread和AsyncTask并不会因为Activity销毁而销毁，Thread会一直等到run()执行结束才会停止，AsyncTask的doInBackground()方法同理
 * 非静态的内部类和匿名类会隐式地持有一个外部类的引用
 * <p>
 * 将线程改为静态内部类，切断Activity 对于Thread的强引用
 * 在线程内部采用弱引用保存Context引用，切断Thread对于Activity 的强引用
 */
package interview.heruijun.com.modulememory.activity;

import android.util.Log;

import com.heruijun.baselibrary.activity.BaseActivity;

import java.lang.ref.WeakReference;

import interview.heruijun.com.modulememory.R;

/**
 * Created by heruijun on 2018/3/3.
 */
public class ThreadActivity2 extends BaseActivity {

    private static final String TAG = "ThreadActivity2";
    private static int threadIndex;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_memory;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        threadIndex++;
        new MyThread(this).start();
    }

    private static class MyThread extends Thread {

        private WeakReference<ThreadActivity2> activity2WeakReference;

        MyThread(ThreadActivity2 threadActivity2) {
            activity2WeakReference = new WeakReference<>(threadActivity2);
        }

        @Override
        public void run() {
            if (activity2WeakReference == null) {
                return;
            }
            if (activity2WeakReference.get() != null) {
                int i = threadIndex;
                while (true) {
                    Log.e(TAG, "Hi--" + i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}