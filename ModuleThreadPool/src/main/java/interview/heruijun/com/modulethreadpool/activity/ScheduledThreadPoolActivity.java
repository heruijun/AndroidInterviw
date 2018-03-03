package interview.heruijun.com.modulethreadpool.activity;

import android.widget.TextView;

import com.heruijun.baselibrary.activity.BaseActivity;

import butterknife.BindView;
import interview.heruijun.com.modulethreadpool.R;
import interview.heruijun.com.modulethreadpool.R2;

/**
 * 特点：核心线程数量固定、非核心线程数量无限制（闲置时马上回收）
 * 应用场景：执行定时 / 周期性 任务
 * 使用：通过*Executors.newScheduledThreadPool()*创建
 * Created by heruijun on 2018/3/3.
 */

public class ScheduledThreadPoolActivity extends BaseActivity {

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
