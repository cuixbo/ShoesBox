package com.cuixbo.lib.common.mvp;

import android.os.Bundle;

import com.cuixbo.lib.common.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends BaseActivity implements IView {
    public P mPresenter;

    public abstract P setPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        if (mPresenter != null) {
            mPresenter.onAttach(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
            mPresenter.onDestroy();
        }
    }
}
