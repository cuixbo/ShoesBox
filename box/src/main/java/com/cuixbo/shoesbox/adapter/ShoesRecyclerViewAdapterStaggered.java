package com.cuixbo.shoesbox.adapter;

import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.interf.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView列表适配器
 *
 * @author xiaobocui
 * @date 2019-12-19
 */
public class ShoesRecyclerViewAdapterStaggered extends RecyclerView.Adapter<ShoesRecyclerViewAdapterStaggered.ViewHolder> {

    private final List<Shoes> mValues = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

    public ShoesRecyclerViewAdapterStaggered(List<Shoes> items, OnListFragmentInteractionListener listener) {
        if (items != null && !items.isEmpty()) {
            mValues.addAll(items);
        }
        mListener = listener;
    }

    public List<Shoes> getData() {
        return mValues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_staggered, parent, false);
        return new ViewHolder(view);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if (!TextUtils.isEmpty(holder.mItem.images)) {
            Glide.with(holder.itemView)
                    .load(Uri.parse(holder.mItem.images))
                    .into(holder.mImageView);
        }
        holder.mContentView.setText(getShoesContentString(holder.mItem));
        holder.mTvNumber.setText(holder.mItem.sNumber);
        holder.mTvOwner.setText(holder.mItem.ownerName);
        holder.mTvCreatedAt.setText(holder.mItem.createAt);
        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private String getShoesContentString(Shoes item) {
        StringBuilder builder = new StringBuilder();
        builder.append(item.brand == null ? "" : item.brand + "，")
                .append(item.season == null ? "" : item.season + "，")
                .append(item.type == null ? "" : item.type + "，")
                .append(item.comment == null ? "" : item.comment + "，")
                .append(item.color == null ? "" : item.color + "，")
                .append(item.size == 0 ? "" : item.size + "，")
                .append(item.tags == null ? "" : item.tags + "，")
        ;
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mImageView;
        final TextView mContentView;
        final TextView mTvNumber;
        final TextView mTvOwner;
        final TextView mTvCreatedAt;
        Shoes mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.image);
            mContentView = view.findViewById(R.id.content);
            mTvNumber = view.findViewById(R.id.tv_number);
            mTvOwner = view.findViewById(R.id.tv_owner);
            mTvCreatedAt = view.findViewById(R.id.tv_created_at);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
