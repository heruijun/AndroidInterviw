package com.heruijun.baselibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by heruijun on 2018/3/3.
 */

public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, String info) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), info, Toast.LENGTH_SHORT);
        }
        toast.setText(info);
        toast.show();
    }
}
