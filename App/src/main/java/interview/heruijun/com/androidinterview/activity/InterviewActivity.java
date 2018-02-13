package interview.heruijun.com.androidinterview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.fragment.GlideFragment;
import interview.heruijun.com.androidinterview.helper.CustomBadgeProvider;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import com.heruijun.baselibrary.config.RouterPath;

@Route(path = RouterPath.PATH_ACTIVITY)
public class InterviewActivity extends AppCompatActivity implements BottomNavigation.OnMenuItemSelectionListener {

    private BottomNavigation mBottomNavigation;
    private FragmentManager fm;
    private GlideFragment glideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        glideFragment = GlideFragment.newInstance("测试");
        fragmentTransaction.add(R.id.content, glideFragment);
        fragmentTransaction.commit();

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
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position, final boolean fromUser) {

    }

}
