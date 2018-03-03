package interview.heruijun.com.moduleuser.fragment.account;

import android.content.Context;
import android.view.View;

import com.heruijun.baselibrary.fragment.BaseFragment;

import butterknife.OnClick;
import interview.heruijun.com.moduleuser.R;
import interview.heruijun.com.moduleuser.R2;

/**
 * Created by heruijun on 2018/2/27.
 */

public class LoginFragment extends BaseFragment {

    private AccountListener accountListener;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        accountListener = (AccountListener) context;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        // mLink.setText("aaa");
    }

    @SuppressWarnings("unused")
    @OnClick(R2.id.to_reg)
    void toRegView() {
        accountListener.triggerView();
    }
}
