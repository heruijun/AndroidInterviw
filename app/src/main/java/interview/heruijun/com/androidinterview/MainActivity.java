package interview.heruijun.com.androidinterview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import interview.heruijun.com.androidinterview.activity.DispatcherExampleActivity;
import interview.heruijun.com.androidinterview.normalwebview.NormalwebviewActivity;
import interview.heruijun.com.androidinterview.util.Utils;
import interview.heruijun.com.androidinterview.webview.WebviewActivity;

/**
 * Created by heruijun on 2018/1/5.
 */

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private static final String LINE_1 = "普通webview";
    private static final String LINE_2 = "生成UUID";
    private static final String LINE_3 = "获取当前进程";
    private static final String LINE_4 = "隐式跳转";
    private static final String LINE_5 = "Handler正常用法";
    private static final String LINE_6 = "腾讯webview";
    private static final String LINE_7 = "内存抖动";
    private static final String LINE_8 = "列表的事件分发";

    private List<String> lines = Arrays.asList(LINE_1, LINE_2, LINE_3, LINE_4, LINE_5, LINE_6, LINE_7, LINE_8);

    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, lines);
        setListAdapter(adapter);
        this.getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String lineName = lines.get(position);
        Intent intent = null;
        switch (lineName) {
            case LINE_1:
                startActivity(new Intent(MainActivity.this, NormalwebviewActivity.class));
                break;
            case LINE_2:
                Toast.makeText(MainActivity.this, Utils.createUUID(), Toast.LENGTH_SHORT).show();
                break;
            case LINE_3:
                Toast.makeText(MainActivity.this,
                        Utils.getRunningProcess(MainActivity.this).processName, Toast.LENGTH_SHORT).show();
                break;
            case LINE_4:
                intent = new Intent("com.interview.next");
                startActivity(intent);
                break;
            case LINE_5:
                myHandler.sendEmptyMessage(0);
                break;
            case LINE_6:
                intent = new Intent(MainActivity.this, WebviewActivity.class);
                startActivity(intent);
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
}
