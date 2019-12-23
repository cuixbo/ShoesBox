package com.cuixbo.shoesbox.view;

import android.content.Intent;
import android.os.Bundle;

import com.cuixbo.lib.common.base.BaseMvpActivity;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.StatusBarUtil;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.presenter.MainPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 1.list
 * 2.detail+edit
 * 3.me
 * 4.setting
 * 5.splash
 *
 * @author xiaobocui
 * @date 2019-12-09
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> implements ShoesListFragment.OnListFragmentInteractionListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private String[] mTitle = new String[]{"盒子", "查找", "我的"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPresenter(new MainPresenter());
        // 将StatusBar文字颜色设为深色
        StatusBarUtil.setStatusBarLightMode(getWindow(), true);
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);

        mFragments.add(BoxFragment.newInstance());
        mFragments.add(SearchFragment.newInstance());
        mFragments.add(MeFragment.newInstance());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mTabLayout.setTabRippleColor(null);
    }

    @Override
    public void initListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //这里开启的话，可以控制Activity中不同Fragment的StatusBar文字颜色模式
//                StatusBarUtil.setStatusBarLightMode(getWindow(), !TextUtils.equals(tab.getText(), "我的"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onListFragmentInteraction(Shoes item) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("shoes_id", item.id);
        startActivity(intent);
    }

}
