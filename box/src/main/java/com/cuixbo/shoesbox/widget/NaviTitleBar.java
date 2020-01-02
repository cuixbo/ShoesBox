package com.cuixbo.shoesbox.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.cuixbo.shoesbox.R;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
@SuppressWarnings("unused")
public class NaviTitleBar extends FrameLayout {

    private static final int COLOR_DEFAULT = 0xFF333333;
    private static final int DP_16 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, Resources.getSystem().getDisplayMetrics());

    AppCompatTextView mLeft, mCenter, mRight;

    int mNaviIconColor = COLOR_DEFAULT, mMenuIconColor = COLOR_DEFAULT;

    public NaviTitleBar(Context context) {
        super(context);
        init(context);
    }

    public NaviTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NaviTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NaviTitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mLeft = initLeft(context);
        mCenter = initCenter(context);
        mRight = initRight(context);
        initListener();
    }


    private AppCompatTextView initLeft(Context context) {
        AppCompatTextView left = new AppCompatTextView(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        left.setText("");
        left.setLayoutParams(params);
        left.setTextColor(COLOR_DEFAULT);
        left.setGravity(Gravity.CENTER_VERTICAL);
        left.setPadding(DP_16 / 2, 0, DP_16 / 2, 0);
        left.setCompoundDrawablesWithIntrinsicBounds(tintDrawable(R.drawable.ic_svg_back, mNaviIconColor), null, null, null);
        left.setCompoundDrawablePadding(0);
        left.setClickable(true);
        addView(left);
        return left;
    }

    private AppCompatTextView initCenter(Context context) {
        AppCompatTextView center = new AppCompatTextView(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
//        params.weight = 1.0F;
        center.setLayoutParams(params);
        center.setText("设计如何");
        center.setTextColor(COLOR_DEFAULT);
        center.setMaxEms(12);
        center.setSingleLine(true);
        center.setEllipsize(TextUtils.TruncateAt.END);
        center.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        center.setPadding(DP_16 / 2, 0, DP_16 / 2, 0);
        addView(center);
        return center;
    }

    private AppCompatTextView initRight(Context context) {
        AppCompatTextView right = new AppCompatTextView(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        right.setLayoutParams(params);
        right.setText("");
        right.setTextColor(COLOR_DEFAULT);
        right.setGravity(Gravity.CENTER_VERTICAL);
        right.setPadding(DP_16, 0, DP_16, 0);
        right.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_svg_more, 0, 0, 0);
        right.setCompoundDrawablePadding(0);
        right.setClickable(true);
        addView(right);
        return right;
    }

    private void initListener() {
        mLeft.setOnClickListener(v -> {
            if (getContext() instanceof Activity) {
                Activity activity = (Activity) getContext();
                activity.finish();
            }
        });
    }

    public NaviTitleBar setTitle(String title) {
        mCenter.setText(title);
        return this;
    }

    public NaviTitleBar setNaviIcon(@DrawableRes int icon) {
        mLeft.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        return this;
    }

    public NaviTitleBar setNaviIconColor(@ColorInt int color) {
        mNaviIconColor = color;
        Drawable left = mLeft.getCompoundDrawables()[0];
        if (left != null) {
            mLeft.setCompoundDrawablesWithIntrinsicBounds(tintDrawable(left, color), null, null, null);
        }
        return this;
    }

    public NaviTitleBar setNaviText(String text) {
        mLeft.setText(text);
        return this;
    }

    public NaviTitleBar setNaviTextColor(@ColorInt int color) {
        mLeft.setTextColor(color);
        return this;
    }

    public NaviTitleBar setMenuIcon(@DrawableRes int icon) {
        mRight.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        return this;
    }

    public NaviTitleBar setMenuIconColor(@ColorInt int color) {
        mMenuIconColor = color;
        Drawable right = mRight.getCompoundDrawables()[0];
        if (right != null) {
            mRight.setCompoundDrawablesWithIntrinsicBounds(tintDrawable(right, color), null, null, null);
        }
        return this;
    }

    public NaviTitleBar setMenuText(String text) {
        mRight.setText(text);
        return this;
    }

    public NaviTitleBar setMenuTextColor(@ColorInt int color) {
        mRight.setTextColor(color);
        return this;
    }

    private Drawable tintDrawable(Drawable drawable, @ColorInt int color) {
        Drawable mutated = drawable.mutate();
        DrawableCompat.setTint(mutated, color);
        return mutated;
    }

    private Drawable tintDrawable(@DrawableRes int drawable, @ColorInt int color) {
        Drawable mutated = getResources().getDrawable(drawable, null).mutate();
        DrawableCompat.setTint(mutated, color);
        return mutated;
    }

}
