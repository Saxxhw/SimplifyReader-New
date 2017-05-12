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
 * Created by Saxxhw on 2017/5/12.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class ImageContainerFragment extends BaseFragment {

    @BindView(R.id.tab)
    TabLayout tab;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_container;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initEventAndData() {
        ImagePagerAdapter adapter = new ImagePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }

    @Override
    protected void bindListener() {

    }

    @SuppressWarnings("WeakerAccess")
    class ImagePagerAdapter extends FragmentPagerAdapter {

        String[] titles = getActivity().getResources().getStringArray(R.array.Image_Titles);

        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageListFragment.newInstance(titles[position]);
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
