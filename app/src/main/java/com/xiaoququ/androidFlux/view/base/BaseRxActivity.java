package com.xiaoququ.androidFlux.view.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiaoququ.R;

/**
 * 应用程序Activity的基类
 *
 * @author lizhihhui
 * @since  2016-11-3
 */

public abstract class BaseRxActivity extends RxAppCompatActivity {
    /**
     * Toolbar
     */
    private Toolbar mToolbar;
    /**
     * Toolbar中的TextView
     */
    private TextView mToolbarTV;

    public abstract void initView();
    public abstract void setListener();
    public abstract void initDate();

    /**
     * 项目中重写此父类方法需要保证Activity已经包含了Toolbar
     */
    protected void initToolbar(String title) {
        mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        mToolbarTV = (TextView) findViewById(R.id.app_toolbar_title);
        // 判断是否有Toolbar
        if (mToolbar != null) {
            // 将Toolbar显示到界面
            setSupportActionBar(mToolbar);
            // 设置默认的标题不显示(或者mToolbra.setTitle("")也能达到相同的效果)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // 设置ToolBar的TextView值(PS:getTitle()的值是activity的android:lable属性值)
            if(mToolbarTV != null){
                mToolbarTV.setText(title);
            } else {
                mToolbar.setTitle(title);
            }
            // 默认显示返回按钮
            if (isShowBacking()) {
                showBack();
            }
        }
    }

    /**
     * 获取ToolBar
     * @return
     */
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 获取ToolBar的TextView
     * @return
     */
    protected TextView getToolbarTV() {
        return mToolbarTV;
    }

    /**
     * 设置后退按钮图片
     */
    protected void showBack() {
        // 回退按钮需要加在setSupportActionBar(toolbar)后,否则虽然有图片但是点击事件失效
        mToolbar.setNavigationIcon(R.mipmap.img_back_32px);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
