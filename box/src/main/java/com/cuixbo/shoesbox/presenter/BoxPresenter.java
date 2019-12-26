package com.cuixbo.shoesbox.presenter;

import com.cuixbo.lib.common.mvp.BaseMvpPresenter;
import com.cuixbo.shoesbox.contract.BoxContract;
import com.cuixbo.shoesbox.data.local.Owner;
import com.cuixbo.shoesbox.model.BoxModel;
import com.cuixbo.shoesbox.ui.ShoesListFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * @author xiaobocui
 * @date 2019-12-10
 */
public class BoxPresenter extends BaseMvpPresenter<BoxContract.View> implements BoxContract.Presenter {
    private BoxModel mModel = new BoxModel();

    @Override
    public void loadOwners() {
        List<Owner> owners = mModel.getOwners();
        String[] titles = new String[0];
        List<Fragment> fragments = new ArrayList<>();
        if (owners != null && !owners.isEmpty()) {
            titles = new String[owners.size()];
            for (int i = 0; i < owners.size(); i++) {
                titles[i] = owners.get(i).name;
                fragments.add(ShoesListFragment.newInstance(titles[i]));
            }
        }
        mView.setupViewPager(fragments, titles);
    }
}
