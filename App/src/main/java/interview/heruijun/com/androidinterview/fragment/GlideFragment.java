package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heruijun.baselibrary.fragment.BaseFragment;

import interview.heruijun.com.androidinterview.R;

/**
 * Created by heruijun on 2018/2/13.
 */

public class GlideFragment extends BaseFragment implements View.OnClickListener {

    private MyAdapter myAdapter;
    private ViewPager mImageViewPager;

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
        mImageViewPager = view.findViewById(R.id.imageViewPager);

        myAdapter = new MyAdapter(getFragmentManager());
        mImageViewPager.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.load_img:
//                mProgessBar.setVisibility(View.VISIBLE);
//                GlideApp.with(getActivity())
//                        .load(TEST_IMG)
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                        .skipMemoryCache(true)
//                        //.miniThumb(1000)
//                        //.centerCrop()
//                        .transition(withCrossFade())
//                        .listener(new RequestListener<Drawable>() {
//                            @Override
//                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                mProgessBar.setVisibility(View.GONE);
//                                return false;
//                            }
//
//                            @Override
//                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                Log.e("加载性质：", isFirstResource + "");
//                                mProgessBar.setVisibility(View.GONE);
//                                mCacheSize.setText(GlideUtil.getCacheSize(getActivity()));
//                                return false;
//                            }
//                        })
//                        .into(mImage);
//                break;
//            case R.id.clear:
//                GlideUtil.clearGlideCache(getActivity());
//                mCacheSize.setText(GlideUtil.getCacheSize(getActivity()));
//                break;
        }
    }

    private static final String[] urls = new String[]{
            "http://pic1.win4000.com/tj/2017-10-10/59dc58a141b73.jpg",
            "http://pic1.win4000.com/tj/2018-02-09/5a7d5f2dd4a66.jpg",
            "http://pic1.win4000.com/tj/2018-01-17/5a5ebeac8b4a0.jpg"};

    private static class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageViewFragment.newInstance(urls[position]);
        }

        @Override
        public int getCount() {
            return urls.length;
        }
    }
}
