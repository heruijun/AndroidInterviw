package interview.heruijun.com.androidinterview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.heruijun.baselibrary.activity.BaseActivity;

import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/1/13.
 */

public class DispatcherViewActivity extends BaseActivity {

    private AppCompatTextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher_view);

        mTextView = findViewById(R.id.myTextView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DispatcherViewActivity.this, "点击", Toast.LENGTH_LONG).show();
            }
        });
    }

}
