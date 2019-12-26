package com.cuixbo.shoesbox.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.cuixbo.lib.common.mvp.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.adapter.ShoesRecyclerViewAdapter;
import com.cuixbo.shoesbox.contract.SearchContract;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.interf.OnListFragmentInteractionListener;
import com.cuixbo.shoesbox.presenter.SearchPresenter;

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
public class SearchFragment extends BaseMvpFragment<SearchPresenter> implements SearchContract.View {

    private ShoesRecyclerViewAdapter mAdapter;
    private OnListFragmentInteractionListener mListener;

    private EditText mEtSearch;

    @SuppressWarnings("unused")
    public static SearchFragment newInstance() {
        return new SearchFragment();
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
    public SearchPresenter setPresenter() {
        return new SearchPresenter();
    }

    @LayoutRes
    @Override
    public int setContentView() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        if (getView() == null) {
            return;
        }
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        mEtSearch = getView().findViewById(R.id.et_search);

        mAdapter = new ShoesRecyclerViewAdapter(new ArrayList<>(), mListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        recyclerView.setAdapter(mAdapter);

        //  mPresenter.load(mEtSearch.getText().toString());
    }

    @Override
    protected void initListener() {
        mEtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.load(mEtSearch.getText().toString());
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
                mPresenter.load(null);
            }
        });
    }


    @Override
    public void updateData(List<Shoes> data) {
        mAdapter.getData().clear();
        if (data != null) {
            mAdapter.getData().addAll(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}
