package interview.heruijun.com.androidinterview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by heruijun on 2018/1/13.
 */

public class CustomLinearLayoutB extends LinearLayout {
    public CustomLinearLayoutB(Context context) {
        super(context);
    }

    public CustomLinearLayoutB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayoutB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // return super.onInterceptTouchEvent(ev);
        // return true;
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Toast.makeText(this.getContext(), "custom view b", Toast.LENGTH_LONG).show();
            Log.w("view分发机制", "custom view b");
        }
        // return super.onTouchEvent(event);
        // return false;
        return true;
    }
}
