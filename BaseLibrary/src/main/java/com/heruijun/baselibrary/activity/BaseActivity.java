package com.heruijun.baselibrary.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.heruijun.baselibrary.fragment.BaseFragment;
import com.heruijun.baselibrary.widget.convention.PlaceHolderView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by heruijun on 2018/1/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected PlaceHolderView mPlaceHolderView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidows();
        if (initArgs(getIntent().getExtras())) {
            setContentView(getContentLayoutId());
            initBefore();
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    void initBefore() {

    }

    protected void initWidows() {
    }

    @LayoutRes
    protected abstract int getContentLayoutId();

    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    protected void initWidget() {
        ButterKnife.bind(this);
    }

    protected void initData() {
    }

    protected final void setPlaceHolderView(PlaceHolderView view) {
        mPlaceHolderView = view;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        @SuppressWarnings("RestrictedApi")
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof BaseFragment) {
                    if (((BaseFragment) fragment).onBackPressed()) {
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
