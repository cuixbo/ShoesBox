package com.xbc.douban.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        log();
    }

    @Override
    protected void onStart() {
        super.onStart();
        log();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        log();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log();
    }

    @Override
    protected void onPause() {
        super.onPause();
        log();
    }

    @Override
    protected void onStop() {
        super.onStop();
        log();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        log();
    }

    public Activity getActivity() {
        return this;
    }

    public Context getContext() {
        return this;
    }

    protected abstract void initIntent();

    protected abstract void initView();

    protected abstract void initListener();

    public void log(String log) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String prefix = "";
        if (elements.length >= 4) {
            prefix = elements[3].getFileName().replace(".java", "") + "-->" + elements[3].getMethodName() + ":";
        }
        Log.e("xbc", prefix + log);
    }

    public void log() {
        if (System.currentTimeMillis() != 0) {
            return;
        }
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String prefix = "";
        if (elements.length >= 4) {
            prefix = elements[3].getFileName().replace(".java", "") + "-->" + elements[3].getMethodName();
        }
        Log.e("xbc", prefix);
    }
}
