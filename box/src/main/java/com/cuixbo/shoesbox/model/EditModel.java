package com.cuixbo.shoesbox.model;

import com.cuixbo.shoesbox.contract.EditContract;
import com.cuixbo.shoesbox.data.local.Shoes;

/**
 * @author xiaobocui
 * @date 2019-12-24
 */
public class EditModel implements EditContract.Model {

    private ShoesModel mShoesModel = new ShoesModel();

    /**
     * 根据ShoesId获取Shoes详情
     *
     * @param id ShoesId
     * @return Shoes 实例
     */
    public Shoes getShoesDetail(long id) {
        return mShoesModel.getShoesDetail(id);
    }

    /**
     * 更新/保存 Shoes实例
     *
     * @param shoes 要更新/保存的实例
     * @return id
     */
    public long saveShoes(Shoes shoes) {
        return mShoesModel.saveShoes(shoes);
    }

}
