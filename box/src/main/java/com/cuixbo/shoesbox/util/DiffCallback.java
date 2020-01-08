package com.cuixbo.shoesbox.util;

import android.text.TextUtils;

import com.cuixbo.shoesbox.data.local.Shoes;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * @author xiaobocui
 * @date 2020-01-07
 */
public class DiffCallback extends DiffUtil.Callback {
    List<Shoes> mOldData, mNewData;

    public DiffCallback(List<Shoes> oldData, List<Shoes> newData) {
        mOldData = oldData;
        mNewData = newData;
    }

    @Override
    public int getOldListSize() {
        return mOldData == null ? 0 : mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData == null ? 0 : mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldData.get(oldItemPosition).id == mNewData.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Shoes oldItem = mOldData.get(oldItemPosition);
        Shoes newItem = mNewData.get(newItemPosition);
        if (!TextUtils.equals(oldItem.ownerName, newItem.ownerName)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.sNumber, newItem.sNumber)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.brand, newItem.brand)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.type, newItem.type)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.material, newItem.material)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.color, newItem.color)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.season, newItem.season)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.images, newItem.images)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.tags, newItem.tags)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.comment, newItem.comment)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.extra, newItem.extra)) {
            return false;
        }
        if (!TextUtils.equals(oldItem.updateAt, newItem.updateAt)) {
            return false;
        }
        if (oldItem.size != newItem.size){
            return false;
        }
        return true;
    }
}
