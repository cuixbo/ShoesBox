package com.cuixbo.lib.common.base;

/**
 * 视图基类
 *
 * @author xiaobocui
 * @date 2019-12-20
 */
public interface IView<P extends IPresenter> {

    void setPresenter(P presenter);

}
