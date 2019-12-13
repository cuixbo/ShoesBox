package com.cuixbo.shoesbox.data.local;

import android.content.Context;
import android.util.Log;

import com.cuixbo.shoesbox.BuildConfig;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class ObjectBox {

    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();

        //开启浏览器访问ObjectBox
        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(context);
            Log.i("ObjectBrowser", "Started: " + started);
        }
    }

    public static BoxStore get() { return boxStore; }


}
