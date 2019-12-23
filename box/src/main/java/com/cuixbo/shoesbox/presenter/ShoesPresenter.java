package com.cuixbo.shoesbox.presenter;

import com.cuixbo.lib.common.base.BasePresenter;
import com.cuixbo.shoesbox.data.DataCenter;
import com.cuixbo.shoesbox.data.local.Shoes;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

/**
 * @author xiaobocui
 * @date 2019-12-10
 */
public class ShoesPresenter extends BasePresenter {

    public static String[] seasons = new String[]{"春天", "夏天", "秋天", "冬天", "春夏", "秋冬"};
    public static String[] brands = new String[]{"耐克", "阿迪", "安踏", "李宁"};
    public static String[] types = new String[]{"网鞋", "跑步鞋", "帆布鞋", "篮球鞋", "板鞋", "皮鞋", "高跟鞋", "高筒靴"};
    public static String[] colors = new String[]{"白色", "黑色", "蓝色"};
    public static String[] sizes = new String[]{"38", "38.5", "39", "42", "42.5", "43", "43.5"};
    public static String[] tags = new String[]{};

    public List<Shoes> getShoesList(String ownerName) {
        return DataCenter.getShoesList(ownerName);
    }

    public List<Shoes> searchShoes(String keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return new ArrayList<>();
        }
        return DataCenter.searchShoes(keywords);
    }

    public Shoes getShoesDetail(long id) {
        return DataCenter.getShoesBox().get(id);
    }

    public Box<Shoes> getShoesBox() {
        return DataCenter.getShoesBox();
    }

}
