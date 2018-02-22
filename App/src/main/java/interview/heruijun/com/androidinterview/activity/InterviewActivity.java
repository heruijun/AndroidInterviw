package interview.heruijun.com.androidinterview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heruijun.baselibrary.config.RouterPath;

import java.util.HashSet;
import java.util.Set;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.fragment.FileManagerFragment;
import interview.heruijun.com.androidinterview.fragment.GlideFragment;
import interview.heruijun.com.androidinterview.helper.CustomBadgeProvider;
import interview.heruijun.com.androidinterview.util.AppPersistenceAPI;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

@Route(path = RouterPath.PATH_ACTIVITY)
public class InterviewActivity extends AppCompatActivity implements BottomNavigation.OnMenuItemSelectionListener {

    private BottomNavigation mBottomNavigation;
    private FragmentManager fm;
    private Fragment mCurrent;
    private GlideFragment glideFragment;
    private FileManagerFragment managerFragment;
    private Set<String> imgSet = new HashSet<>();
    private AppPersistenceAPI appPersistenceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appPersistenceAPI = new AppPersistenceAPI(this);
        imgSet = getCachedImg();
        fm = getSupportFragmentManager();

        initializeBottomNavigation(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("activity status: ", "on new intent");
    }

    protected void initializeBottomNavigation(final Bundle savedInstanceState) {
        mBottomNavigation = findViewById(R.id.BottomNavigation);
        mBottomNavigation.setOnMenuItemClickListener(this);
        mBottomNavigation.setDefaultSelectedIndex(0);
        swtchFragmentOnMenuSelect(R.id.bbn_item1);

        CustomBadgeProvider provider = (CustomBadgeProvider) mBottomNavigation.getBadgeProvider();
        provider.show(R.id.bbn_item3, 1);

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
    }

    @Override
    public void onMenuItemSelect(final int itemId, final int position, final boolean fromUser) {
        if (fromUser) {
            mBottomNavigation.getBadgeProvider().remove(itemId);
            swtchFragmentOnMenuSelect(itemId);
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position, final boolean fromUser) {

    }

    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    /**
     * 根据选择的menu，切换Fragment
     *
     * @param itemId
     */
    private void swtchFragmentOnMenuSelect(int itemId) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        switch (itemId) {
            case R.id.bbn_item1:
                hideFragment(managerFragment, fragmentTransaction);
                if (glideFragment == null) {
                    glideFragment = GlideFragment.newInstance("测试");
                    fragmentTransaction.add(R.id.content, glideFragment);
                } else {
                    mCurrent = glideFragment;
                    fragmentTransaction.show(glideFragment);
                }
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case R.id.bbn_item2:
                hideFragment(glideFragment, fragmentTransaction);
                if (managerFragment == null) {
                    managerFragment = FileManagerFragment.newInstance("测试");
                    fragmentTransaction.add(R.id.content, managerFragment);
                } else {
                    mCurrent = managerFragment;
                    fragmentTransaction.show(managerFragment);
                }
                fragmentTransaction.commitAllowingStateLoss();
                break;
        }
    }

    public Set<String> getImageSet() {
        return imgSet;
    }

    public void cacheImg(String imgUrl) {
        imgSet.add(imgUrl);
        appPersistenceAPI.setCachedImageSet(imgSet);
    }

    public Set<String> getCachedImg() {
        return appPersistenceAPI.getCachedImageSet();
    }

}
