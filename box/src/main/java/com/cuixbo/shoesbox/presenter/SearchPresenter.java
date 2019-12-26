package com.cuixbo.shoesbox.presenter;

import com.cuixbo.lib.common.mvp.BaseMvpPresenter;
import com.cuixbo.shoesbox.contract.SearchContract;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.model.SearchModel;

import java.util.List;

/**
 * @author xiaobocui
 * @date 2019-12-10
 */
public class SearchPresenter extends BaseMvpPresenter<SearchContract.View> implements SearchContract.Presenter {
    private SearchModel mModel = new SearchModel();

    @Override
    public void load(String keywords) {
        List<Shoes> data = mModel.searchShoes(keywords);
        mView.updateData(data);
    }
}
