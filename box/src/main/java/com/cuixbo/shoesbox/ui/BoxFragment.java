package com.cuixbo.shoesbox.ui;

import android.content.Intent;
import android.widget.TextView;

import com.cuixbo.lib.common.mvp.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.adapter.SimpleFragmentPagerAdapter;
import com.cuixbo.shoesbox.contract.BoxContract;
import com.cuixbo.shoesbox.presenter.BoxPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class BoxFragment extends BaseMvpFragment<BoxPresenter> implements BoxContract.View {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mTvAdd;

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[0];

    @SuppressWarnings("unused")
    public static BoxFragment newInstance() {
        return new BoxFragment();
    }

    @Override
    public BoxPresenter setPresenter() {
        return new BoxPresenter();
    }

    @Override
    @LayoutRes
    public int setContentView() {
        return R.layout.fragment_box;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    public void initView() {
        if (getView() == null) {
            return;
        }
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mTvAdd = findViewById(R.id.tv_add);

        mTabLayout.setTabRippleColor(null);

        // 根据主人进行Tab分区
        mPresenter.loadOwners();
    }

    @Override
    public void initListener() {
        mTvAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void setupViewPager(List<Fragment> fragments, String[] titles) {
        this.mFragments = fragments;
        this.mTitles = titles;
        mViewPager.setAdapter(new SimpleFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitles));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }
}
