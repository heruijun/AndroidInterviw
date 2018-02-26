package com.heruijun.baselibrary.recycler;

/**
 * Created by heruijun on 2018/2/26.
 */
interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
