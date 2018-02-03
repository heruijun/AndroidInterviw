package interview.heruijun.com.androidinterview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.helper.CustomBadgeProvider;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import com.heruijun.baselibrary.config.RouterPath;

@Route(path = RouterPath.PATH_ACTIVITY)
public class InterviewActivity extends AppCompatActivity implements BottomNavigation.OnMenuItemSelectionListener {

//    private ImageView mImage;
//    private AppCompatButton mLoadGlide;
//    private AppCompatButton mClearGlide;
//    private static final String TEST_IMG = "https://www.baidu.com/img/bd_logo1.png";
    private BottomNavigation mBottomNavigation;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBottomNavigation = findViewById(R.id.BottomNavigation);
        mBottomNavigation.setOnMenuItemClickListener(this);

//        mImage = findViewById(R.id.image);
//        mClearGlide = findViewById(R.id.clearGlide);
//        mLoadGlide = findViewById(R.id.loadGlide);
//
//        GlideUtil.loadImage(this, TEST_IMG, mImage);
//
//        mClearGlide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GlideUtil.clearCache(InterviewActivity.this);
//            }
//        });
//
//        mLoadGlide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GlideUtil.loadImage(InterviewActivity.this, TEST_IMG, mImage);
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent("com.interview.next");     // 隐士跳转
//                startActivity(intent);
//                 startActivity(new Intent(InterviewActivity.this, Main2Activity.class));

                int selectedIndex = mBottomNavigation.getSelectedIndex() + 1;
                final int totalCount = mBottomNavigation.getMenuItemCount();

                if (selectedIndex >= totalCount) {
                    selectedIndex = 0;
                }

                final int itemId = mBottomNavigation.getMenuItemId(selectedIndex);

                CustomBadgeProvider provider = (CustomBadgeProvider) mBottomNavigation.getBadgeProvider();

                if (provider.hasBadge(itemId)) {
                    int count = provider.getBadgeTextCount(itemId);
                    provider.show(itemId, count + 1);
                } else {
                    provider.show(itemId, 1);
                }
            }
        });

        initializeBottomNavigation(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("activity status: ", "on new intent");
    }

    protected void initializeBottomNavigation(final Bundle savedInstanceState) {
        mBottomNavigation.setDefaultSelectedIndex(0);

        CustomBadgeProvider provider = (CustomBadgeProvider) mBottomNavigation.getBadgeProvider();
        provider.show(R.id.bbn_item3, 1);
    }

    @Override
    public void onMenuItemSelect(final int itemId, final int position, final boolean fromUser) {
        if (fromUser) {
            mBottomNavigation.getBadgeProvider().remove(itemId);
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position, final boolean fromUser) {

    }
}
