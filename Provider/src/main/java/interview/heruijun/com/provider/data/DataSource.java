package interview.heruijun.com.provider.data;

import android.support.annotation.StringRes;

/**
 * Created by heruijun on 2018/3/1.
 */

public interface DataSource {
    /**
     * 数据请求回调
     * 用于Presenter与DataSource之间交互
     *
     * @param <T> 返回类型
     */
    interface Callback<T> extends SucceedCallback<T>, FailedCallback {
    }

    interface SucceedCallback<T> {
        /**
         * 当数据加载成功时回调
         *
         * @param t 成功参数
         */
        void onDataLoaded(T t);
    }

    interface FailedCallback {
        /**
         * 当数据请求失败时回调
         */
        void onDataNotAvailable(@StringRes int strRes);
    }

    void dispose();
}