package interview.heruijun.com.androidinterview.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.view.DispatcherLinearLayout;

/**
 * Created by heruijun on 2018/1/13.
 */

public class DispatcherExampleActivity extends AppCompatActivity {

    private ListView mListView;
    private DispatcherAdapter mAdapter;
    private List<Item> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher_example);

        initData();
        mListView = findViewById(R.id.dispatcher_list);
        mAdapter = new DispatcherAdapter(DispatcherExampleActivity.this, mList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DispatcherExampleActivity.this, "整行触发", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initData() {
        Item i1 = new Item();
        i1.title = "你好";
        i1.subTitle = "你好啊";
        Item i2 = new Item();
        i2.title = "下雨了";
        i2.subTitle = "";
        Item i3 = new Item();
        i3.title = "打篮球";
        i3.subTitle = "踢足球";
        mList.add(i1);
        mList.add(i2);
        mList.add(i3);
    }

    private static class DispatcherAdapter extends BaseAdapter {

        private Context mContext;
        private List<Item> mList;

        public DispatcherAdapter(Context context, List<Item> list) {
            this.mContext = context;
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Item item = mList.get(position);
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_dispatcher_example,
                        parent, false);
            } else {
                view = convertView;
            }
            DispatcherLinearLayout dispatcherLinearLayout = view.findViewById(R.id.dispatcher_layout);
            TextView title = view.findViewById(R.id.text1);
            title.setText(item.title);
            final TextView subTitle = view.findViewById(R.id.text2);
            if (!TextUtils.isEmpty(item.subTitle)) {
                subTitle.setText(item.subTitle);
                dispatcherLinearLayout.setCanDispatcher(false);
                subTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, item.subTitle, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                dispatcherLinearLayout.setCanDispatcher(true);
            }
            return view;
        }
    }

    class Item {
        String title;
        String subTitle;
    }

}
