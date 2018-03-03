package interview.heruijun.com.moduleuser.fragment.account;

import android.content.Context;

import com.heruijun.baselibrary.fragment.BaseFragment;

import interview.heruijun.com.moduleuser.R;

/**
 * Created by heruijun on 2018/2/27.
 */

public class RegFragment extends BaseFragment {

    private AccountListener accountListener;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_reg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        accountListener = (AccountListener) context;
    }

//    @OnClick(R.id.to_login)
//    void toRegView() {
//        accountListener.triggerView();
//    }
}
