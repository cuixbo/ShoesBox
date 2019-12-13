package com.xbc.douban;


import android.os.Bundle;

import com.xbc.douban.base.BaseActivity;
import com.xbc.douban.movie.fragment.HotMovieFragment;

import androidx.fragment.app.FragmentManager;


public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fl_fragment_container, new HotMovieFragment()).commitAllowingStateLoss();
    }

    @Override
    protected void initListener() {

    }

}
