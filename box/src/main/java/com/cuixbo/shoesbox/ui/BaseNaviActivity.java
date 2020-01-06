package com.cuixbo.shoesbox.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.cuixbo.lib.common.mvp.BaseMvpActivity;
import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.widget.NaviTitleBar;

import androidx.annotation.LayoutRes;

/**
 * @author xiaobocui
 * @date 2020-01-02
 */
public abstract class BaseNaviActivity<P extends IPresenter> extends BaseMvpActivity<P> implements NaviTitleBar.OnNaviClickListener {

    /**
     * root容器
     */
    private ViewGroup mContainerWrapper;
    /**
     * 导航Title
     */
    private NaviTitleBar mNaviTitleBar;
    /**
     * 页面内容布局
     */
    private View mContentView;

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        mContainerWrapper = createWrapper();
        super.setContentView(wrapContentView(mContainerWrapper, layoutResId));
    }

    @Override
    public void setContentView(View view) {
        mContainerWrapper = createWrapper();
        super.setContentView(wrapContentView(mContainerWrapper, view));
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        mContainerWrapper = createWrapper();
        super.setContentView(wrapContentView(mContainerWrapper, view, params));
    }

    /**
     * 使用LinearLayout包裹NaviTitleBar与页面ContentView，线性排列
     *
     * @param layoutResId contentView layout id
     * @return wrapper root容器 LinearLayout
     */
    private View wrapContentView(ViewGroup wrapper, int layoutResId) {
        return wrapContentView(wrapper, LayoutInflater.from(this).inflate(layoutResId, wrapper, false));
    }

    /**
     * 使用LinearLayout包裹NaviTitleBar与页面ContentView，线性排列
     *
     * @param view contentView
     * @return wrapper root容器 LinearLayout
     */
    private View wrapContentView(ViewGroup wrapper, View view) {
        return wrapContentView(wrapper, view, view.getLayoutParams());
    }

    /**
     * 使用LinearLayout包裹NaviTitleBar与页面ContentView，线性排列
     *
     * @param view   contentView
     * @param params contentView 的参数
     * @return wrapper root容器 LinearLayout
     */
    private View wrapContentView(ViewGroup wrapper, View view, LayoutParams params) {
        mNaviTitleBar = createNaviTitleBar();
        mContentView = view;
        wrapper.addView(mNaviTitleBar);
        wrapper.addView(mContentView, params);
        return wrapper;
    }

    /**
     * 创建root容器
     *
     * @return LinearLayout root容器
     */
    private LinearLayout createWrapper() {
        LinearLayout wrapper = new LinearLayout(this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        wrapper.setLayoutParams(params);
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.setFitsSystemWindows(true);
        return wrapper;
    }

    /**
     * 创建NaviTitleBar
     *
     * @return NaviTitleBar
     */
    private NaviTitleBar createNaviTitleBar() {
        NaviTitleBar naviTitleBar = new NaviTitleBar(this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.nav_height)
        );
        naviTitleBar.setLayoutParams(params);
        naviTitleBar.setOnNaviClickListener(this);
        return naviTitleBar;
    }

    public NaviTitleBar getNaviTitleBar() {
        return mNaviTitleBar;
    }

    public View getContentView() {
        return mContentView;
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        if (mNaviTitleBar != null) {
            mNaviTitleBar.setTitle((String) getText(titleId));
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (mNaviTitleBar != null) {
            mNaviTitleBar.setTitle((String) title);
        }
    }

    public void setHasMenu(boolean has) {
        if (mNaviTitleBar != null) {
            mNaviTitleBar.setMenuVisibility(has ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public void onNaviClick() {
        finish();
    }

    @Override
    public void onMenuClick() {

    }

    @Override
    public void onTitleClick() {

    }

    @Override
    public void onTitleLongClick() {

    }

}
