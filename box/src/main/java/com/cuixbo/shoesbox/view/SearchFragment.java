package com.cuixbo.shoesbox.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.cuixbo.lib.common.base.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.presenter.ShoesPresenter;
import com.cuixbo.shoesbox.presenter.adapter.ShoesItemRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class SearchFragment extends BaseMvpFragment<ShoesPresenter> {

    private ShoesItemRecyclerViewAdapter mAdapter;
    private ShoesListFragment.OnListFragmentInteractionListener mListener;

    private EditText mEtSearch;

    public SearchFragment() {
    }

    @SuppressWarnings("unused")
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ShoesListFragment.OnListFragmentInteractionListener) {
            mListener = (ShoesListFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new ShoesPresenter());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        if (getView() == null) {
            return;
        }
        Context context = getView().getContext();
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        mEtSearch = getView().findViewById(R.id.et_search);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new ShoesItemRecyclerViewAdapter(mPresenter.searchShoes(mEtSearch.getText().toString()), mListener);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mEtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                updateData(mPresenter.searchShoes(mEtSearch.getText().toString()));
                Log.e("xbc", "actionId:" + actionId);
                return true;
            }
            return false;
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAdapter.updateData(new ArrayList<>());
            }
        });
    }

    public void updateData(List<Shoes> data) {
        mAdapter.updateData(data);
    }

}
