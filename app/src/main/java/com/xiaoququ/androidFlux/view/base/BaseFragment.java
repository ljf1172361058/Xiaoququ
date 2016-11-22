package com.xiaoququ.androidFlux.view.base;


import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * 应用程序Fragment的基类(app包)
 *
 * @author lizhihhui
 * @since  2016-11-3
 */
public abstract class BaseFragment extends Fragment{
    /**
     * Toolbar
     */
    private Toolbar mToolbar;

    public abstract void initView();
    public abstract void setListener();
    public abstract void initDate();

    protected void initToolbar(int toolbarId) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        mToolbar = (Toolbar) appCompatActivity.findViewById(toolbarId);
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

}
