package com.saxxhw.srn.presenter.contract;

import com.saxxhw.srn.base.BasePresenter;
import com.saxxhw.srn.base.BaseView;
import com.saxxhw.srn.model.bean.VideoListEntity;

import java.util.List;

/**
 * Created by Saxxhw on 2017/5/11.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public interface VideoListContract {

    interface View extends BaseView {

        void loadEnd();

        void loadFail();

        void showImageList(List<VideoListEntity.VideosBean> list);

        boolean isFirstRefresh();
    }

    interface Presenter extends BasePresenter<View> {

        void getImageList(String keyword, boolean isRefresh);
    }
}
