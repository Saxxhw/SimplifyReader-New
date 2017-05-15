package com.saxxhw.srn.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseFragment;
import com.saxxhw.srn.model.bean.VideoListEntity;
import com.saxxhw.srn.model.http.HttpHelper;
import com.saxxhw.srn.model.http.api.Apis;
import com.socks.library.KLog;

import butterknife.BindView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Saxxhw on 2017/5/11.
 * 邮箱：Saxxhw@126.com
 * 功能：视频爽看
 */

public class VideoContainerFragment extends BaseFragment {

    @BindView(R.id.tab)
    TabLayout tab;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_container;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initEventAndData() {
        VideoPagerAdapter adapter = new VideoPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

        Subscription subscription = new HttpHelper().getVideoRetrofit(Apis.class).getVideoList("搞笑", 0, 5, "all", 0, "today", "published", "6ecd6970268b4c53")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<VideoListEntity>() {
                    @Override
                    public void call(VideoListEntity videoListEntity) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        KLog.e(throwable);
                    }
                });
    }

    @Override
    protected void bindListener() {

    }

    @SuppressWarnings("WeakerAccess")
    class VideoPagerAdapter extends FragmentPagerAdapter {

        String[] titles = getActivity().getResources().getStringArray(R.array.Video_Titles);

        public VideoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return VideoListFragment.newInstance(titles[position]);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        // 此方法用来显示Tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
