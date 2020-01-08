package com.cuixbo.shoesbox.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.lib.dialog.BottomDialog;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarUtil.setStatusBarLightMode(getWindow(), true);
        setTitle("设置");
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

    @Override
    public void onMenuClick() {
        new BottomDialog.Builder(this)
                .addItems("关于", "设置")
                .setOnItemClickListener(new BottomDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(Dialog dialog, View view, int which, String text) {

                    }

                    @Override
                    public void onCancel() {

                    }
                })
                .create()
                .show(getSupportFragmentManager());
    }
}
