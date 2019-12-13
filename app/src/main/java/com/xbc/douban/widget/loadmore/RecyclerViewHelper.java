package com.xbc.douban.widget.loadmore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import com.xbc.douban.R;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewHelper {

    public interface OnRecycleViewItemClickListener {
        void onItemClick(View item, int position);
    }

    public interface OnRecycleViewItemLongClickListener {
        void onItemLongClick(View item, int position);
    }

    /**
     * 可以设置间距的分割线
     */
    public static class InsetDividerItemDecoration extends RecyclerView.ItemDecoration {

        public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
        public static final int VERTICAL = LinearLayout.VERTICAL;

        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Context mContext;
        private Drawable mDivider;
        private int mDividerColor = R.color.color_divider;//分割线颜色
        private int marginLeft, marginRight, marginTop, marginBottom;
        private int mOrientation;
        private final Rect mBounds = new Rect();
        private boolean mHeaderDividersEnabled = true;
        private boolean mFooterDividersEnabled = true;
        private boolean mLastItemDividerEnabled = true;//除了footer之外的最后一个item的分割线

        public InsetDividerItemDecoration(Context context, int orientation) {
            mContext = context;
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
            setDividerColor(mDividerColor);
        }

        public InsetDividerItemDecoration setMargin(int l, int r, int t, int b) {
            marginLeft = l;
            marginRight = r;
            marginTop = t;
            marginBottom = b;
            return this;
        }

        public InsetDividerItemDecoration setHeaderDividersEnabled(boolean enabled) {
            mHeaderDividersEnabled = enabled;
            return this;
        }

        public InsetDividerItemDecoration setFooterDividersEnabled(boolean enabled) {
            mFooterDividersEnabled = enabled;
            return this;
        }

        public InsetDividerItemDecoration setLastItemDividerEnabled(boolean enabled) {
            mLastItemDividerEnabled = enabled;
            return this;
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL && orientation != VERTICAL) {
                throw new IllegalArgumentException(
                        "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
            }
            mOrientation = orientation;
        }

        public void setDrawable(@NonNull Drawable drawable) {
            if (drawable == null) {
                throw new IllegalArgumentException("Drawable cannot be null.");
            }
            mDivider = drawable;
        }

        public void setDividerColor(@NonNull int color) {
            mDividerColor = color;
            setDrawable(new ColorDrawable(mContext.getResources().getColor(mDividerColor)));
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (parent.getLayoutManager() == null) {
                return;
            }
            if (mOrientation == VERTICAL) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }


        @SuppressLint("NewApi")
        private void drawVertical(Canvas canvas, RecyclerView parent) {
            canvas.save();
            final int left;
            final int right;
            if (parent.getClipToPadding()) {
                left = parent.getPaddingLeft() + marginLeft;
                right = parent.getWidth() - parent.getPaddingRight() - marginRight;
                canvas.clipRect(left, parent.getPaddingTop(), right,
                                parent.getHeight() - parent.getPaddingBottom());
            } else {
                left = 0 + marginLeft;
                right = parent.getWidth() - marginRight;
            }

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                //检查是否显示分割线
                if (filterDivider(parent, child)) {
                    continue;
                }
                parent.getDecoratedBoundsWithMargins(child, mBounds);
                final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
                final int top = bottom - mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            canvas.restore();
        }

        @SuppressLint("NewApi")
        private void drawHorizontal(Canvas canvas, RecyclerView parent) {
            canvas.save();
            final int top;
            final int bottom;
            if (parent.getClipToPadding()) {
                top = parent.getPaddingTop() + marginTop;
                bottom = parent.getHeight() - parent.getPaddingBottom() - marginBottom;
                canvas.clipRect(parent.getPaddingLeft(), top,
                                parent.getWidth() - parent.getPaddingRight(), bottom);
            } else {
                top = 0 + marginTop;
                bottom = parent.getHeight() - marginBottom;
            }

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                //检查是否显示分割线
                if (filterDivider(parent, child)) {
                    continue;
                }
                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
                final int right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
                final int left = right - mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            canvas.restore();
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (mOrientation == VERTICAL) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }

        /**
         * 过滤掉分割线(不显示)
         */
        private boolean filterDivider(RecyclerView parent, View child) {
            if (!mHeaderDividersEnabled || !mFooterDividersEnabled || !mLastItemDividerEnabled) {
                int position = parent.getChildAdapterPosition(child);
                if (!mHeaderDividersEnabled) {
                    if (position == 0) {
                        return true;
                    }
                }
                if (!mFooterDividersEnabled) {
                    if (position == parent.getAdapter().getItemCount() - 1) {
                        return true;
                    }
                }
                if (!mLastItemDividerEnabled) {
                    if (position == parent.getAdapter().getItemCount() - 2) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
