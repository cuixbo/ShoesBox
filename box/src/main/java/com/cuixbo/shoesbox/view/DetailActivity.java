package com.cuixbo.shoesbox.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.cuixbo.lib.common.base.BaseMvpActivity;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.presenter.ShoesPresenter;

/**
 * @author xiaobocui
 * @date 2019-12-10
 */
public class DetailActivity extends BaseMvpActivity<ShoesPresenter> {

    private ImageView mImageView;
    private SuperTextView mNaviTitleBar;
    private ViewGroup mDetailContainer;
    private Shoes mShoes;
    private long mShoesId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setPresenter(new ShoesPresenter());
    }

    @Override
    protected void initIntent() {
        mShoesId = getIntent().getLongExtra("shoes_id", 0L);
        mShoes = mPresenter.getShoesDetail(mShoesId);
//        mShoes = new Shoes();
//        mPresenter.getShoesBox().removeAll();
    }

    @Override
    protected void initView() {
        mImageView = findViewById(R.id.image);
        mNaviTitleBar = findViewById(R.id.navi_title_bar);
        mDetailContainer = findViewById(R.id.ll_detail_container);

        mNaviTitleBar.setCenterString("资料详情")
                .setRightIcon(null)
                .setRightString("编辑")
                .setRightTextColor(Color.DKGRAY);
    }

    @Override
    protected void initListener() {
        mNaviTitleBar.setLeftImageViewClickListener(v -> finish());
        mNaviTitleBar.setRightTvClickListener(() -> {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("shoes_id", mShoesId);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        mShoes = mPresenter.getShoesDetail(mShoesId);
        if (mShoes.images != null && mShoes.images.length() > 0) {
            Glide.with(this)
                    .load(Uri.parse(mShoes.images))
                    .into(mImageView);
        }

        for (int i = 0; i < mDetailContainer.getChildCount(); i++) {
            SuperTextView view = (SuperTextView) mDetailContainer.getChildAt(i);
            switch (view.getLeftString()) {
                case "编号":
                    view.setRightString(mShoes.sNumber);
                    break;
                case "主人":
                    view.setRightString(mShoes.ownerName);
                    break;
                case "品牌":
                    view.setRightString(mShoes.brand);
                    break;
                case "季节":
                    view.setRightString(mShoes.season);
                    break;
                case "类型":
                    view.setRightString(mShoes.type);
                    break;
                case "颜色":
                    view.setRightString(mShoes.color);
                    break;
                case "尺码":
                    view.setRightString(mShoes.size > 0 ? String.valueOf(mShoes.size) : "");
                    break;
                case "备注":
                    view.setRightString(mShoes.comment);
                    break;
                case "标签":
                    view.setRightString(mShoes.tags);
                    break;
                default:
                    break;
            }
        }
    }

}
