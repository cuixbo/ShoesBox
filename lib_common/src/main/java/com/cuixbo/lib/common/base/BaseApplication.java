package com.cuixbo.lib.common.base;

import android.app.Application;

/**
 * @author xiaobocui
 * @date 2019-12-19
 */
public class BaseApplication extends Application {
    protected static Application APPLICATION;

    @Override
    public void onCreate() {
        super.onCreate();
        APPLICATION = this;
    }

    public static Application getApplication() {
        return APPLICATION;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
