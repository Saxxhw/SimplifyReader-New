package com.saxxhw.srn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.saxxhw.srn.R;
import com.saxxhw.srn.base.PresenterFragment;
import com.saxxhw.srn.model.bean.ImageListEntity;
import com.saxxhw.srn.presenter.ImageListPresenter;
import com.saxxhw.srn.presenter.contract.ImageListContract;
import com.saxxhw.srn.ui.adapter.ImageListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Saxxhw on 2017/5/11.
 * 邮箱：Saxxhw@126.com
 * 功能：图片浏览
 */

public class ImageListFragment extends PresenterFragment<ImageListPresenter> implements ImageListContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.llyt_refresh)
    LinearLayout llytRefresh;

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;

    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;

    private ImageListAdapter adapter;

    private boolean isRefresh = true;

    private static final String TITLE = "title";
    private String title;

    public ImageListFragment() {

    }

    public static ImageListFragment newInstance(String title) {
        ImageListFragment fragment = new ImageListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_list;
    }

    @Override
    protected View getLoadingTargetView() {
        return llytRefresh;
    }

    @Override
    protected void initEventAndData() {
        adapter = new ImageListAdapter(getActivity());
        rvImageList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvImageList.setAdapter(adapter);
        onRefresh();
    }

    @Override
    protected void bindListener() {
        srlRefresh.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this, rvImageList);
        adapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void onRefresh() {
        mPresenter.getImageList(title, isRefresh = true);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getImageList(title, isRefresh = false);
    }

    @Override
    public void loadEnd() {
        adapter.loadMoreEnd();
    }

    @Override
    public void loadFail() {
        adapter.loadMoreFail();
    }

    @Override
    public void showImageList(List<ImageListEntity.ImgsBean> list) {
        // 当前操作为下拉刷新且控件实例不为空，停止刷新动画
        if (isRefresh && null != srlRefresh) {
            srlRefresh.setRefreshing(false);
        }
        if (isRefresh) {
            adapter.setNewData(list);
        } else {
            adapter.addData(list);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public boolean isFirstRefresh() {
        return adapter.getData().isEmpty();
    }
}