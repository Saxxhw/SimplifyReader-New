package com.saxxhw.srn.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saxxhw.srn.R;
import com.saxxhw.srn.model.bean.VideoListEntity;

import java.util.List;

/**
 * Created by Saxxhw on 2017/5/15.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class VideoListAdapter extends BaseQuickAdapter<VideoListEntity.VideosBean, BaseViewHolder> {

    public VideoListAdapter() {
        super(R.layout.item_video_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoListEntity.VideosBean item) {
        ImageView ivThumbnail = helper.getView(R.id.iv_video_thumbnail);
        Glide.with(mContext).load(item.getBigThumbnail()).into(ivThumbnail);
        helper.setText(R.id.tv_video_title, item.getTitle());
    }
}
