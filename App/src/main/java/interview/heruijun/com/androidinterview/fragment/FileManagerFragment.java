package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heruijun.baselibrary.fragment.BaseFragment;
import com.heruijun.baselibrary.util.glide.GlideCatchConfig;

import java.io.File;

import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/2/18.
 */

public class FileManagerFragment extends BaseFragment {

    private TextView mFiles;

    public static FileManagerFragment newInstance(String text) {
        FileManagerFragment fileManagerFragment = new FileManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fileManagerFragment.setArguments(bundle);
        return fileManagerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filemanager, container, false);
        mFiles = view.findViewById(R.id.files);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFiles.setText(showFiles());
    }

    private String showFiles() {
        String files = "";
        String pathname = getActivity().getApplicationContext().getCacheDir() + "/" + GlideCatchConfig.GLIDE_CARCH_DIR;
        File file = new File(pathname);
        if (file != null && file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                files += listFiles[i].getAbsolutePath() + "\n\n\n";
            }
        }
        return files;
    }
}
