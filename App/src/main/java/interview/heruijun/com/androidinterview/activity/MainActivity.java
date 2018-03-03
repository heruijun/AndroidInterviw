package interview.heruijun.com.androidinterview.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heruijun.baselibrary.activity.BaseActivity;
import com.heruijun.baselibrary.config.RouterPath;
import com.heruijun.baselibrary.recycler.DividerItemDecoration;
import com.heruijun.baselibrary.recycler.RecyclerAdapter;
import com.heruijun.baselibrary.util.Utils;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.classloader.ClassloaderActivity;
import interview.heruijun.com.androidinterview.normalwebview.NormalwebviewActivity;
import interview.heruijun.com.androidinterview.service.ServiceActivity;
import interview.heruijun.com.androidinterview.webview.WebviewActivity;

/**
 * Created by heruijun on 2018/1/5.
 */

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private static final String LINE_0 = "Activity相关";
    private static final String LINE_1 = "普通webview";
    private static final String LINE_2 = "登陆注册";
    private static final String LINE_3 = "获取当前进程";
    private static final String LINE_4 = "Android动画";
    private static final String LINE_5 = "Handler正常用法";
    private static final String LINE_6 = "腾讯webview";
    private static final String LINE_7 = "内存抖动";
    private static final String LINE_8 = "列表的事件分发";
    private static final String LINE_9 = "事件分发机制";
    private static final String LINE_10 = "service用法";
    private static final String LINE_11 = "插件化与热修复";

    private List<String> lines = Arrays.asList(
            LINE_0, LINE_1, LINE_2, LINE_3, LINE_4, LINE_5,
            LINE_6, LINE_7, LINE_8, LINE_9, LINE_10, LINE_11
    );

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    RecyclerAdapter<String> adapter;

    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        //        GodEye.instance().installAll(getApplication(), new CrashFileProvider(this, new Serializer() {
//            @Override
//            public String serialize(Object o) {
//                return GsonUtil.toJson(o);
//            }
//
//            @Override
//            public <T> T deserialize(Reader reader, Class<T> clz) {
//                return GsonUtil.fromJson(reader, clz);
//            }
//        }));
//        GodEyeMonitor.work(this);
//        StartupTracer.get().onSplashCreate();

        // ActivityMgr.getInstance().addActivity(this);     // 模拟内存泄漏

        Uri uri = getIntent().getData();
        if (uri != null) {
            // 完整的url信息
            String url = uri.toString();
            Log.e(TAG, "url: " + url);
            // scheme部分
            String scheme = uri.getScheme();
            Log.e(TAG, "scheme: " + scheme);
            // host部分
            String host = uri.getHost();
            Log.e(TAG, "host: " + host);
            //port部分
            int port = uri.getPort();
            Log.e(TAG, "host: " + port);
            // 访问路劲
            String path = uri.getPath();
            Log.e(TAG, "path: " + path);
            List<String> pathSegments = uri.getPathSegments();
            // Query部分
            String query = uri.getQuery();
            Log.e(TAG, "query: " + query);
            //获取指定参数值
            String id = uri.getQueryParameter("id");
            Log.e(TAG, "id: " + id);
            String name = uri.getQueryParameter("name");
            Log.e(TAG, "name: " + name);
        }
        // ARouter.getInstance().build(uri).navigation();

        adapter = new RecyclerAdapter<String>(lines, new RecyclerAdapter.AdapterListenerImpl<String>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, String line) {
                bindLine(line);
            }
        }) {
            @Override
            protected ViewHolder<String> onCreateViewHolder(View root, int viewType) {
                return new MainActivity.ViewHolder(root);
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
//                Intent intent = new Intent("com.interview.next");     // 隐士跳转
//                startActivity(intent);
//                 startActivity(new Intent(InterviewActivity.this, Main2Activity.class));
                ARouter.getInstance().build(RouterPath.PATH_ACTIVITY).navigation();
                break;
            case LINE_1:
                startActivity(new Intent(MainActivity.this, NormalwebviewActivity.class));
                break;
            case LINE_2:
                // Toast.makeText(MainActivity.this, Utils.createUUID(), Toast.LENGTH_SHORT).show();    // UUID生成
                ARouter.getInstance().build(RouterPath.PATH_USER).navigation();
                break;
            case LINE_3:
                Toast.makeText(MainActivity.this,
                        Utils.getRunningProcess(MainActivity.this).processName, Toast.LENGTH_SHORT).show();
                break;
            case LINE_4:
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                break;
            case LINE_5:
                // myHandler.sendEmptyMessage(0);
                startActivity(new Intent(MainActivity.this, HandlerExample.class));
                break;
            case LINE_6:
                startActivity(new Intent(MainActivity.this, WebviewActivity.class));
                break;
            case LINE_7:
                for (int i = 0; i < 10000; i++) {
                    A a = new A();
                    a.say();
                }
                break;
            case LINE_8:
                startActivity(new Intent(MainActivity.this, DispatcherExampleActivity.class));
                break;
            case LINE_9:
                startActivity(new Intent(MainActivity.this, DispatcherViewActivity.class));
                break;
            case LINE_10:
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                break;
            case LINE_11:
                startActivity(new Intent(MainActivity.this, ClassloaderActivity.class));
                break;
        }
    }
    
    private static final class MyHandler extends Handler {
        private final WeakReference<MainActivity> mWeakReference;

        public MyHandler(MainActivity activity) {
            mWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(activity, "收到发送消息", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }

    class A {
        void say() {
            Log.d("CPU 抖动测试", "测试内容");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        GodEyeMonitor.shutDown();
//        GodEye.instance().uninstallAll();
    }

    class ViewHolder extends RecyclerAdapter.ViewHolder<String> {

        @BindView(R.id.text_title)
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
