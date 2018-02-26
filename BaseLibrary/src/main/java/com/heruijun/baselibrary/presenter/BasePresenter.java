package com.heruijun.baselibrary.presenter;

/**
 * Created by heruijun on 2018/2/27.
 */
public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {

    protected T mView;

    protected BasePresenter(T view) {
        setView(view);
    }

    protected void setView(T view) {
        mView = view;
        mView.setPresenter(this);
    }

    protected T getView() {
        return mView;
    }

    @Override
    public void start() {
        if (mView != null) {
            mView.showLoading();
        }
    }

    @Override
    public void destroy() {
        if (mView != null)
            mView.setPresenter(null);
        mView = null;
    }
}
