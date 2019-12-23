package com.cuixbo.lib.common.base;

/**
 * @author xiaobocui
 * @date 2019-12-23
 */
public abstract class BasePresenter<V extends IView> implements IPresenter<V> {
    public V view;

    @Override
    public void onAttach(V view) {
        this.view = view;
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
        this.view = null;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public boolean isAttached() {
        return this.view != null;
    }
}
