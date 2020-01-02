package com.cuixbo.shoesbox.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;

import com.cuixbo.lib.common.util.DeviceUtil;

/**
 * @author xiaobocui
 * @date 2019-12-18
 */
public class StatusBarUtil {

    public static int getStatusBarHeight(Context context) {
        return DeviceUtil.getStatusHeight(context);
    }

    public static void setStatusBarLightMode(Window window, boolean dark) {
        View decorView = window.getDecorView();
        if (dark) {
            // 状态栏图标、文字 深色（黑）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            // M之前的暂不支持
        } else {
            // 状态栏图标、文字 浅色（白）
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    private static void setStatusBarLightModeCompat(Activity activity, boolean dark) {
        // 需要兼容处理 MIUI、FLYME、Google原生
    }

}
