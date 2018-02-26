package com.heruijun.baselibrary.presenter;

import android.support.annotation.StringRes;

import com.heruijun.baselibrary.recycler.RecyclerAdapter;

/**
 * Created by heruijun on 2018/2/26.
 */

public interface BaseContract {

    interface Presenter {
        void start();

        void destroy();
    }

    interface View<T extends Presenter> {
        void setPresenter(T presenter);

        void showError(@StringRes int str);

        void showLoading();
    }

    interface RecycleView<ViewModel, T extends Presenter> extends View<T> {
        void onAdapterDataChanged();

        RecyclerAdapter<ViewModel> getRecyclerAdapter();

        void scrollRecyclerToPosition(int position);
    }
}
