package com.xiaoququ.androidFlux.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
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
import com.xiaoququ.androidFlux.store.JokeStore;
import com.xiaoququ.androidFlux.view.base.BaseRxFragment_v4;
import com.xiaoququ.general.adapter.RvAdapter_Joke;
import com.xiaoququ.general.bean.JokeBean;
import com.xiaoququ.general.utils.LogUtils;
import com.xiaoququ.general.utils.ToastUtils;
import com.xiaoququ.rxBus.RxBus;
import com.xiaoququ.rxBus.event.JokeStoreChangeEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;


public class MainFragment01 extends BaseRxFragment_v4 {
    @BindView(R.id.fragment_main_fragment01_toolbar)
    Toolbar fragmentMainFragment01Toolbar;
    /**
     * Android Flux架构模式相关依赖
     */
    private Dispatcher mDispatcher;
    private ActionCreator mActionCreator;
    public JokeStore mStore;

    protected View mView;
    protected XRecyclerView mXRecyclerView;

    /**
     * 数据集合(不要默认赋值null)
     */
    private List<JokeBean.ResultBean> mList = new ArrayList<JokeBean.ResultBean>();
    /**
     * 适配器
     */
    RvAdapter_Joke mRvAdapter_Joke;
    /**
     * 当前数据页码
     */
    int mPageNumber = 1;

    public MainFragment01() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment01 newInstance() {
        MainFragment01 fragment = new MainFragment01();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_main_fragment01, container, false);
        ButterKnife.bind(this, mView);
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
        mStore = new JokeStore();
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
        // 订阅加载数据的事件
        subscribeJoke();
        // 发送加载数据的请求
        mActionCreator.loadJoke(mPageNumber);
    }

    /**
     * 初始化XRecyclerView
     */
    public void initXRecyclerView() {
        mXRecyclerView = (XRecyclerView) mView.findViewById(R.id.fragment_main_fragment01_xrv);
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
                new Thread() {
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
                mActionCreator.loadJoke(mPageNumber);
            }
        });
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(
                        getActivity(), LinearLayoutManager.VERTICAL, false
                )
        );
        // 设置适配器
        mRvAdapter_Joke = new RvAdapter_Joke(getActivity(), R.layout.ntb_item_list_fmf01, mList);
        mXRecyclerView.setAdapter(mRvAdapter_Joke);
    }

    /**
     * 订阅加载数据的事件
     */
    private void subscribeJoke() {
        // 订阅事件
        RxBus.getInstance()
                .toObserverable(JokeStoreChangeEvent.class) // 只接收JokeStoreChangeEvent及其子类,不接收其父类或兄弟类等
                /**
                 * this.<TestEvent>bindUntilEvent(FragmentEvent.DESTROY):【手动】手动指定序列结束的时间(Activity使用ActivityEvent,Fragment选择FragmentEvent)
                 * this.<TestEvent>bindToLifecycle():【自动】让RxLifecycle自动在适当的时间结束序列
                 *
                 * PS:1.需要集成Rx系列的Activity或者Fragment
                 *    2.bindXXX()前要加泛型,泛型类型需要和observer的泛型类型保持一致
                 */
                .compose(this.<JokeStoreChangeEvent>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Action1<JokeStoreChangeEvent>() {
                    @Override
                    public void call(JokeStoreChangeEvent storeChangeEvent) {
                        // 上拉加载完成
                        if (mXRecyclerView != null) {
                            mXRecyclerView.loadMoreComplete();
                        }
                        List<JokeBean.ResultBean> list = (List<JokeBean.ResultBean>) mStore.getData();
                        // 数据加载成功
                        if (list != null && list.size() > 0) {
                            LogUtils.i("01接收到" + list.size() + "条消息");
                            mList.addAll(list);
                            // 这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义
                            mRvAdapter_Joke.notifyItemRangeInserted(mList.size(), list.size());
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
