package com.cuixbo.shoesbox.data;

import android.content.Context;

import com.cuixbo.lib.common.util.PreferenceUtil;
import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.data.local.Shoes_;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.collection.ArraySet;
import io.objectbox.Box;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public class DataCenter {

    public static List<Shoes> getShoesList(String ownerName) {
        return getShoesBox()
                .query()
                .equal(Shoes_.ownerName, ownerName)
                .build()
                .find();
    }

    public static List<Shoes> searchShoes(String keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return new ArrayList<>();
        }
        return getShoesBox()
                .query()
                .equal(Shoes_.sNumber, keywords).or()
                .equal(Shoes_.ownerName, keywords).or()
                .contains(Shoes_.brand, keywords).or()
                .contains(Shoes_.material, keywords).or()
                .equal(Shoes_.season, keywords).or()
                .contains(Shoes_.type, keywords).or()
                .contains(Shoes_.color, keywords).or()
                .contains(Shoes_.comment, keywords).or()
                .contains(Shoes_.tags, keywords)
                .build()
                .find();
    }

    public static Shoes getShoesDetail(long id) {
        return getShoesBox().get(id);
    }

    public static List<Shoes> getShoesDetail(String sNumber) {
        return getShoesBox()
                .query()
                .equal(Shoes_.sNumber, sNumber)
                .build()
                .find();
    }

    public static Box<Shoes> getShoesBox() {
        return ObjectBox.get().boxFor(Shoes.class);
    }

//    public PreferenceUtil getPreferenceUtil

    public static Set<String> getSeasons(Context context) {
        return PreferenceUtil.get(context).getStringSet("seasons", new ArraySet<>());
    }

    public static void setSeasons(Context context, Set<String> set) {
        PreferenceUtil.get(context).setStringSet("seasons", set);
    }
}
