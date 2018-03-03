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
 * Created by heruijun on 2018/3/3.
 */
@Route(path = RouterPath.PATH_THREADPOOL)
public class ThreadPoolActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    RecyclerAdapter<String> adapter;


    private static final String LINE_0 = "线程池";

    private List<String> lines = Arrays.asList(
            LINE_0
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
                startActivity(new Intent(ThreadPoolActivity.this, MyFixedThreadPool.class));
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