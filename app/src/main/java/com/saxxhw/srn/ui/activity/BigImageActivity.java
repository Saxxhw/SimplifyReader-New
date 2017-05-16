package com.saxxhw.srn.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Saxxhw on 2017/5/16.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class BigImageActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;

    public static final String IMAGE_URL = "imageUrl";
    private String imageUrl;

    @Override
    protected int getLayout() {
        return R.layout.activity_big_image;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        imageUrl = extras.getString(IMAGE_URL);
    }

    @Override
    protected void bindListener() {

    }

    @Override
    protected void initEventAndData() {
        Glide.with(this).load(imageUrl).into(image);
    }

    @Override
    protected boolean hideBackButton() {
        return false;
    }
}
