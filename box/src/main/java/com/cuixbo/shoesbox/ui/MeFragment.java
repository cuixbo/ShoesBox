package com.cuixbo.shoesbox.ui;

import com.cuixbo.lib.common.mvp.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.contract.MeContract;
import com.cuixbo.shoesbox.presenter.MePresenter;

import androidx.annotation.LayoutRes;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class MeFragment extends BaseMvpFragment<MePresenter> implements MeContract.View {

    @SuppressWarnings("unused")
    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public MePresenter setPresenter() {
        return new MePresenter();
    }

    @LayoutRes
    @Override
    public int setContentView() {
        return R.layout.fragment_me;
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