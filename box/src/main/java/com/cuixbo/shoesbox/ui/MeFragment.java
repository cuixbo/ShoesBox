package com.cuixbo.shoesbox.ui;

import android.content.Intent;
import android.graphics.Color;

import com.cuixbo.lib.common.mvp.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.contract.MeContract;
import com.cuixbo.shoesbox.presenter.MePresenter;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        ButterKnife.bind(this, getView());
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationIcon().mutate().setTint(Color.WHITE);
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.stv_user_manager)
    public void gotoUserManager() {
        startActivity(new Intent(getContext(), OwnerManagerActivity.class));
    }

    @OnClick(R.id.stv_setting)
    public void gotoSetting() {
        startActivity(new Intent(getContext(), SettingActivity.class));
    }

}
