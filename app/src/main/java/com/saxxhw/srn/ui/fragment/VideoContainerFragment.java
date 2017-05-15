package com.saxxhw.srn.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseFragment;

import butterknife.BindView;

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
