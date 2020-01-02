package com.cuixbo.shoesbox.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.util.StatusBarUtil;

/**
 * @author xiaobocui
 * @date 2019-12-16
 */
public class SettingActivity extends BaseNaviActivity {

    @Override
    public IPresenter setPresenter() {
        return null;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarUtil.setStatusBarLightMode(getWindow(), true);
//        NaviTitleBar naviTitleBar = findViewById(R.id.navi_title_bar);
//        naviTitleBar.setTitle("设计模式");
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }
}
