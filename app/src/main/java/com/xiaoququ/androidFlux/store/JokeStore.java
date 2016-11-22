package com.xiaoququ.androidFlux.store;

import com.xiaoququ.androidFlux.action.Action;
import com.xiaoququ.androidFlux.action.JokeAction;
import com.xiaoququ.androidFlux.model.JokeModel;
import com.xiaoququ.general.bean.JokeBean;
import com.xiaoququ.general.utils.LogUtils;
import com.xiaoququ.rxBus.event.JokeStoreChangeEvent;
import com.xiaoququ.rxBus.event.StoreChangeEvent;

import java.util.List;

/**
 * 趣段的Store
 * Created by LiZhiHui on 2016/11/18 14:00.
 */

public class JokeStore extends Store {
    private JokeModel mJokeModel = new JokeModel();

    public List<JokeBean.ResultBean> getData() {
        return mJokeModel.getLists();
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case JokeAction.ACTION_LOAD_START:
                break;

            case JokeAction.ACTION_LOAD_SUCCESS:
                mJokeModel.setLists((List<JokeBean.ResultBean>) action.getData());
                 LogUtils.i(mJokeModel.getLists().toString());
                // 发送事件
                emitStoreChange();
                break;

            case JokeAction.ACTION_LOAD_ERROR:
                // 打印出错信息
                ((Throwable) action.getData()).printStackTrace();
                // 发送事件
                emitStoreChange();
                break;

            default:
        }
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new JokeStoreChangeEvent();
    }

}
