package com.heruijun.baselibrary.recycler;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heruijun.baselibrary.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by heruijun on 2018/2/26.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>
        implements AdapterCallback<Data>, View.OnClickListener, View.OnLongClickListener {
    private final List<Data> mDataList;
    private AdapterListener<Data> mListener;

    public RecyclerAdapter() {
        this(new ArrayList<Data>(), null);
    }

    public RecyclerAdapter(AdapterListener<Data> listener) {
        this(new ArrayList<Data>(), listener);
    }

    @SuppressWarnings("WeakerAccess")
    public RecyclerAdapter(List<Data> dataList, AdapterListener<Data> listener) {
        super();
        this.mDataList = dataList;
        this.mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mDataList.get(position));
    }

    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(viewType, parent, false);
        ViewHolder<Data> holder = onCreateViewHolder(root, viewType);
        root.setTag(R.id.tag_recycler_holder, holder);
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        holder.unbinder = ButterKnife.bind(holder, root);
        holder.callback = this;

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position) {
        Data data = mDataList.get(position);
        holder.bind(data);
    }

    @SuppressWarnings("WeakerAccess")
    protected abstract ViewHolder<Data> onCreateViewHolder(View root, int viewType);

    @SuppressWarnings("WeakerAccess")
    @LayoutRes
    protected abstract int getItemViewType(int position, Data data);

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<Data> getItems() {
        return mDataList;
    }

    public void replace(Collection<Data> dataList) {
        mDataList.clear();
        if (dataList == null || dataList.size() == 0)
            return;
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    public final void add(Data data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size() - 1);
    }

    @SafeVarargs
    public final void add(Data... dataList) {
        if (dataList == null || dataList.length == 0)
            return;
        int startPos = mDataList.size() - 1;
        Collections.addAll(mDataList, dataList);
        notifyItemRangeInserted(startPos, dataList.length);
    }

    public final void add(Collection<Data> dataList) {
        if (dataList == null || dataList.size() == 0)
            return;
        int startPos = mDataList.size() - 1;
        mDataList.addAll(dataList);
        notifyItemRangeInserted(startPos, dataList.size());
    }

    @Override
    public void onClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (mListener != null)
            mListener.onItemClick(viewHolder, mDataList.get(viewHolder.getAdapterPosition()));
    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (mListener != null) {
            mListener.onItemLongClick(viewHolder, mDataList.get(viewHolder.getAdapterPosition()));
            return true;
        }
        return false;
    }

    public void setListener(AdapterListener<Data> listener) {
        this.mListener = listener;
    }

    @Override
    public void update(Data data, ViewHolder<Data> holder) {
        int pos = holder.getAdapterPosition();
        if (pos >= 0) {
            mDataList.remove(pos);
            mDataList.add(pos, data);
            notifyItemChanged(pos);
        }
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    public interface AdapterListener<Data> {
        void onItemClick(RecyclerAdapter.ViewHolder holder, Data data);

        void onItemLongClick(RecyclerAdapter.ViewHolder holder, Data data);
    }

    public abstract static class AdapterListenerImpl<Data> implements AdapterListener<Data> {

        @Override
        public void onItemClick(ViewHolder holder, Data data) {

        }

        @Override
        public void onItemLongClick(ViewHolder holder, Data data) {

        }
    }

    @SuppressWarnings("WeakerAccess")
    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder {
        private Unbinder unbinder;
        private AdapterCallback<Data> callback;

        protected Data mData;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        void bind(Data data) {
            this.mData = data;
            onBind(data);
        }

        protected abstract void onBind(Data data);

        public void updateData(Data data) {
            if (this.callback != null)
                this.callback.update(data, this);
        }
    }

}
