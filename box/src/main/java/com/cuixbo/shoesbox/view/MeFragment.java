package com.cuixbo.shoesbox.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuixbo.shoesbox.R;

import androidx.fragment.app.Fragment;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class MeFragment extends Fragment {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mOwner = (Owner) getArguments().getSerializable(ARG_MEMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
