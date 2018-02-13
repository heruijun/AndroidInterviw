package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.heruijun.baselibrary.fragment.BaseFragment;
import com.heruijun.baselibrary.util.glide.GlideApp;
import com.heruijun.baselibrary.util.glide.GlideUtil;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.activity.InterviewActivity;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by heruijun on 2018/2/13.
 */

public class GlideFragment extends BaseFragment {

    private static final String TEST_IMG = "https://www.baidu.com/img/bd_logo1.png";
    private ImageView mImage;
    private AppCompatButton mLoadGlide;
    private AppCompatButton mClearGlide;

    public static GlideFragment newInstance(String text) {
        GlideFragment glideFragment = new GlideFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        glideFragment.setArguments(bundle);
        return glideFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_glide, container, false);
        mImage = view.findViewById(R.id.image);
        mClearGlide = view.findViewById(R.id.clearGlide);
        mLoadGlide = view.findViewById(R.id.loadGlide);

        GlideUtil.loadImage(getContext(), TEST_IMG, mImage);

        mClearGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideUtil.clearCache(getActivity());
            }
        });

        mLoadGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideUtil.loadImage(getContext(), TEST_IMG, mImage);
            }
        });
        return view;
    }
}
