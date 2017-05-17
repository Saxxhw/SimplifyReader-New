package com.saxxhw.srn.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseActivity;
import com.saxxhw.srn.widget.SmoothImageView;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Saxxhw on 2017/5/16.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class BigImageActivity extends BaseActivity {

    public static final String INTENT_IMAGE_URL_TAG = "INTENT_IMAGE_URL_TAG";
    public static final String INTENT_IMAGE_X_TAG = "INTENT_IMAGE_X_TAG";
    public static final String INTENT_IMAGE_Y_TAG = "INTENT_IMAGE_Y_TAG";
    public static final String INTENT_IMAGE_W_TAG = "INTENT_IMAGE_W_TAG";
    public static final String INTENT_IMAGE_H_TAG = "INTENT_IMAGE_H_TAG";

    private String mImageUrl;
    private int mLocationX;
    private int mLocationY;
    private int mWidth;
    private int mHeight;

    @BindView(R.id.iv_detail_smooth_image)
    SmoothImageView mSmoothImageView;

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
        mImageUrl = extras.getString(INTENT_IMAGE_URL_TAG);
        mLocationX = extras.getInt(INTENT_IMAGE_X_TAG);
        mLocationY = extras.getInt(INTENT_IMAGE_Y_TAG);
        mWidth = extras.getInt(INTENT_IMAGE_W_TAG);
        mHeight = extras.getInt(INTENT_IMAGE_H_TAG);
    }

    @Override
    protected void bindListener() {

    }

    @Override
    protected void initEventAndData() {
        mSmoothImageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        mSmoothImageView.transformIn();
        Glide.with(this).load(mImageUrl).into(mSmoothImageView);
        mSmoothImageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });

        mSmoothImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v2) {
                mSmoothImageView.transformOut();
            }
        });
    }

    @Override
    protected boolean hideBackButton() {
        return false;
    }

    @Override
    public void onBackPressed() {
        mSmoothImageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }
}
