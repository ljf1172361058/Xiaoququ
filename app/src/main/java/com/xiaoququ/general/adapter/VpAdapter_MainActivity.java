package com.xiaoququ.general.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面的ViewPager适配器
 * Created by LiZhiHui on 2016/11/17 13:08.
 */

public class VpAdapter_MainActivity extends FragmentStatePagerAdapter {
    private List<Fragment> fragments=new ArrayList<Fragment>();
    public VpAdapter_MainActivity(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return fragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragments.size();
    }
}
