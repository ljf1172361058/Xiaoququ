package com.xiaoququ.androidFlux.store;

import com.xiaoququ.androidFlux.action.Action;
import com.xiaoququ.androidFlux.action.FunnyPicturesAction;
import com.xiaoququ.androidFlux.model.FunnyPicturesModel;
import com.xiaoququ.general.bean.FunnyPicturesBean;
import com.xiaoququ.general.utils.LogUtils;
import com.xiaoququ.rxBus.event.FunnyPicturesChangeEvent;
import com.xiaoququ.rxBus.event.StoreChangeEvent;

import java.util.List;

/**
 * 趣图的Store
 * Created by LiZhiHui on 2016/11/15 14:00
 */

public class FunnyPicturesStore extends Store {
    private FunnyPicturesModel mFunnyPicturesModel = new FunnyPicturesModel();

    public List<FunnyPicturesBean.ResultBean> getData() {
        return mFunnyPicturesModel.getLists();
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case FunnyPicturesAction.ACTION_LOAD_START:
                break;

            case FunnyPicturesAction.ACTION_LOAD_SUCCESS:
                mFunnyPicturesModel.setLists((List<FunnyPicturesBean.ResultBean>) action.getData());
                LogUtils.i(mFunnyPicturesModel.getLists().toString());
                // 发送事件
                emitStoreChange();
                break;

            case FunnyPicturesAction.ACTION_LOAD_ERROR:
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
        return new FunnyPicturesChangeEvent();
    }

}
