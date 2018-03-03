/**
 * This Handler class should be static or leaks might occur.
 * <p>
 * 应用在第一次启动时， 系统会在主线程创建Looper对象，Looper实现了一个简单的消息队列，用来循环处理Message。所有主要的应用层事件（例如Activity的生命周期方法回调、Button点击事件等）都会包含在Message里，系统会把Message添加到Looper中，然后Looper进行消息循环。主线程的Looper存在于整个应用的生命周期期间。
 * 当主线程创建Handler对象时，会与Looepr对象绑定，被分发到消息队列的Message会持有对Handler的引用，以便系统在Looper处理到该Message时能调用Handle的handlerMessage(Message)方法。
 * 在上述代码中，Handler不是静态内部类，所以会持有外部类（HandlerActivity）的一个引用。当Handler中有延迟的的任务或者等待执行的任务队列过长时，由于消息持有对Handler的引用，而Handler又持有对其外部类的潜在引用，这条引用关系会一直保持到消息得到处理为止，导致了HandlerActivity无法被垃圾回收器回收，从而导致了内存泄露。
 */
package interview.heruijun.com.modulememory.activity;

import android.os.Handler;
import android.util.Log;

import com.heruijun.baselibrary.activity.BaseActivity;

import interview.heruijun.com.modulememory.R;

/**
 * Created by heruijun on 2018/3/3.
 */
public class HandlerActivity extends BaseActivity {

    private final String TAG = "HandlerActivity";

    private Handler handler = new Handler();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_memory;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Hi");
                handler.postDelayed(this, 1000);
            }
        }, 6000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 防止内存泄漏
        handler.removeCallbacksAndMessages(null);
    }
}