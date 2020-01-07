package com.cuixbo.shoesbox.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xiaobocui
 * @date 2020-01-06
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private GridLayoutManager mLayoutManager;
    private int mSpace;

    public GridSpaceItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mLayoutManager == null) {
            if (parent.getLayoutManager() instanceof GridLayoutManager) {
                mLayoutManager = (GridLayoutManager) parent.getLayoutManager();
            } else {
                return;
            }
        }
        int position = parent.getChildLayoutPosition(view);
        int spanCount = mLayoutManager.getSpanCount();

        // 设置左右间距
        if (position % spanCount == 0) {
            // 最左侧item
            outRect.left = mSpace;
            outRect.right = mSpace >> 1;
        } else if (position % spanCount == spanCount - 1) {
            // 最右侧item
            outRect.left = mSpace >> 1;
            outRect.right = mSpace;
        } else {
            // 中间item
            outRect.left = mSpace >> 1;
            outRect.right = mSpace >> 1;
        }
        // 设置上下间距
        outRect.top = mSpace >> 1;
        outRect.bottom = mSpace >> 1;
    }
}
