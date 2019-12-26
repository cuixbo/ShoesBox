package com.cuixbo.shoesbox.contract;

import com.cuixbo.lib.common.mvp.IPresenter;
import com.cuixbo.lib.common.mvp.IView;
import com.cuixbo.shoesbox.data.local.Shoes;

import java.util.List;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public interface SearchContract {

    interface Model {

    }

    interface View extends IView {
        void updateData(List<Shoes> data);
    }

    interface Presenter extends IPresenter<View> {
        void load(String keywords);
    }


}
