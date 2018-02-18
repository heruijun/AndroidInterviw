package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
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
        mImageViewPager.setCurrentItem(1);
        mImageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {      // 监听滚动完毕

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
            "https://desk-fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/03/04/ChMkJ1oJW4iIIAZJAAfcOXA1PHcAAiH8gHsUiYAB9xR139.jpg",
            "https://desk-fd.zol-img.com.cn/t_s1440x900c5/g5/M00/03/04/ChMkJloJW36IGDpwAAd_V2iVa6wAAiH8gF1_xAAB39v037.jpg",
            "https://desk-fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/03/04/ChMkJ1oJW4WIFWWcAAs8tHMGUwUAAiH8gG5hqkACzzM660.jpg"};

//    private static final String[] urls = new String[]{
//            "https://desk-fd.zol-img.com.cn/t_s208x130c5/g5/M00/03/04/ChMkJ1oJW4iIIAZJAAfcOXA1PHcAAiH8gHsUiYAB9xR139.jpg",
//            "https://desk-fd.zol-img.com.cn/t_s208x130c5/g5/M00/0C/0D/ChMkJlnF0vKIPFlaANd0cSO7qRUAAguMgJ2LX4A13SJ836.jpg",
//            "https://desk-fd.zol-img.com.cn/t_s208x130c5/g5/M00/0E/00/ChMkJlnJ4TOIAyeVAJqtjV-XTiAAAgzDAE7v40Amq2l708.jpg"
//    };

    private static class MyAdapter extends FragmentPagerAdapter {

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

        @Override
        public int getItemPosition(@NonNull Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
