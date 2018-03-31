package com.heruijun.baselibrary;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by heruijun on 2018/3/31.
 */

public class RxManager {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * 添加observer
     *
     * @param observer
     */
    public void addObserver(DisposableObserver observer) {
        if (observer != null) {
            compositeDisposable.add(observer);
        }
    }

    public void clear() {
        compositeDisposable.clear();
    }
}
