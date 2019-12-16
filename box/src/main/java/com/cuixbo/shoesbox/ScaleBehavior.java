package com.cuixbo.shoesbox;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * @author xiaobocui
 * @date 2019-12-13
 */
public class ScaleBehavior extends AppBarLayout.ScrollingViewBehavior {

    /**
     * 标题栏的高度
     */
    private int mToolbarHeight = 0;
    private ImageView mImageView;

    public ScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        mImageView = parent.findViewById(R.id.image_view);

        return dependency instanceof AppBarLayout;
    }

    /**
     * 必须要加上  layout_anchor，对方也要layout_collapseMode才能使用
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        super.onDependentViewChanged(parent, child, dependency);
        // 初始化高度
        if (mToolbarHeight == 0) {
            //为了更慢的
            mToolbarHeight = child.getBottom() * 1;
            Log.e("xbc", "child.getBottom():" + child.getBottom());
        }
//        Log.e("xbc", "dependency.getY():" + dependency.getY()+dependency.getClass().getCanonicalName());


        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY() / mToolbarHeight;

        //百分大于1，直接赋值为1
        if (percent >= 1) {
            percent = 1f;
        }

        // 计算alpha通道值
        float alpha = percent * 255;
//        child.setTranslationX(dependency.getY());

        if (mImageView != null) {
            mImageView.setScaleX(0.8f + percent);
            mImageView.setScaleY(0.8f + percent);
        } else {
//            mImageView=parent.findViewById(R.id.image_view);
        }

        //设置背景颜色
        child.setBackgroundColor(Color.argb((int) alpha, 63, 81, 181));
        return true;
    }
}