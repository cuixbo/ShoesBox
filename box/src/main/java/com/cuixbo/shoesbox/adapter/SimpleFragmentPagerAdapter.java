package com.cuixbo.shoesbox.adapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author xiaobocui
 * @date 2019-12-25
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mTitles;

    public SimpleFragmentPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
