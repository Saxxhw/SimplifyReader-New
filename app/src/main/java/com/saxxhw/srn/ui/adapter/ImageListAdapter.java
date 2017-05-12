package com.saxxhw.srn.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saxxhw.srn.R;
import com.saxxhw.srn.model.bean.ImageListEntity;

/**
 * Created by Saxxhw on 2017/5/12.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class ImageListAdapter extends BaseQuickAdapter<ImageListEntity.ImgsBean, BaseViewHolder> {

    private int mPhotoWidth;

    public ImageListAdapter(Context context) {
        super(R.layout.item_image_list, null);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int marginPixels = context.getResources().getDimensionPixelOffset(R.dimen.photo_margin_width);
        mPhotoWidth = widthPixels / 2 - marginPixels;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageListEntity.ImgsBean item) {
        ImageView iv = helper.getView(R.id.image);

        int widthPixel = item.getThumbnailWidth();
        int heightPixel = item.getThumbnailHeight();
        ViewGroup.LayoutParams params = iv.getLayoutParams();
        params.width = mPhotoWidth;
        params.height = calcPhotoHeight(widthPixel, heightPixel, mPhotoWidth);
        iv.setLayoutParams(params);

        Glide.with(mContext).load(item.getThumbnailUrl()).into(iv);
    }

    /**
     * 计算图片要显示的高度
     *
     * @param widthPixel  原始分辨率W
     * @param heightPixel 原始分辨率H
     * @param width       要显示的宽度
     * @return 图片高度
     */
    private int calcPhotoHeight(int widthPixel, int heightPixel, int width) {
        return (int) (heightPixel * (width * 1.0f / widthPixel));
    }
}
