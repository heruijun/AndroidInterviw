/**
 * 常见的4种功能线程池
 * 1.定长线程池
 * 2.定时线程池
 * 3.可缓存线程池
 * 4.单线程化线程池
 */
package interview.heruijun.com.modulethreadpool.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heruijun.baselibrary.activity.BaseActivity;
import com.heruijun.baselibrary.config.RouterPath;
import com.heruijun.baselibrary.recycler.DividerItemDecoration;
import com.heruijun.baselibrary.recycler.RecyclerAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import interview.heruijun.com.modulethreadpool.R;
import interview.heruijun.com.modulethreadpool.R2;

/**
 * // 1. 创建线程池
 * // 创建时，通过配置线程池的参数，从而实现自己所需的线程池
 * Executor threadPool = new ThreadPoolExecutor(
 * CORE_POOL_SIZE,
 * MAXIMUM_POOL_SIZE,
 * KEEP_ALIVE,
 * TimeUnit.SECONDS,
 * sPoolWorkQueue,
 * sThreadFactory
 * );
 * // 注：在Java中，已内置4种常见线程池，下面会详细说明
 * <p>
 * // 2. 向线程池提交任务：execute（）
 * // 说明：传入 Runnable对象
 * threadPool.execute(new Runnable() {
 *
 * @Override public void run() {
 * ... // 线程执行任务
 * }
 * });
 * <p>
 * // 3. 关闭线程池shutdown()
 * threadPool.shutdown();
 * <p>
 * // 关闭线程的原理
 * // a. 遍历线程池中的所有工作线程
 * // b. 逐个调用线程的interrupt（）中断线程（注：无法响应中断的任务可能永远无法终止）
 * <p>
 * // 也可调用shutdownNow（）关闭线程：threadPool.shutdownNow（）
 * // 二者区别：
 * // shutdown：设置 线程池的状态 为 SHUTDOWN，然后中断所有没有正在执行任务的线程
 * // shutdownNow：设置 线程池的状态 为 STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表
 * // 使用建议：一般调用shutdown（）关闭线程池；若任务不一定要执行完，则调用shutdownNow（）
 * <p>
 * Created by heruijun on 2018/3/3.
 */
@Route(path = RouterPath.PATH_THREADPOOL)
public class ThreadPoolActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    RecyclerAdapter<String> adapter;


    private static final String LINE_0 = "定长线程池（FixedThreadPool）";
    private static final String LINE_1 = "定时线程池（ScheduledThreadPool）";
    private static final String LINE_2 = "可缓存线程池（CachedThreadPool）";
    private static final String LINE_3 = "单线程化线程池（SingleThreadExecutor）";

    private List<String> lines = Arrays.asList(
            LINE_0,
            LINE_1,
            LINE_2,
            LINE_3
    );

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_threadpool;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        adapter = new RecyclerAdapter<String>(lines, new RecyclerAdapter.AdapterListenerImpl<String>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, String line) {
                bindLine(line);
            }
        }) {
            @Override
            protected ViewHolder<String> onCreateViewHolder(View root, int viewType) {
                return new ThreadPoolActivity.ViewHolder(root);
            }

            @Override
            protected int getItemViewType(int position, String s) {
                return R.layout.list_item;
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void bindLine(String line) {
        switch (line) {
            case LINE_0:
                startActivity(new Intent(ThreadPoolActivity.this, FixedThreadPoolActivity.class));
                break;
            case LINE_1:
                startActivity(new Intent(ThreadPoolActivity.this, ScheduledThreadPoolActivity.class));
                break;
            case LINE_2:
                startActivity(new Intent(ThreadPoolActivity.this, CachedThreadPoolActivity.class));
                break;
            case LINE_3:
                startActivity(new Intent(ThreadPoolActivity.this, SingleThreadExecutorActivity.class));
                break;
        }
    }

    class ViewHolder extends RecyclerAdapter.ViewHolder<String> {

        @BindView(R2.id.text_title)
        AppCompatTextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(String s) {
            textView.setText(s);
        }
    }
}