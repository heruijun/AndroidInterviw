package com.heruijun.baselibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heruijun.baselibrary.widget.convention.PlaceHolderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by heruijun on 2018/2/13.
 */

public abstract class BaseFragment extends Fragment {

    protected PlaceHolderView mPlaceHolderView;
    private boolean mFirstInit = true;
    protected View mRoot;
    protected Unbinder mRootUnBinder;

    protected boolean isSaveView() {
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = mRoot;
        if (root == null) {
            // Inflate the layout for this fragment
            int layId = getContentLayoutId();
            root = inflater.inflate(layId, container, false);
            initWidget(root);
        } else {
            if (root.getParent() != null)
                ((ViewGroup) root.getParent()).removeView(root);
        }
        return (mRoot = root);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        if (mFirstInit) {
            mFirstInit = false;
            onFirstInit();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirstInit = true;
    }

    protected void initArgs(Bundle args) {

    }

    @LayoutRes
    protected abstract int getContentLayoutId();

    protected void initWidget(View root) {
        mRootUnBinder = ButterKnife.bind(this, root);
    }

    protected void initData() {

    }

    protected void onFirstInit() {

    }

    protected final void setPlaceHolderView(PlaceHolderView view) {
        mPlaceHolderView = view;
    }

    /**
     * 拦截返回键，如果返回False则代表不处理事物，返回True则代表拦截处理事物
     *
     * @return 是否拦截返回
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!isSaveView() && mRoot != null) {
            if (mRootUnBinder != null)
                mRootUnBinder.unbind();
            mRoot = null;
        }
    }
}
