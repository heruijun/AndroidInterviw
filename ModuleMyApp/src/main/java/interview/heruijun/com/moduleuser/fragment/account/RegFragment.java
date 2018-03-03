package interview.heruijun.com.moduleuser.fragment.account;

import android.content.Context;

import com.heruijun.baselibrary.fragment.BaseFragment;

import butterknife.OnClick;
import interview.heruijun.com.moduleuser.R;
import interview.heruijun.com.moduleuser.R2;

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

    @OnClick(R2.id.to_login)
    void toRegView() {
        accountListener.triggerView();
    }
}
