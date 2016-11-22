package com.xiaoququ.androidFlux.view.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.xiaoququ.R;
import com.xiaoququ.androidFlux.view.base.BaseRxActivity;
import com.xiaoququ.androidFlux.view.fragment.MainFragment01;
import com.xiaoququ.androidFlux.view.fragment.MainFragment02;
import com.xiaoququ.androidFlux.view.fragment.MainFragment03;
import com.xiaoququ.general.adapter.VpAdapter_MainActivity;
import com.xiaoququ.general.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends BaseRxActivity implements BoomMenuButton.OnSubButtonClickListener,
        BoomMenuButton.AnimatorListener,
        View.OnClickListener{
    NavigationTabBar mNavigationTabBar;
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        initDate();
    }

    @Override
    public void initView() {
        initBoom(4);
        initNavigationTabBar();
    }
    @Override
    public void setListener() {

    }
    @Override
    public void initDate() {
        ShareSDK.initSDK(this);
    }

    /**
     * 初始化NavigationTabBar及ViewPager
     */
    List<Fragment> fragments=new ArrayList<Fragment>();
    private void initNavigationTabBar() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.ntb_vp_horizontal_ntb);
        viewPager.setOffscreenPageLimit(3);
        MainFragment01 mainFragment01=new MainFragment01();
        MainFragment02 mainFragment02=new MainFragment02();
        MainFragment03 mainFragment03=new MainFragment03();
        fragments.add(mainFragment01);
        fragments.add(mainFragment02);
        fragments.add(mainFragment03);
        // 设置ViewPager适配器
        viewPager.setAdapter(new VpAdapter_MainActivity(getSupportFragmentManager(), fragments));

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        mNavigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ntb_ic_first),
                        Color.parseColor(colors[0]))
                        .title("趣段")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ntb_ic_second),
                        Color.parseColor(colors[1]))
                        .title("趣图")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ntb_ic_third),
                        Color.parseColor(colors[2]))
                        .title("我的")
                        .build()
        );
       /* models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ntb_ic_fourth),
                        Color.parseColor(colors[3]))
                        .title("Flag")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ntb_ic_fifth),
                        Color.parseColor(colors[4]))
                        .title("Medal")
                        .build()
        );*/

        mNavigationTabBar.setModels(models);
        mNavigationTabBar.setViewPager(viewPager, 0);

        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        mNavigationTabBar.setBehaviorEnabled(true);

        mNavigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        mNavigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        //final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.ntb_parent);
        // 不采用NavigationTabBar指定的fab
       /* findViewById(R.id.ntb_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // 重新显示勋章(Badge)
                *//*for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final String title = String.valueOf(new Random().nextInt(15));
                            if (!model.isBadgeShowed()) {
                                model.setBadgeTitle(title);
                                model.showBadge();
                            } else model.updateBadgeTitle(title);
                        }
                    }, i * 100);
                }*//*

                // 显示Snackbar
                *//*coordinatorLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Snackbar snackbar = Snackbar.make(navigationTabBar, "Coordinator NTB", Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(Color.parseColor("#9b92b3"));
                        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text))
                                .setTextColor(Color.parseColor("#423752"));
                        snackbar.show();
                    }
                }, 1000);*//*
            }
        });*/


        /*final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.ntb_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#009F90AF"));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#9f90af"));*/
    }

    /**
     * 初始化BoomMenuButton
     *
     * @param int number 几个子Button
     */
    BoomMenuButton mBoomMenuButton;
    private void initBoom(int number) {
        mBoomMenuButton = (BoomMenuButton) findViewById(R.id.main_activity_ntb_fab);
        Drawable[] drawables = new Drawable[number];
        int[] drawablesResource = new int[]{
                R.mipmap.bmb_refresh,
                R.mipmap.bmb_heart,
                R.mipmap.bmb_mark,
                R.mipmap.bmb_like,
        };
        for (int i = 0; i < number; i++)
            drawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] STRINGS = new String[]{
                "刷新",
                "综合",
                "收藏",
                "关注",
        };
        String[] Colors = {
                "#607D8B",
                "#FF5722",
                "#00BCD4",
                "#795548"};
        String[] strings = new String[number];
        for (int i = 0; i < number; i++) {
            strings[i] = STRINGS[i];
        }

        int[][] colors = new int[number][2];
        for (int i = 0; i < number; i++) {
            colors[i][1] = Color.parseColor(Colors[i]);
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

        ButtonType buttonType = ButtonType.CIRCLE;
        BoomType[] boomTypes = new BoomType[]{BoomType.LINE,BoomType.PARABOLA,BoomType.HORIZONTAL_THROW,BoomType.PARABOLA_2,BoomType.HORIZONTAL_THROW_2};
        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .subButtons(drawables, colors, strings)
                .button(buttonType)
                .boom(boomTypes[1])
                .place(PlaceType.CIRCLE_4_1)
                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .onSubButtonClick(this)
                .animator(this)
                .init(mBoomMenuButton);
    }

    // BoomMenuButton需要实现的方法
    @Override
    public void onClick(int buttonIndex) {
        ToastUtils.showShort(this, "您点击了 " +
                mBoomMenuButton.getTextViews()[buttonIndex].getText().toString() +
                " 按钮");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void toShow() {

    }

    @Override
    public void showing(float fraction) {

    }

    @Override
    public void showed() {

    }

    @Override
    public void toHide() {

    }

    @Override
    public void hiding(float fraction) {

    }

    @Override
    public void hided() {

    }
    // BoomMenuButton实现步骤到此结束

    public void showShare() {
        OnekeyShare oks = new OnekeyShare();
        oks.setTitle("分享一个Bootstrap框架网页");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.0102003.com/Bootstrap/components.html");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("安全绿色无病毒,请放心点击");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.0102003.com/Bootstrap/index.html");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.0102003.com/Bootstrap/components.html");
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
