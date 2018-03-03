package interview.heruijun.com.modulethreadpool.activity;

import android.widget.TextView;

import com.heruijun.baselibrary.activity.BaseActivity;

import butterknife.BindView;
import interview.heruijun.com.modulethreadpool.R;
import interview.heruijun.com.modulethreadpool.R2;

/**
 * 特点：只有一个核心线程（保证所有任务按照指定顺序在一个线程中执行，不需要处理线程同步的问题）
 * 应用场景：不适合并发但可能引起IO阻塞性及影响UI线程响应的操作，如数据库操作，文件操作等
 * 使用：通过*Executors.newSingleThreadExecutor()*创建
 * Created by heruijun on 2018/3/3.
 */

public class SingleThreadExecutorActivity extends BaseActivity {

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
