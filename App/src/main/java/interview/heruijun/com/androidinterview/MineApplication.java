package interview.heruijun.com.androidinterview;

import com.heruijun.baselibrary.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by heruijun on 2018/1/27.
 */

public class MineApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
