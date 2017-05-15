package com.saxxhw.srn.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseActivity;
import com.saxxhw.srn.ui.fragment.ImageContainerFragment;
import com.saxxhw.srn.ui.fragment.MusicFragment;
import com.saxxhw.srn.ui.fragment.VideoContainerFragment;

import butterknife.BindView;

/**
 * Created by Saxxhw on 2017/4/11.
 * 邮箱：Saxxhw@126.com
 * 功能：首页
 */
public class MainActivity extends BaseActivity {

    /**
     * 底部导航栏
     */
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    // 图片浏览
    private ImageContainerFragment imageContainerFragment;
    // 视频爽看
    private VideoContainerFragment videoContainerFragment;
    // 音乐轻听
    protected MusicFragment musicFragment;

    // Fragment切换标识
    private enum Marker {
        IMAGE, VIDEO, MUSIC
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void bindListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initEventAndData() {
        setFragmentSelect(Marker.IMAGE);
    }

    @Override
    protected boolean hideBackButton() {
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_image:
                    setFragmentSelect(Marker.IMAGE);
                    return true;
                case R.id.navigation_video:
                    setFragmentSelect(Marker.VIDEO);
                    return true;
                case R.id.navigation_music:
                    setFragmentSelect(Marker.MUSIC);
                    return true;
            }
            return false;
        }
    };

    /**
     * 根据索引设置指定的Fragment现实
     */
    private void setFragmentSelect(Marker marker) {
        // 开启一个事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 隐藏掉所有的Fragment
        hideAllFragment(transaction);
        switch (marker) {
            case IMAGE:
                setTitle(R.string.title_image);
                if (null == imageContainerFragment) {
                    imageContainerFragment = new ImageContainerFragment();
                    transaction.add(R.id.content, imageContainerFragment);
                } else {
                    transaction.show(imageContainerFragment);
                }
                break;
            case VIDEO:
                setTitle(R.string.title_video);
                if (null == videoContainerFragment) {
                    videoContainerFragment = new VideoContainerFragment();
                    transaction.add(R.id.content, videoContainerFragment);
                } else {
                    transaction.show(videoContainerFragment);
                }
                break;
            case MUSIC:
                setTitle(R.string.title_music);
                if (null == musicFragment) {
                    musicFragment = new MusicFragment();
                    transaction.add(R.id.content, musicFragment);
                } else {
                    transaction.show(musicFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏全部Fragment，保证界面上只有一个Fragment展示
     */
    private void hideAllFragment(FragmentTransaction ft) {
        if (null != imageContainerFragment) {
            ft.hide(imageContainerFragment);
        }
        if (null != videoContainerFragment) {
            ft.hide(videoContainerFragment);
        }
        if (null != musicFragment) {
            ft.hide(musicFragment);
        }
    }
}
