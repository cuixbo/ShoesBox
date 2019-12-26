package com.cuixbo.shoesbox.ui;

import android.content.Intent;
import android.os.Bundle;

import com.cuixbo.lib.common.mvp.BaseMvpActivity;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.adapter.SimpleFragmentPagerAdapter;
import com.cuixbo.shoesbox.contract.MainContract;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.interf.OnListFragmentInteractionListener;
import com.cuixbo.shoesbox.presenter.MainPresenter;
import com.cuixbo.shoesbox.util.StatusBarUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View, OnListFragmentInteractionListener<Shoes> {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[]{"盒子", "查找", "我的"};

    @Override
    public MainPresenter setPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        mViewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
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
