package interview.heruijun.com.modulememory.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heruijun.baselibrary.activity.BaseActivity;
import com.heruijun.baselibrary.config.RouterPath;

import interview.heruijun.com.modulememory.R;

/**
 * Created by heruijun on 2018/3/3.
 */
@Route(path = RouterPath.PATH_MEMORY)
public class MemoryActivity extends BaseActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_memory;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }
}