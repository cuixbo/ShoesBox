package com.cuixbo.lib.common.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author xiaobocui
 * @date 2019-12-19
 */
public abstract class BaseFragment extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected Context mContext;

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        log();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        log("Context");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log();
        //fragment恢复后,恢复到原来的show,hide状态
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (isSupportHidden) {
                    ft.hide(this);
                } else {
                    ft.show(this);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        log();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log();
        initIntent();
        initView();
        initListener();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        log();
    }

    @Override
    public void onStart() {
        super.onStart();
        log();
    }

    @Override
    public void onResume() {
        super.onResume();
        log();
    }

    @Override
    public void onPause() {
        super.onPause();
        log();
    }

    @Override
    public void onStop() {
        super.onStop();
        log();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        log();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        log();
    }

    protected abstract void initIntent();

    protected abstract void initView();

    protected abstract void initListener();

    public void log(String log) {
        if (System.currentTimeMillis() != 0) {
            // return;
        }
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
