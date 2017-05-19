package com.saxxhw.srn.ui.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;

import static java.util.Calendar.HOUR_OF_DAY;

/**
 * Created by Saxxhw on 2017/5/19.
 * 邮箱：Saxxhw@126.com
 * 功能：闪屏页
 */

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    /**
     * 背景图片
     */
    @BindView(R.id.splash_image)
    ImageView splashImage;

    /**
     * 版本号
     */
    @BindView(R.id.splash_version_name)
    TextView splashVersionName;

    /**
     * 版权声明
     */
    @BindView(R.id.splash_copyright)
    TextView splashCopyright;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initEventAndData() {
        // 渲染界面
        splashVersionName.setText(getVersionName());
        splashCopyright.setText(R.string.copy_right);
        splashImage.setImageResource(getBackgroundImageResId());
        // 设置启动动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_scale_in);
        animation.setAnimationListener(this);
        splashImage.startAnimation(animation);
    }

    @Override
    protected void bindListener() {

    }

    @Override
    protected boolean hideBackButton() {
        return false;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        readyGoThenKill(MainActivity.class);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * 获取版本名称
     *
     * @return
     */
    private String getVersionName() {
        String versionName = null;
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format(getResources().getString(R.string.version), versionName);
    }

    /**
     * 根据不同时间段获取启动图片
     *
     * @return
     */
    private int getBackgroundImageResId() {
        int resId;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            resId = R.mipmap.bg_splash_morning;
        } else if (hour > 12 && hour <= 18) {
            resId = R.mipmap.bg_splash_afternoon;
        } else {
            resId = R.mipmap.bg_splash_night;
        }
        return resId;
    }
}
