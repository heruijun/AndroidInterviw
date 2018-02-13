package interview.heruijun.com.androidinterview.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.heruijun.baselibrary.fragment.BaseFragment;
import com.heruijun.baselibrary.util.glide.GlideApp;
import com.heruijun.baselibrary.util.glide.GlideUtil;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.activity.InterviewActivity;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by heruijun on 2018/2/13.
 */

public class GlideFragment extends Fragment implements View.OnClickListener {

    private static final String TEST_IMG = "https://oe9nbfytu.qnssl.com/images%2Fscreenshot-2.png";
    private ImageView mImage;
    private Button mLoadGlide;
    private Button mClearGlide;
    private ProgressBar mProgessBar;
    private TextView mCacheSize;

    public static GlideFragment newInstance(String text) {
        GlideFragment glideFragment = new GlideFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        glideFragment.setArguments(bundle);
        return glideFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_glide, container, false);
        mImage = view.findViewById(R.id.image);
        mLoadGlide = view.findViewById(R.id.load_img);
        mClearGlide = view.findViewById(R.id.clear);
        mProgessBar = view.findViewById(R.id.progressBar);
        mCacheSize = view.findViewById(R.id.cache_size);

        mLoadGlide.setOnClickListener(this);
        mClearGlide.setOnClickListener(this);
        mCacheSize.setText(GlideUtil.getCacheSize(getActivity()));

        // GlideUtil.loadImage(getContext(), TEST_IMG, mImage);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.load_img:
                mProgessBar.setVisibility(View.VISIBLE);
                GlideApp.with(getActivity())
                        .load(TEST_IMG)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .skipMemoryCache(true)
                        //.miniThumb(1000)
                        //.centerCrop()
                        .transition(withCrossFade())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                mProgessBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                Log.e("加载性质：", isFirstResource + "");
                                mProgessBar.setVisibility(View.GONE);
                                mCacheSize.setText(GlideUtil.getCacheSize(getActivity()));
                                return false;
                            }
                        })
                        .into(mImage);
                break;
            case R.id.clear:
                GlideUtil.clearGlideCache(getActivity());
                mCacheSize.setText(GlideUtil.getCacheSize(getActivity()));
                break;
        }
    }
}
