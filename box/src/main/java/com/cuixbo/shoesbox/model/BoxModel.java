package com.cuixbo.shoesbox.model;

import com.cuixbo.shoesbox.contract.BoxContract;
import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Owner;
import com.cuixbo.shoesbox.data.local.Shoes;

import java.util.List;

/**
 * @author xiaobocui
 * @date 2019-12-24
 */
public class BoxModel implements BoxContract.Model {

    private ShoesModel mShoesModel = new ShoesModel();

    /**
     * 根据主人名称获取Shoes列表
     *
     * @param ownerName 主人名称
     * @return List<Shoes> 列表
     */
    public List<Shoes> getShoesList(String ownerName) {
        return mShoesModel.getShoesList(ownerName);
    }

    /**
     * 获取所有Owners
     *
     * @return List<Owner> 列表
     */
    public List<Owner> getOwners() {
        return ObjectBox.get().boxFor(Owner.class).getAll();
    }

}
