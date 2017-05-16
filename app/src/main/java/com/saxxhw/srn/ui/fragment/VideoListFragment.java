package com.saxxhw.srn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.saxxhw.srn.R;
import com.saxxhw.srn.base.PresenterFragment;
import com.saxxhw.srn.model.bean.VideoListEntity;
import com.saxxhw.srn.presenter.VideoListPresenter;
import com.saxxhw.srn.presenter.contract.VideoListContract;
import com.saxxhw.srn.ui.adapter.VideoListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Saxxhw on 2017/5/15.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class VideoListFragment extends PresenterFragment<VideoListPresenter> implements
        VideoListContract.View,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.fl_refresh)
    FrameLayout flRefresh;

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;

    @BindView(R.id.rv_video)
    RecyclerView rvVideo;

    private static final String TITLE = "title";
    private String title;

    private boolean isRefresh = true;

    private VideoListAdapter adapter;

    public VideoListFragment() {

    }

    public static VideoListFragment newInstance(String title) {
        VideoListFragment fragment = new VideoListFragment();
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
        return R.layout.fragment_video_list;
    }

    @Override
    protected View getLoadingTargetView() {
        return flRefresh;
    }

    @Override
    protected void initEventAndData() {
        adapter = new VideoListAdapter();
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.setAdapter(adapter);
        onRefresh();
    }

    @Override
    protected void bindListener() {
        srlRefresh.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this, rvVideo);
        adapter.setOnItemClickListener(this);
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
    public void showImageList(List<VideoListEntity.VideosBean> list) {
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        VideoListEntity.VideosBean bean = (VideoListEntity.VideosBean) adapter.getItem(position);
        if (null != bean){

        }
    }
}
