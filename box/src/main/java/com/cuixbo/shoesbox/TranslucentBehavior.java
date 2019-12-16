package com.cuixbo.shoesbox;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * @author xiaobocui
 * @date 2019-12-13
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    /**
     * 标题栏的高度
     */
    private int mToolbarHeight = 0;

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {
        return dependency instanceof TextView;
    }

    /**
     * 必须要加上  layout_anchor，对方也要layout_collapseMode才能使用
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {

        // 初始化高度
        if (mToolbarHeight == 0) {
            //为了更慢的
            mToolbarHeight = child.getBottom() * 1;
            Log.e("xbc", "child.getBottom():" + child.getBottom());
        }
        Log.e("xbc", "dependency.getY():" + dependency.getY());

        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY() / mToolbarHeight;

        //百分大于1，直接赋值为1
        if (percent >= 1) {
            percent = 1f;
        }

        // 计算alpha通道值
        float alpha = percent * 255;

        //设置背景颜色
        child.setBackgroundColor(Color.argb((int) alpha, 63, 81, 181));
        return true;
    }
}