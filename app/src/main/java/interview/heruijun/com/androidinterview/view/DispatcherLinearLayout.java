package interview.heruijun.com.androidinterview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by heruijun on 2018/1/13.
 */

public class DispatcherLinearLayout extends LinearLayout {

    private boolean canDispatcher = true;

    public void setCanDispatcher(boolean canDispatcher) {
        this.canDispatcher = canDispatcher;
    }

    public DispatcherLinearLayout(Context context) {
        super(context);
    }

    public DispatcherLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatcherLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return !canDispatcher;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !canDispatcher;
    }
}
