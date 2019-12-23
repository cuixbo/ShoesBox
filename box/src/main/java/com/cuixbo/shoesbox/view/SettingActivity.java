package com.cuixbo.shoesbox.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.cuixbo.lib.common.base.BaseMvpActivity;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.StatusBarUtil;

/**
 * @author xiaobocui
 * @date 2019-12-16
 */
public class SettingActivity extends BaseMvpActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarUtil.setStatusBarLightMode(getWindow(), true);
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
