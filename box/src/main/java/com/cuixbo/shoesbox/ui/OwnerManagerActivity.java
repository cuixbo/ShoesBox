package com.cuixbo.shoesbox.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.contract.OwnerManagerContract;
import com.cuixbo.shoesbox.presenter.OwnerManagerPresenter;
import com.cuixbo.shoesbox.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xiaobocui
 * @date 2020-01-14
 */
public class OwnerManagerActivity extends BaseNaviActivity<OwnerManagerPresenter> implements OwnerManagerContract.View {

    @BindView(R.id.list_view)
    public ListView mListView;
    public ArrayAdapter<String> mAdapter;

    @Override
    public OwnerManagerPresenter setPresenter() {
        return new OwnerManagerPresenter();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        StatusBarUtil.setStatusBarLightMode(getWindow(), true);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("用户管理");
        setHasMenu(false);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<>());
        mListView.setAdapter(mAdapter);
        mPresenter.load();
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_add)
    public void addOwner() {
        showInputDialog();
    }

    @Override
    public void updateData(List<String> data) {
        mAdapter.clear();
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();
    }


    private void showInputDialog() {
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(TextUtils.concat("填写信息"))
                .setView(input)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    mPresenter.addOwner(input.getText().toString());
                })
                .create()
                .show();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 48 * 6);
        params.gravity = Gravity.CENTER;
        params.leftMargin = 24 * 3;
        params.rightMargin = 24 * 3;
        input.setLayoutParams(params);
    }


}
