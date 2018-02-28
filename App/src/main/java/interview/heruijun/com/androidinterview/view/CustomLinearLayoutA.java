package interview.heruijun.com.androidinterview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by heruijun on 2018/1/13.
 */

public class CustomLinearLayoutA extends LinearLayout {
    public CustomLinearLayoutA(Context context) {
        super(context);
    }

    public CustomLinearLayoutA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayoutA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // return super.onInterceptTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Toast.makeText(this.getContext(), "custom view a", Toast.LENGTH_LONG).show();
            Log.w("view分发机制", "custom view a");
        }
        // return super.onTouchEvent(event);
        // return false;
        return false;
    }
}
