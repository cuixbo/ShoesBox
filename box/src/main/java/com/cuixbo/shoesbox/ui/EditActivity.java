package com.cuixbo.shoesbox.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.cuixbo.lib.common.mvp.BaseMvpActivity;
import com.cuixbo.lib.common.util.PreferenceUtil;
import com.cuixbo.lib.dialog.BottomDialog;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.contract.EditContract;
import com.cuixbo.shoesbox.data.Consts;
import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Owner;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.model.EditModel;
import com.cuixbo.shoesbox.presenter.EditPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import androidx.appcompat.app.AlertDialog;
import androidx.collection.ArraySet;
import io.objectbox.Box;

/**
 * @author xiaobocui
 * @date 2019-12-10
 */
public class EditActivity extends BaseMvpActivity<EditPresenter> implements EditContract.View {
    private static final int REQUEST_CODE_CHOOSE = 11;

    private ImageView mImageView;
    private SuperTextView mNaviTitleBar;
    private ViewGroup mDetailContainer;
    private Shoes mShoes;
    private long mShoesId = 0;

    private EditModel mModel = new EditModel();

    @Override
    public EditPresenter setPresenter() {
        return new EditPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        load();
    }

    @Override
    protected void initIntent() {
        mShoesId = getIntent().getLongExtra("shoes_id", 0L);
        if (mShoesId == 0) {
            mShoes = new Shoes();
        } else {
            mShoes = mModel.getShoesDetail(mShoesId);
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        mImageView = findViewById(R.id.image);
        mNaviTitleBar = findViewById(R.id.navi_title_bar);
        mDetailContainer = findViewById(R.id.ll_detail_container);

        mNaviTitleBar.setCenterString("资料编辑")
                .setRightIcon(null)
                .setRightString("完成")
                .setRightTextColor(Color.DKGRAY);

        String[] ps = {
                Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE,
                Permission.CAMERA
        };

        if (!AndPermission.hasPermissions(this, ps)) {
            AndPermission.with(this)
                    .runtime()
                    .permission(ps)
                    .onGranted(permissions -> {
                        // Storage permission are allowed.
                        Log.e("xbc", "onGranted");
                    })
                    .onDenied(permissions -> {
                        Log.e("xbc", "onDenied");
                        // Storage permission are not allowed.
                    })
                    .start();
        }
    }

    @Override
    protected void initListener() {
        mNaviTitleBar.setLeftImageViewClickListener(v -> finish());
        mNaviTitleBar.setRightTvClickListener(this::save);
        mImageView.setOnClickListener(v -> takePhoto());
        for (int i = 0; i < mDetailContainer.getChildCount(); i++) {
            SuperTextView view = (SuperTextView) mDetailContainer.getChildAt(i);
            switch (view.getLeftString()) {
                case "编号":
                case "备注":
                    view.setOnClickListener(this::showInputDialog);
                    break;
                case "主人":
                    view.setOnClickListener(this::showMenuDialog);
                    break;
                case "品牌":
                    view.setOnClickListener(v -> setItemListener(view, Consts.Prefs.Keys.BRANDS));
                    break;
                case "季节":
                    view.setOnClickListener(v -> setItemListener(view, Consts.Prefs.Keys.SEASONS));
                    break;
                case "类型":
                    view.setOnClickListener(v -> setItemListener(view, Consts.Prefs.Keys.TYPES));
                    break;
                case "颜色":
                    view.setOnClickListener(v -> setItemListener(view, Consts.Prefs.Keys.COLORS));
                    break;
                case "尺码":
                    view.setOnClickListener(v -> setItemListener(view, Consts.Prefs.Keys.SIZES));
                    break;
                case "标签":
                    view.setOnClickListener(v -> setItemListener(view, Consts.Prefs.Keys.TAGS));
                    break;
                default:
                    break;
            }
        }
    }

    private void load() {
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

    private void save() {
        for (int i = 0; i < mDetailContainer.getChildCount(); i++) {
            SuperTextView view = (SuperTextView) mDetailContainer.getChildAt(i);
            switch (view.getLeftString()) {
                case "编号":
                    mShoes.sNumber = view.getRightString();
                    break;
                case "主人":
                    mShoes.ownerName = view.getRightString();
                    break;
                case "品牌":
                    mShoes.brand = view.getRightString();
                    break;
                case "季节":
                    mShoes.season = view.getRightString();
                    break;
                case "类型":
                    mShoes.type = view.getRightString();
                    break;
                case "颜色":
                    mShoes.color = view.getRightString();
                    break;
                case "尺码":
                    mShoes.size = Float.parseFloat(view.getRightString().length() > 0 ? view.getRightString() : "0F");
                    break;
                case "备注":
                    mShoes.comment = view.getRightString();
                    break;
                case "标签":
                    mShoes.tags = view.getRightString();
                    break;
                default:
                    break;
            }
        }
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
                .format(new Date());
        if (mShoesId == 0) {
            mShoes.createAt = time;
        } else {
            mShoes.updateAt = time;
        }
        mModel.saveShoes(mShoes);
        finish();
    }

    private void setItemListener(SuperTextView view, String key) {
        Set<String> prefSet = PreferenceUtil.get(this).getStringSet(key, new ArraySet<>());
        showChipInputDialog(view, prefSet, (set, result) -> {
            PreferenceUtil.get(this).setStringSet(key, set);
            view.setRightString(result);
        });
    }

    private void showInputDialog(View v) {
        final EditText input = new EditText(this);
        SuperTextView view = (SuperTextView) v;
        input.setText(view.getRightString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(TextUtils.concat("填写", view.getLeftString(), "信息"))
                .setView(input)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    view.setRightString(input.getText().toString());
                })
                .create()
                .show();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 48 * 6);
        params.gravity = Gravity.CENTER;
        params.leftMargin = 24 * 3;
        params.rightMargin = 24 * 3;
        input.setLayoutParams(params);
    }

    private void showMenuDialog(View v) {
        Box<Owner> ownerBox = ObjectBox.get().boxFor(Owner.class);
        List<Owner> owners = ownerBox.getAll();
        String[] ownerItems = new String[owners.size()];
        for (int i = 0; i < owners.size(); i++) {
            ownerItems[i] = owners.get(i).name;
        }

        /// Material 风格
//        new MaterialAlertDialogBuilder(this).setTitle("请选择")
//                .setItems(ownerItems, (dialog, which) -> {
//                        SuperTextView stv = (SuperTextView) v;
//                        stv.setRightString(ownerItems[which]);
//                        dialog.dismiss();
//                })
//                .create()
//                .show();

        new BottomDialog.Builder(this)
                .addItems(ownerItems)
                .setOnItemClickListener(new BottomDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(Dialog dialog, View view, int which, String text) {
                        SuperTextView stv = (SuperTextView) v;
                        stv.setRightString(ownerItems[which]);
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancel() {

                    }
                })
                .create()
                .show(getSupportFragmentManager());
    }

    private void showChipInputDialog(View v, Set<String> set, OnChipInputListener listener) {
        View view = View.inflate(this, R.layout.layout_input, null);
        ChipGroup cgTags = view.findViewById(R.id.cg_tags);
        EditText etInput = view.findViewById(R.id.et_input);
        Set<String> resultSet = new ArraySet<>(set);
        new MaterialAlertDialogBuilder(this)
                .setTitle("请选择")
                .setView(view)
                .setNegativeButton("取消", (dialog, which) -> {

                })
                .setPositiveButton("确定", (dialog, which) -> {
                    String input = etInput.getText().toString();
                    if (input.length() > 0) {
                        resultSet.add(input);
                    }
                    listener.onResult(resultSet, input);
                })
                .create()
                .show();
        for (String s : set) {
            Chip chip = (Chip) View.inflate(this, R.layout.view_chip, null);
            chip.setText(s);
            chip.setOnClickListener(a -> etInput.setText(chip.getText()));
            chip.setOnLongClickListener(a -> {
                resultSet.remove(chip.getText().toString());
                cgTags.removeView(a);
                return true;
            });
            cgTags.addView(chip);
        }
    }

    private void takePhoto() {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, getPackageName()))
                .countable(true)
                .maxSelectable(9)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(1f)
                .originalEnable(true)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uris = Matisse.obtainResult(data);
            if (uris != null && !uris.isEmpty()) {
                mShoes.images = uris.get(0).toString();
                Glide.with(this)
                        .load(Uri.parse(mShoes.images))
                        .into(mImageView);
                Log.e("xbc", Matisse.obtainPathResult(data).get(0));
            }
        }
    }

    public interface OnChipInputListener {
        void onResult(Set<String> set, String result);
    }

}
