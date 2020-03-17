package com.cuixbo.shoesbox.presenter;

import android.util.Log;

import com.cuixbo.lib.common.mvp.BaseMvpPresenter;
import com.cuixbo.shoesbox.contract.OwnerManagerContract;
import com.cuixbo.shoesbox.data.local.Owner;
import com.cuixbo.shoesbox.model.OwnerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobocui
 * @date 2020-01-15
 */
public class OwnerManagerPresenter extends BaseMvpPresenter<OwnerManagerContract.View> implements OwnerManagerContract.Presenter {

    OwnerModel mOwnerModel = new OwnerModel();

    @Override
    public void load() {
        List<Owner> owners = mOwnerModel.getOwners();
        List<String> ownersStr = new ArrayList<>();
        for (int i = 0; i < owners.size(); i++) {
            ownersStr.add(owners.get(i).name);
            Log.e("xbc", owners.get(i).name);
        }
        mView.updateData(ownersStr);
    }

    @Override
    public void addOwner(String name) {
        mOwnerModel.addOwner(name);
        load();
    }


}
