/**
 * 内存泄露(Memory Leak)指一个无用对象持续占有内存或无用对象的内存得不到及时的释放，从而造成内存空间的浪费
 * 例如，当Activity的onDestroy()方法被调用以后，Activity 本身以及它涉及到的 View、Bitmap等都应该被回收。
 * 但是，如果有一个后台线程持有对这个Activity的引用，那么Activity占据的内存就不能被回收，严重时将导致OOM，最终Crash。
 * 内存溢出(Out Of Memory)指一个应用在申请内存时，没有足够的内存空间供其使用
 * <p>
 * 有时候我们需要把一些对象加入到集合容器（例如ArrayList）中，当不再需要当中某些对象时，如果不把该对象的引用从集
 * 合中清理掉，也会使得GC无法回收该对象。如果集合是static类型的话，那内存泄漏情况就会更为严重。
 * 因此，当不再需要某对象时，需要主动将之从集合中移除
 */
package interview.heruijun.com.modulememory.activity;

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
import interview.heruijun.com.modulememory.R;
import interview.heruijun.com.modulememory.R2;

/**
 * 模拟Hander内存溢出
 * Created by heruijun on 2018/3/3.
 */
@Route(path = RouterPath.PATH_MEMORY)
public class MemoryActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    RecyclerAdapter<String> adapter;


    private static final String LINE_0 = "Handler引起内存泄漏并解决的案例";
    private static final String LINE_1 = "Thread引起内存泄漏并解决的案例（开关控制方案）";
    private static final String LINE_2 = "Thread引起内存泄漏并解决的案例（静态内部类和对Activity弱引用的方案）";

    private List<String> lines = Arrays.asList(
            LINE_0,
            LINE_1,
            LINE_2
    );

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_memory;
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
                return new MemoryActivity.ViewHolder(root);
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
                startActivity(new Intent(MemoryActivity.this, HandlerActivity.class));
                break;
            case LINE_1:
                startActivity(new Intent(MemoryActivity.this, ThreadActivity1.class));
                break;
            case LINE_2:
                startActivity(new Intent(MemoryActivity.this, ThreadActivity2.class));
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