package interview.heruijun.com.androidinterview.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存泄漏模拟
 * Created by heruijun on 2018/1/17.
 */

public class ActivityMgr {

    private static ActivityMgr sInstance = new ActivityMgr();
    List<Activity> activities = new ArrayList<>();

    private ActivityMgr() {

    }

    public static ActivityMgr getInstance() {
        return sInstance;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

}
