package com.heruijun.baselibrary.fragment;

import android.content.Context;
import android.support.annotation.StringRes;

import com.heruijun.baselibrary.BaseApplication;
import com.heruijun.baselibrary.presenter.BaseContract;

/**
 * Created by heruijun on 2018/3/1.
 */

public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenter();
    }

    protected abstract Presenter initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(@StringRes int str) {
        if (mPlaceHolderView != null)
            mPlaceHolderView.triggerError(str);
        else
            BaseApplication.showToast(str);
    }

    @Override
    public void showLoading() {
        if (mPlaceHolderView != null)
            mPlaceHolderView.triggerLoading();
    }

    protected void hideLoading() {
        if (mPlaceHolderView != null)
            mPlaceHolderView.triggerOk();
    }
}
