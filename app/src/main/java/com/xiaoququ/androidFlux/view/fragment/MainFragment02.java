package com.xiaoququ.androidFlux.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xiaoququ.R;
import com.xiaoququ.androidFlux.action.ActionCreator;
import com.xiaoququ.androidFlux.dispatcher.Dispatcher;
import com.xiaoququ.androidFlux.store.FunnyPicturesStore;
import com.xiaoququ.androidFlux.view.base.BaseRxFragment_v4;
import com.xiaoququ.general.adapter.RvAdapter_FunnyPictures;
import com.xiaoququ.general.bean.FunnyPicturesBean;
import com.xiaoququ.general.utils.LogUtils;
import com.xiaoququ.general.utils.ToastUtils;
import com.xiaoququ.rxBus.RxBus;
import com.xiaoququ.rxBus.event.FunnyPicturesChangeEvent;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;


public class MainFragment02 extends BaseRxFragment_v4 {
    /**
     * Android Flux架构模式相关依赖
     */
    private Dispatcher mDispatcher;
    private ActionCreator mActionCreator;
    public FunnyPicturesStore mStore;

    protected View mView;
    protected XRecyclerView mXRecyclerView;

    /** 趣图数据集合(不要默认赋值null) */
    private List<FunnyPicturesBean.ResultBean> mList = new ArrayList<FunnyPicturesBean.ResultBean>();
    /** 趣图适配器 */
    RvAdapter_FunnyPictures mRvAdapter_funnyPictures;
    /** 当前数据页码 */
    int mPageNumber = 1;

    public MainFragment02() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static MainFragment02 newInstance() {
        MainFragment02 fragment = new MainFragment02();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_main_fragment02, container, false);
        initDependencies();
        initView();
        setListener();
        initDate();
        return mView;
    }

    /**
     * 初始化相关依赖
     */
    private void initDependencies() {
        mDispatcher = Dispatcher.getInstance();
        mActionCreator = ActionCreator.getInstance(mDispatcher);
        mStore = new FunnyPicturesStore();
        mDispatcher.register(mStore);
    }

    @Override
    public void initView() {
        initToolbar(R.id.fragment_main_fragment01_toolbar);
        // 初始化XRecyclerView
        initXRecyclerView();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initDate() {
        // 订阅加载趣图的事件
        subscribeFunnyPictures();
        // 发送加载趣图数据的请求
        mActionCreator.loadFunnyPictures(mPageNumber);
    }

    /**
     * 初始化XRecyclerView
     */
    public void initXRecyclerView() {
        mXRecyclerView = (XRecyclerView) mView.findViewById(R.id.fragment_main_fragment02_xrv);
        mXRecyclerView.setHasFixedSize(true);
        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mXRecyclerView.setArrowImageView(R.mipmap.xrv_iconfont_downgrey);
        // 为RecyclerView添加默认动画效果,默认不写也行
        mXRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(2000);
                        // 刷新完成
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar snackbar = Snackbar.make(mXRecyclerView, "啊!我被刷新了", Snackbar.LENGTH_SHORT);
                                snackbar.getView().setBackgroundColor(Color.parseColor("#9b92b3"));
                                ((TextView) snackbar.getView().findViewById(R.id.snackbar_text))
                                        .setTextColor(Color.parseColor("#423752"));
                                snackbar.setAction("不要点我", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ToastUtils.showShort(getActivity(), "说了不要点我,然并卵");
                                    }
                                });
                                snackbar.show();
                                mXRecyclerView.refreshComplete();
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onLoadMore() {
                // 发送加载趣图数据的请求
                mActionCreator.loadFunnyPictures(mPageNumber);
            }
        });
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(
                        getActivity(), LinearLayoutManager.VERTICAL, false
                )
        );
        // 设置适配器
        mRvAdapter_funnyPictures = new RvAdapter_FunnyPictures(getActivity(), R.layout.ntb_item_list_fmf02, mList);
        mXRecyclerView.setAdapter(mRvAdapter_funnyPictures);
    }

    /**
     * 订阅加载趣图的事件
     */
    private void subscribeFunnyPictures() {
        // 订阅事件
        RxBus.getInstance()
                .toObserverable(FunnyPicturesChangeEvent.class) // 只接收FunnyPicturesChangeEvent及其子类,不接收其父类或兄弟类等
                /**
                 * this.<TestEvent>bindUntilEvent(FragmentEvent.DESTROY):【手动】手动指定序列结束的时间(Activity使用ActivityEvent,Fragment选择FragmentEvent)
                 * this.<TestEvent>bindToLifecycle():【自动】让RxLifecycle自动在适当的时间结束序列
                 *
                 * PS:1.需要集成Rx系列的Activity或者Fragment
                 *    2.bindXXX()前要加泛型,泛型类型需要和observer的泛型类型保持一致
                 */
                .compose(this.<FunnyPicturesChangeEvent>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Action1<FunnyPicturesChangeEvent>() {
                    @Override
                    public void call(FunnyPicturesChangeEvent storeChangeEvent) {
                        // 上拉加载完成
                        if (mXRecyclerView != null) {
                            mXRecyclerView.loadMoreComplete();
                        }
                        List<FunnyPicturesBean.ResultBean> list = mStore.getData();
                        // 数据加载成功
                        if (list != null && list.size() > 0) {
                            LogUtils.i("02接收到" + list.size() + "条消息");
                            mList.addAll(list);
                            // 这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义
                            mRvAdapter_funnyPictures.notifyItemRangeInserted(mList.size(), list.size());
                            // 请求数据成功页码加一
                            mPageNumber++;
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 反注册Store
        mDispatcher.unregister(mStore);
    }
}
