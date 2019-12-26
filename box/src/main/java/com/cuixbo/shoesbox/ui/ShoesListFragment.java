package com.cuixbo.shoesbox.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.cuixbo.lib.common.mvp.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.adapter.ShoesRecyclerViewAdapter;
import com.cuixbo.shoesbox.contract.ShoesListContract;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.interf.OnListFragmentInteractionListener;
import com.cuixbo.shoesbox.model.BoxModel;
import com.cuixbo.shoesbox.presenter.ShoesListPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
@SuppressWarnings("unused")
public class ShoesListFragment extends BaseMvpFragment<ShoesListPresenter> implements ShoesListContract.View {

    private static final String ARG_MEMBER = "arg_member";
    private String mOwnerName;
    private OnListFragmentInteractionListener mListener;

    private ShoesRecyclerViewAdapter mAdapter;
    private BoxModel mModel = new BoxModel();


    public static ShoesListFragment newInstance(String ownerName) {
        ShoesListFragment fragment = new ShoesListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEMBER, ownerName);
        fragment.setArguments(args);
        return fragment;
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
    public ShoesListPresenter setPresenter() {
        return new ShoesListPresenter();
    }

    @LayoutRes
    @Override
    public int setContentView() {
        return R.layout.fragment_item_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOwnerName = getArguments().getString(ARG_MEMBER);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // todo
        if (mAdapter != null) {
            mPresenter.load(mOwnerName);
        }
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        // Set the adapter
        View view = getView();
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new ShoesRecyclerViewAdapter(new ArrayList<>(), mListener);
            recyclerView.setAdapter(mAdapter);
        }
        mPresenter.load(mOwnerName);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void updateData(List<Shoes> data) {
        mAdapter.getData().clear();
        if (data != null) {
            mAdapter.getData().addAll(data);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
