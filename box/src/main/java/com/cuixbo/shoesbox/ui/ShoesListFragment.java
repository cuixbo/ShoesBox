package com.cuixbo.shoesbox.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuixbo.lib.common.mvp.BaseMvpFragment;
import com.cuixbo.shoesbox.R;
import com.cuixbo.shoesbox.adapter.ShoesRecyclerViewAdapterStaggered;
import com.cuixbo.shoesbox.contract.ShoesListContract;
import com.cuixbo.shoesbox.data.local.Shoes;
import com.cuixbo.shoesbox.interf.OnListFragmentInteractionListener;
import com.cuixbo.shoesbox.presenter.ShoesListPresenter;
import com.cuixbo.shoesbox.util.DiffCallback;
import com.cuixbo.shoesbox.widget.StaggeredSpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
@SuppressWarnings("unused")
public class ShoesListFragment extends BaseMvpFragment<ShoesListPresenter> implements ShoesListContract.View {

    private static final String ARG_MEMBER = "arg_member";
    private String mOwnerName;
    private OnListFragmentInteractionListener mListener;

    private ShoesRecyclerViewAdapterStaggered mAdapter;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("xbc", "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mPresenter.load(mOwnerName);
        }
    }

    @Override
    protected void initIntent() {

    }

    boolean staggered = true;

    @Override
    protected void initView() {
        // Set the adapter
        View view = getView();
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (staggered) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                recyclerView.addItemDecoration(new StaggeredSpaceItemDecoration(16));
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        int[] first = new int[staggeredGridLayoutManager.getSpanCount()];
                        staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(first);
                        boolean firstVisible = false;
                        for (int i : first) {
                            if (i == 1) {
                                firstVisible = true;
                                break;
                            }
                        }
                        // 滑动停止且只要有一列span的第一个item完全可见，则认为到达顶部，可以重新绘制
                        boolean top = newState == RecyclerView.SCROLL_STATE_IDLE && firstVisible;
                        if (top) {
                            // staggeredGridLayoutManager.invalidateSpanAssignments();
                            // Log.e("xbc", "onScrollStateChanged:");
                        }
                        Log.e("xbc", "onScrollStateChanged:newState:" + newState + ",firstVisible:" + firstVisible + ",top:" + top + ",first:" + Arrays.toString(first));
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
            mAdapter = new ShoesRecyclerViewAdapterStaggered(new ArrayList<>(), mListener);
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void updateData(List<Shoes> data) {
        Log.e("xbc", "updateData");
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(mAdapter.getData(), data));
        mAdapter.getData().clear();
        if (data != null) {
            mAdapter.getData().addAll(data);
        }
        diffResult.dispatchUpdatesTo(mAdapter);
//        mAdapter.notifyItemRangeChanged(0, mAdapter.getData().size());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("xbc", "onStop");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("xbc", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("xbc", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
