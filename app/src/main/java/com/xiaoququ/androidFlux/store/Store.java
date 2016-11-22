package com.xiaoququ.androidFlux.store;

import com.xiaoququ.androidFlux.action.Action;
import com.xiaoququ.rxBus.RxBus;
import com.xiaoququ.rxBus.event.StoreChangeEvent;


/**
 * AndroidFlux架构模式的Store模块(这里使用RxBus来实现Store)
 * PS:网络读操作在Store中来做,并在此处理请求结果,反正就是UI逻辑在Store中处理,业务逻辑负责准备数据的事情交给ActionCreator处理.
 * Created by LiZhiHui on 2016/11/12.
 */

public abstract class Store {
    private static final RxBus rxBus = RxBus.getInstance();
    /**
     * 发出事件
     *
     */
    void emitStoreChange() {
        rxBus.send(changeEvent());
    }

    public abstract StoreChangeEvent changeEvent();
    public abstract void onAction(Action action);

}
