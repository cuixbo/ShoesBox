package com.cuixbo.shoesbox.ui;

import android.content.Intent;
import android.graphics.Color;

import com.cuixbo.lib.common.mvp.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.contract.MeContract;
import com.cuixbo.shoesbox.presenter.MePresenter;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationIcon().mutate().setTint(Color.WHITE);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.stv_setting).setOnClickListener(v -> startActivity(new Intent(getContext(), SettingActivity.class)));
//
//        findViewById(R.id.stv_clear_cache).setOnClickListener(v -> {
//            ShoesModel model = new ShoesModel();
//            List<Shoes> shoesList = model.getShoesList("");
//            for (int i = 0; i < shoesList.size(); i++) {
//                model.deleteShoes(shoesList.get(i));
//            }
//            Log.e("xbc", "shoesList.size:" + shoesList.size());
//        });
    }


}
