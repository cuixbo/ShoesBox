package com.cuixbo.shoesbox.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuixbo.lib.common.base.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.data.local.Owner;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.presenter.ShoesPresenter;
import com.cuixbo.shoesbox.presenter.adapter.ShoesItemRecyclerViewAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class ShoesListFragment extends BaseMvpFragment {

    private static final String ARG_MEMBER = "arg_member";
    private Owner mOwner;
    private OnListFragmentInteractionListener mListener;
    private ShoesPresenter mPresenter;

    private ShoesItemRecyclerViewAdapter mAdapter;

    public ShoesListFragment() {
        mPresenter = new ShoesPresenter();
    }


    @SuppressWarnings("unused")
    public static ShoesListFragment newInstance(Owner owner) {
        ShoesListFragment fragment = new ShoesListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEMBER, owner);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
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
        if (getArguments() != null) {
            mOwner = (Owner) getArguments().getSerializable(ARG_MEMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.updateData(mPresenter.getShoesList(mOwner.name));
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
            mAdapter = new ShoesItemRecyclerViewAdapter(mPresenter.getShoesList(mOwner.name), mListener);
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {

        // TODO: Update argument type and name
        void onListFragmentInteraction(Shoes item);
    }
}
