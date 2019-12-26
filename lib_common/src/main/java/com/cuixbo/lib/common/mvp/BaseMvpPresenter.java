package com.cuixbo.lib.common.mvp;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public abstract class BaseMvpPresenter<V extends IView> implements IPresenter<V> {
    public V mView;

    @Override
    public void onAttach(V view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDetach() {
        this.mView = null;
    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }

    @Override
    public boolean isAttached() {
        return this.mView != null;
    }
}
