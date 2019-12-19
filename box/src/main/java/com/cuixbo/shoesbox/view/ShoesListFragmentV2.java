package com.cuixbo.shoesbox.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuixbo.lib.common.base.BaseFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.presenter.adapter.ShoesItemRecyclerViewAdapter;
import com.cuixbo.shoesbox.view.ShoesListFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class ShoesListFragmentV2 extends BaseFragment {

    private OnListFragmentInteractionListener mListener;
    private List<Shoes> mData = new ArrayList<>();

    private ShoesItemRecyclerViewAdapter mAdapter;

    public ShoesListFragmentV2() {
    }

    @SuppressWarnings("unused")
    public static ShoesListFragmentV2 newInstance() {
        return new ShoesListFragmentV2();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }


    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        View view = getView();
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new ShoesItemRecyclerViewAdapter(mData, mListener);
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onStart() {
        super.onStart();
//        updateData();
    }

    public void updateData(List<Shoes> data) {
        mData = data;
        if (mAdapter != null) {
            mAdapter.updateData(data);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
