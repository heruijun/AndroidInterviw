package interview.heruijun.com.androidinterview.view.bottomnavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import it.sephiroth.android.library.bottomnavigation.ItemsLayoutContainer;

/**
 * Created by heruijun on 2018/2/18.
 */

public class MyBottomNavigation extends BottomNavigation {

    public MyBottomNavigation(Context context) {
        super(context);
    }

    public MyBottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onItemClick(ItemsLayoutContainer parent, View view, int index, boolean animate) {
        super.onItemClick(parent, view, index, animate);
    }
}
