package com.cuixbo.shoesbox.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @author xiaobocui
 * @date 2020-01-06
 */
public class StaggeredSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private StaggeredGridLayoutManager mLayoutManager;
    private int mSpace;

    public StaggeredSpaceItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mLayoutManager == null) {
            if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                mLayoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
            } else {
                return;
            }
        }
        int position = parent.getChildLayoutPosition(view);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        int index = layoutParams.getSpanIndex();
        int spanCount = mLayoutManager.getSpanCount();

        // 设置左右间距
        if (index % spanCount == 0) {
            // 最左侧item
            outRect.left = mSpace;
            outRect.right = mSpace >> 1;
        } else if (index % spanCount == spanCount - 1) {
            // 最右侧item
            outRect.left = mSpace >> 1;
            outRect.right = mSpace;
        } else {
            // 中间item
            outRect.left = mSpace >> 1;
            outRect.right = mSpace >> 1;
        }
        // 设置上下间距
        if (position < spanCount) {
            // 首行
            outRect.top = mSpace << 1;
            outRect.bottom = mSpace >> 1;
        } else if (position > (mLayoutManager.getItemCount() / spanCount - 1) * spanCount) {
            // 尾行
            outRect.bottom = mSpace << 1;
            outRect.top = mSpace >> 1;
        } else {
            outRect.top = mSpace >> 1;
            outRect.bottom = mSpace >> 1;
        }
    }
}
