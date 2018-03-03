package interview.heruijun.com.moduleuser.activity;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heruijun.baselibrary.activity.BaseActivity;
import com.heruijun.baselibrary.config.RouterPath;

import interview.heruijun.com.moduleuser.R;
import interview.heruijun.com.moduleuser.fragment.account.AccountListener;
import interview.heruijun.com.moduleuser.fragment.account.LoginFragment;
import interview.heruijun.com.moduleuser.fragment.account.RegFragment;

@Route(path = RouterPath.PATH_USER)
public class AccountActivity extends BaseActivity implements AccountListener {

    private Fragment mLoginFragment;
    private Fragment mRegFragment;
    private Fragment mCurFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mLoginFragment = new LoginFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.app_content, mLoginFragment)
                .commit();
        mCurFragment = mLoginFragment;
    }

    @Override
    public void triggerView() {
        Fragment fragment;
        if (mCurFragment == mLoginFragment) {
            if (mRegFragment == null)
                mRegFragment = new RegFragment();
            fragment = mRegFragment;
        } else {
            fragment = mLoginFragment;
        }
        mCurFragment = fragment;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.app_content, fragment)
                .commit();
    }
}
