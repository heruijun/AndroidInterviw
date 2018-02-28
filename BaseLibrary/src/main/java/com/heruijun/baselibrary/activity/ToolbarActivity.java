package com.heruijun.baselibrary.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.heruijun.baselibrary.R;

/**
 * Created by heruijun on 2018/2/28.
 */

public abstract class ToolbarActivity extends BaseActivity {

    protected Toolbar mToolbar;

    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar((Toolbar) findViewById(R.id.toolbar));
    }

    public void initToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        initTitleNeedBack();
    }

    protected void initTitleNeedBack() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(false);
        }
    }
}
