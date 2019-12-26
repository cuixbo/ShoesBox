package com.cuixbo.shoesbox.model;

import com.cuixbo.shoesbox.contract.ShoesListContract;
import com.cuixbo.shoesbox.data.local.Shoes;

import java.util.List;

/**
 * @author xiaobocui
 * @date 2019-12-24
 */
public class ShoesListModel implements ShoesListContract.Model {


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

}
