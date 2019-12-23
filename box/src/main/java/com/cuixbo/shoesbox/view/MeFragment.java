package com.cuixbo.shoesbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuixbo.lib.common.base.BaseMvpFragment;
import com.cuixbo.shoesbox.R;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class MeFragment extends BaseMvpFragment {

    private static final String ARG_MEMBER = "arg_member";

    public MeFragment() {

    }

    @SuppressWarnings("unused")
    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

}
