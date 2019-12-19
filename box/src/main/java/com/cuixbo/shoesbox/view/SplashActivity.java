package com.cuixbo.shoesbox.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cuixbo.lib.common.base.BaseActivity;
import com.cuixbo.shoesbox.R;

/**
 * 启动页面
 *
 * @author xiaobocui
 * @date 2019-12-18
 */
public class SplashActivity extends BaseActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = () -> {
        mHideHandler.removeCallbacksAndMessages(null);
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        findViewById(R.id.fl_splash_root).setOnClickListener(v -> hide());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(AUTO_HIDE_DELAY_MILLIS);
    }

    private void hide() {
        mHideHandler.removeCallbacksAndMessages(null);
        mHideHandler.postDelayed(mHideRunnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
