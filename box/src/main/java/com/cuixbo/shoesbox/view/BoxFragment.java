package com.cuixbo.shoesbox.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.library.SuperTextView;
import com.cuixbo.lib.common.base.BaseFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Owner;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import io.objectbox.Box;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class BoxFragment extends BaseFragment {

    private static final String ARG_MEMBER = "arg_member";


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SuperTextView mNaviTitleBar;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private String[] mTitle;

    public BoxFragment() {

    }

    @SuppressWarnings("unused")
    public static BoxFragment newInstance() {
        BoxFragment fragment = new BoxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mOwner = (Owner) getArguments().getSerializable(ARG_MEMBER);
        }
        Log.e("xbc", "onCreate：" + this.hashCode());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box, container, false);
        Log.e("xbc", "onCreateView：" + this.hashCode());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListener();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("xbc", "onDestroyView：" + this.hashCode());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("xbc", "onDestroy：" + this.hashCode());
    }

    @Override
    protected void initIntent() {

    }

    @Override
    public void initView() {
        if (getView() == null) {
            return;
        }

        Log.e("xbc", "initView：" + this.hashCode());
        mTabLayout = getView().findViewById(R.id.tab_layout);
        mViewPager = getView().findViewById(R.id.view_pager);
        mNaviTitleBar = getView().findViewById(R.id.navi_title_bar);

        mNaviTitleBar.setLeftIcon(0);
        mNaviTitleBar.setCenterString("SHOES-BOX");
        List<Owner> owners;
        try {
            Box<Owner> ownerBox = ObjectBox.get().boxFor(Owner.class);
            owners = ownerBox.getAll();
            mTitle = new String[owners.size()];
            mFragments.clear();
            for (int i = 0; i < owners.size(); i++) {
                mTitle[i] = owners.get(i).name;
                mFragments.add(ShoesListFragment.newInstance(owners.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(),
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
            Intent intent = new Intent(getActivity(), EditActivity.class);
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


}
