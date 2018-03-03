package interview.heruijun.com.modulethreadpool.activity;

import android.widget.TextView;

import com.heruijun.baselibrary.activity.BaseActivity;

import butterknife.BindView;
import interview.heruijun.com.modulethreadpool.R;
import interview.heruijun.com.modulethreadpool.R2;

/**
 * 特点：只有核心线程 & 不会被回收、线程数量固定、任务队列无大小限制（超出的线程任务会在队列中等待）
 * 应用场景：控制线程最大并发数
 * 具体使用：通过 Executors.newFixedThreadPool() 创建
 * Created by heruijun on 2018/3/3.
 */

public class FixedThreadPoolActivity extends BaseActivity {

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
