package com.cuixbo.lib.common.base;

/**
 * @author xiaobocui
 * @date 2019-12-20
 */
public interface IPresenter<V extends IView> {

    void onAttach(V view);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDetach();

    void onDestroy();

    boolean isAttached();

}
