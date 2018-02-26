package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;

import com.heruijun.baselibrary.fragment.BaseFragment;

import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/2/18.
 */

public class FileManagerFragment extends BaseFragment {

    public static FileManagerFragment newInstance(String text) {
        FileManagerFragment fileManagerFragment = new FileManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fileManagerFragment.setArguments(bundle);
        return fileManagerFragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_filemanager;
    }
}
