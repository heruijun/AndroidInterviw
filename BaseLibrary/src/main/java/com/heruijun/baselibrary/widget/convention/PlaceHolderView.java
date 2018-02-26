package com.heruijun.baselibrary.widget.convention;

import android.support.annotation.StringRes;

/**
 * Created by heruijun on 2018/2/27.
 */
public interface PlaceHolderView {

    void triggerEmpty();

    void triggerNetError();

    void triggerError(@StringRes int strRes);

    void triggerLoading();

    void triggerOk();

    void triggerOkOrEmpty(boolean isOk);
}
