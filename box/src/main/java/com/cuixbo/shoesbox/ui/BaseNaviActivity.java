package com.cuixbo.shoesbox.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.cuixbo.lib.common.mvp.BaseMvpActivity;
import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.shoesbox.widget.NaviTitleBar;

/**
 * @author xiaobocui
 * @date 2020-01-02
 */
public abstract class BaseNaviActivity<P extends IPresenter> extends BaseMvpActivity<P> {

    @Override
    public P setPresenter() {
        return null;
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(wrapContentView(layoutResId));
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

    private View wrapContentView(int layoutResId) {
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.setFitsSystemWindows(true);
        NaviTitleBar naviTitleBar = new NaviTitleBar(this);
        naviTitleBar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 44 * 3));
        wrapper.addView(naviTitleBar);
        wrapper.addView(LayoutInflater.from(this).inflate(layoutResId, wrapper, false));
        return wrapper;
    }

}
