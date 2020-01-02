package com.cuixbo.shoesbox.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.cuixbo.lib.common.mvp.BaseMvpActivity;
import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.widget.NaviTitleBar;

/**
 * @author xiaobocui
 * @date 2020-01-02
 */
public abstract class BaseNaviActivity<P extends IPresenter> extends BaseMvpActivity<P> {
    private NaviTitleBar mNaviTitleBar;

    @Override
    public P setPresenter() {
        return null;
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(wrapContentView(layoutResId));
    }

    /**
     * 使用LinearLayout添加NaviTitleBar，与ContentView线性排列
     *
     * @param layoutResId ContentView布局id
     * @return 页面跟布局 LinearLayout
     */
    private View wrapContentView(int layoutResId) {
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.setFitsSystemWindows(true);
        mNaviTitleBar = createNaviTitleBar();
        wrapper.addView(mNaviTitleBar);
        wrapper.addView(LayoutInflater.from(this).inflate(layoutResId, wrapper, false));
        return wrapper;
    }

    private NaviTitleBar createNaviTitleBar() {
        NaviTitleBar naviTitleBar = new NaviTitleBar(this);
        naviTitleBar.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        (int) getResources().getDimension(R.dimen.nav_height)
                )
        );
        naviTitleBar.setOnNaviClickListener(new NaviTitleBar.OnNaviClickListener() {
            @Override
            public void onNaviClick() {
                super.onNaviClick();
                finish();
            }

            @Override
            public void onMenuClick() {
                super.onMenuClick();
            }
        });
        return naviTitleBar;
    }

    public NaviTitleBar getNaviTitleBar() {
        return mNaviTitleBar;
    }

}
