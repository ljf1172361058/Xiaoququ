package com.xiaoququ.androidFlux.store;

import android.util.Log;

import com.xiaoququ.androidFlux.action.Action;
import com.xiaoququ.androidFlux.action.MessageAction;
import com.xiaoququ.androidFlux.model.Message;
import com.xiaoququ.rxBus.event.StoreChangeEvent;

/**
 * Created by LiZhiHui on 2016/11/12.
 */

public class MessageStore extends Store {
    private Message mMessage = new Message();

    public MessageStore() {}

    public String getMessage() {
        return mMessage.getMessage();
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case MessageAction.ACTION_OLD_MESSAGE:
                Log.i("test", "ACTION_OLD_MESSAGE");
                mMessage.setMessage((String) action.getData());
                break;
            case MessageAction.ACTION_NEW_MESSAGE:
                Log.i("test", "ACTION_NEW_MESSAGE");
                mMessage.setMessage((String) action.getData());
                break;
            default:
        }
        emitStoreChange();
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent();
    }
}
