package com.cuixbo.shoesbox.presenter;

import com.cuixbo.lib.common.mvp.BaseMvpPresenter;
import com.cuixbo.shoesbox.contract.ShoesListContract;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.model.ShoesListModel;

import java.util.List;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public class ShoesListPresenter extends BaseMvpPresenter<ShoesListContract.View> implements ShoesListContract.Presenter {
    private ShoesListModel mModel = new ShoesListModel();

    @Override
    public void load(String owner) {
        List<Shoes> data = mModel.getShoesList(owner);
        mView.updateData(data);
    }
}
