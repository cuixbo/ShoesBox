package com.cuixbo.shoesbox.presenter;

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
 * @date 2019-12-10
 */
public class ShoesPresenter {

    public static String[] seasons = new String[]{"春天", "夏天", "秋天", "冬天", "春夏", "秋冬"};
    public static String[] brands = new String[]{"耐克", "阿迪", "安踏", "李宁"};
    public static String[] types = new String[]{"网鞋", "跑步鞋", "帆布鞋", "篮球鞋", "板鞋", "皮鞋", "高跟鞋", "高筒靴"};
    public static String[] colors = new String[]{"白色", "黑色", "蓝色"};
    public static String[] sizes = new String[]{"38", "38.5", "39", "42", "42.5", "43", "43.5"};
    public static String[] tags = new String[]{};

    public List<Shoes> getShoesList(String ownerName) {
        return getShoesBox()
                .query()
                .equal(Shoes_.ownerName, ownerName)
                .build()
                .find();
    }

    public List<Shoes> searchShoes(String keywords) {
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

    public Shoes getShoesDetail(long id) {
        return getShoesBox().get(id);
    }

    public List<Shoes> getShoesDetail(String sNumber) {
        return getShoesBox()
                .query()
                .equal(Shoes_.sNumber, sNumber)
                .build()
                .find();
    }

    public Box<Shoes> getShoesBox() {
        return ObjectBox.get().boxFor(Shoes.class);
    }

//    public PreferenceUtil getPreferenceUtil

    public Set<String> getSeasons(Context context) {
        return PreferenceUtil.get(context).getStringSet("seasons", new ArraySet<>());
    }

    public void setSeasons(Context context, Set<String> set) {
        PreferenceUtil.get(context).setStringSet("seasons", set);
    }

}
