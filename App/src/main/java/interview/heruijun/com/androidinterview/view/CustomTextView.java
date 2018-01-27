package interview.heruijun.com.androidinterview.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by heruijun on 2018/1/13.
 */

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Toast.makeText(this.getContext(), "textview", Toast.LENGTH_LONG).show();
            Log.w("view分发机制", "textview");
            // Log.w("event", super.onTouchEvent(event) + "");
        }
        // return super.onTouchEvent(event);
        // return true;
        return true;
    }
}
