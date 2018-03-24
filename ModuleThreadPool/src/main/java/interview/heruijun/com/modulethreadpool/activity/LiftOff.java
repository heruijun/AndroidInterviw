package interview.heruijun.com.modulethreadpool.activity;

import android.util.Log;

/**
 * Created by heruijun on 2018/3/7.
 */

public class LiftOff implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "Thread ID: [" + String.format("%3d", Thread.currentThread().getId()) + "] #" + id +
                "(" + (countDown > 0 ? countDown : "LiftOff!") + ") ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            Log.e("ec", status());
            Thread.yield();
        }
    }

}