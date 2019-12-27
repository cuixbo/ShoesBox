package com.cuixbo.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author xiaobocui
 * @date 2019-12-26
 */
public class BottomDialog extends DialogFragment {


    private DialogParams mParams;

    private BottomDialog(Context context) {
        mParams = new DialogParams(context);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog_FullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_bottom_dialog, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDialogParams();
        initView();
    }

    /**
     * 设置Dialog的宽高、重心
     */
    private void setDialogParams() {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(params);
        window.setWindowAnimations(R.style.BottomDialogAnimation);
    }

    private void initView() {
        if (getView() != null) {
            mParams.mTvTitle = getView().findViewById(R.id.tv_title);
            mParams.mLlContainer = getView().findViewById(R.id.ll_container);
            mParams.mTvCancel = getView().findViewById(R.id.tv_cancel);
            if (mParams.mLlContainer != null) {
                for (int i = 0; i < mParams.mItemViews.size(); i++) {
                    final TextView child = mParams.mItemViews.get(i);
                    mParams.mLlContainer.addView(child);
                    final int which = i;
                    child.setOnClickListener(v -> mParams.mListener.onItemClick(getDialog(), v, which, child.getText().toString()));
                }
                mParams.mTvCancel.setOnClickListener(v -> {
                    dismiss();
                    mParams.mListener.onCancel();
                });
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        if (mParams.mListener != null) {
            mParams.mListener.onCancel();
        }
    }

    public void show(FragmentManager manager) {
        show(manager, null);
    }

    public interface OnItemClickListener {
        void onItemClick(Dialog dialog, View view, int which, String text);

        void onCancel();
    }

    public static class Builder {
        DialogParams mParams;

        public Builder(Context context) {
            mParams = new DialogParams(context);
            mParams.mContext = context;
        }

        public Builder setTextGravity(int gravity) {
            mParams.mTextGravity = gravity;
            return this;
        }

        public Builder setTextSize(int size) {
            mParams.mTextSize = size;
            return this;
        }

        public Builder setTextColor(@ColorInt int color) {
            mParams.mTextColor = color;
            return this;
        }

        public Builder setItemMinHeight(int height) {
            mParams.mItemMinHeight = height;
            return this;
        }

        private TextView newItem() {
            TextView textView = new TextView(mParams.mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mParams.mItemMinHeight);
            textView.setLayoutParams(params);
            textView.setGravity(mParams.mTextGravity);
            textView.setTextSize(mParams.mTextSize);
            textView.setTextColor(mParams.mTextColor);
            return textView;
        }

        public Builder addItems(String... items) {
            for (String item : items) {
                TextView textView = newItem();
                textView.setText(item);
                mParams.mItemViews.add(textView);
            }
            return this;
        }

        public Builder setOnMenuClickListener(OnItemClickListener listener) {
            mParams.mListener = listener;
            return this;
        }

        public BottomDialog create() {
            BottomDialog dialog = new BottomDialog(mParams.mContext);
            mParams.apply(dialog);
            return dialog;
        }

    }

    public static class DialogParams {
        Context mContext;
        int mTextGravity = Gravity.CENTER;
        int mTextSize = 14;
        int mTextColor = Color.parseColor("#666666");
        int mItemMinHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, Resources.getSystem().getDisplayMetrics());

        int mBtnTextColor;
        int mBtnBackgroundColor;
        Drawable mBtnBackgroundDrawable;
        String mBtnText;

        OnItemClickListener mListener;
        List<TextView> mItemViews = new ArrayList<>();
        LinearLayout mLlContainer;
        TextView mTvTitle;
        TextView mTvCancel;

        public DialogParams(Context context) {
            mContext = context;
        }

        public void apply(BottomDialog dialog) {
            dialog.mParams.mContext = mContext;
            dialog.mParams.mTextGravity = mTextGravity;
            dialog.mParams.mTextSize = mTextSize;
            dialog.mParams.mTextColor = mTextColor;
            dialog.mParams.mListener = mListener;
            dialog.mParams.mItemViews = mItemViews;
            dialog.mParams.mItemMinHeight = mItemMinHeight;

            if (!mItemViews.isEmpty()) {
                for (TextView textView : mItemViews) {
                    textView.setGravity(mTextGravity);
                    textView.setTextSize(mTextSize);
                    textView.setTextColor(mTextColor);
                    int minHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mItemMinHeight, Resources.getSystem().getDisplayMetrics());
                    textView.setMinHeight(minHeight);
                }
            }
        }
    }
}
