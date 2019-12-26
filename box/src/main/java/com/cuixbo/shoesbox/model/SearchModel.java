package com.cuixbo.shoesbox.model;

import com.cuixbo.shoesbox.contract.SearchContract;
import com.cuixbo.shoesbox.data.local.Shoes;

import java.util.List;

/**
 * @author xiaobocui
 * @date 2019-12-24
 */
public class SearchModel implements SearchContract.Model {

    private ShoesModel mShoesModel = new ShoesModel();

    /**
     * 根据关键词查找Shoes列表
     *
     * @param keywords 关键词
     * @return List<Shoes> 列表
     */
    public List<Shoes> searchShoes(String keywords) {
        return mShoesModel.searchShoes(keywords);
    }

}
