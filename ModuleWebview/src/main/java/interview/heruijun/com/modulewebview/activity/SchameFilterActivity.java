package interview.heruijun.com.modulewebview.activity;

import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.heruijun.baselibrary.activity.BaseActivity;
import com.heruijun.baselibrary.config.RouterPath;

import interview.heruijun.com.modulewebview.R;


@Route(path = RouterPath.PATH_SCHAMEFILTER)
public class SchameFilterActivity extends BaseActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_normalwebview;
    }

    @Override
    protected void initData() {
        super.initData();
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}
