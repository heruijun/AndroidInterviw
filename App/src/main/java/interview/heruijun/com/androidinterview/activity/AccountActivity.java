package interview.heruijun.com.androidinterview.activity;

import android.support.v4.app.Fragment;

import com.heruijun.baselibrary.activity.BaseActivity;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.fragment.account.AccountListener;
import interview.heruijun.com.androidinterview.fragment.account.LoginFragment;
import interview.heruijun.com.androidinterview.fragment.account.RegFragment;

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
