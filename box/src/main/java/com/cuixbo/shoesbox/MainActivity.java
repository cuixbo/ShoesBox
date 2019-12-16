package com.cuixbo.shoesbox;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import com.allen.library.SuperTextView;
import com.cuixbo.lib.common.base.BaseActivity;
import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Owner;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import io.objectbox.Box;

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
public class MainActivity extends BaseActivity implements ShoesListFragment.OnListFragmentInteractionListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SuperTextView mNaviTitleBar;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private String[] mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initIntent() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.setClass(this,SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void initView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mNaviTitleBar = findViewById(R.id.navi_title_bar);

        mNaviTitleBar.setLeftIcon(0);
        mNaviTitleBar.setCenterString("SHOES-BOX");
        List<Owner> owners;
        try {
            Box<Owner> ownerBox = ObjectBox.get().boxFor(Owner.class);
            owners = ownerBox.getAll();
            mTitle = new String[owners.size() + 1];
            for (int i = 0; i < owners.size(); i++) {
                mTitle[i] = owners.get(i).name;
                mFragments.add(ShoesListFragment.newInstance(owners.get(i)));
            }
            mTitle[mTitle.length - 1] = "设置";
//            mFragments.add(MeFragment.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        mFragments.add(BoxFragment.newInstance());
//        mFragments.add(SearchFragment.newInstance());
//        mFragments.add(MeFragment.newInstance());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

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

        //去除Tab默认的Ripple效果
        if (mTabLayout.getChildCount() > 0) {
            ViewGroup tabStrip = ((ViewGroup) mTabLayout.getChildAt(0));
            if (tabStrip != null && tabStrip.getChildCount() > 0) {
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    if (tabStrip.getChildAt(i) != null) {
                        tabStrip.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        }
    }

    @Override
    public void initListener() {

        mNaviTitleBar.setRightImageViewClickListener(imageView -> {
            Intent intent = new Intent(this, EditActivity.class);
            startActivity(intent);
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

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
