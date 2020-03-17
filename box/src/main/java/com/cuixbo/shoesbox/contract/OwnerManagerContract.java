package com.cuixbo.shoesbox.contract;

import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.lib.common.mvp.IView;

import java.util.List;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public interface OwnerManagerContract {

    interface Model {

    }

    interface View extends IView {
        void updateData(List<String> data);
    }

    interface Presenter extends IPresenter<View> {
        void load();
        void addOwner(String name);
    }

}
