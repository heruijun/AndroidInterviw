package interview.heruijun.com.androidinterview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import interview.heruijun.com.androidinterview.service.ServiceExample;

/**
 * Created by heruijun on 2018/1/13.
 */

public class ServiceActivity extends AppCompatActivity {

    Button mStartService;
    Button mStopService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mStartService = findViewById(R.id.startService);
        mStopService = findViewById(R.id.startService);

        mStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, ServiceExample.class);
                startService(intent);
            }
        });

        mStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}
