package interview.heruijun.com.modulethreadpool.activity;

import android.widget.TextView;

import com.heruijun.baselibrary.activity.BaseActivity;

import butterknife.BindView;
import interview.heruijun.com.modulethreadpool.R;
import interview.heruijun.com.modulethreadpool.R2;

/**
 * 特点：只有非核心线程、线程数量不固定（可无限大）、灵活回收空闲线程（具备超时机制，全部回收时几乎不占系统资源）、新建线程（无线程可用时）
 * 任何线程任务到来都会立刻执行，不需要等待
 * 应用场景：执行大量、耗时少的线程任务
 * 使用：通过*Executors.newCachedThreadPool()*创建
 * Created by heruijun on 2018/3/3.
 */

public class CachedThreadPoolActivity extends BaseActivity {

    @BindView(R2.id.threadpool_test)
    TextView mThreadPool;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_threadpool_test;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }
}
