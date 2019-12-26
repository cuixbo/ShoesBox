package com.cuixbo.shoesbox.contract;

import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.lib.common.mvp.IView;

import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public interface BoxContract {

    interface Model {

    }

    interface View extends IView {
        void setupViewPager(List<Fragment> fragments, String[] titles);
    }

    interface Presenter extends IPresenter<View> {
        void loadOwners();
    }

}
