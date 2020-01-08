package com.cuixbo.shoesbox.model;

import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.data.local.Shoes_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

/**
 * @author xiaobocui
 * @date 2019-12-24
 */
public class ShoesModel {

    /**
     * 获取Shoes的Box对象
     *
     * @return Box<Shoes> 实例
     */
    private Box<Shoes> getShoesBox() {
        return ObjectBox.get().boxFor(Shoes.class);
    }

    /**
     * 根据主人名称获取Shoes列表
     *
     * @param ownerName 主人名称
     * @return List<Shoes> 列表
     */
    public List<Shoes> getShoesList(String ownerName) {
        return getShoesBox()
                .query()
                .equal(Shoes_.ownerName, ownerName)
                .build()
                .find();
    }

    /**
     * 根据关键词查找Shoes列表
     *
     * @param keywords 关键词
     * @return List<Shoes> 列表
     */
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

    /**
     * 根据ShoesId获取Shoes详情
     *
     * @param id ShoesId
     * @return Shoes 实例
     */
    public Shoes getShoesDetail(long id) {
        return getShoesBox().get(id);
    }

    /**
     * 根据Shoes编号获取Shoes详情
     *
     * @param sNumber 编号
     * @return List<Shoes> 列表
     */
    public List<Shoes> getShoesDetail(String sNumber) {
        return getShoesBox()
                .query()
                .equal(Shoes_.sNumber, sNumber)
                .build()
                .find();
    }

    /**
     * 更新/保存 Shoes实例
     *
     * @param shoes 要更新/保存的实例
     * @return id
     */
    public long saveShoes(Shoes shoes) {
        return getShoesBox().put(shoes);
    }

    /**
     * 删除 Shoes记录
     *
     * @param id 要删除的shoes id
     * @return boolean
     */
    public boolean deleteShoes(long id) {
        return getShoesBox().remove(id);
    }

    /**
     * 删除 Shoes记录
     *
     * @param shoes 要删除的shoes
     * @return boolean
     */
    public boolean deleteShoes(Shoes shoes) {
        return getShoesBox().remove(shoes);
    }


}
