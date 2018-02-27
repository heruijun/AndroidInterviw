package interview.heruijun.com.androidinterview.activity;

import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.heruijun.baselibrary.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/1/13.
 */

public class DispatcherViewActivity extends BaseActivity {

    @BindView(R.id.myTextView)
    AppCompatTextView mTextView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_dispatcher_view;
    }

    @OnClick(R.id.myTextView)
    void myTextViewClick() {
        Toast.makeText(DispatcherViewActivity.this, "点击", Toast.LENGTH_LONG).show();
    }
}
