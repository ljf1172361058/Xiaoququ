package com.xiaoququ.androidFlux.action;

/**
 * 趣图的Action
 * Created by LiZhiHui on 2016/11/15.
 */

public class FunnyPicturesAction extends Action<Object> {
    public static final String ACTION_LOAD_START = "Load_FunnyPictures_Start";
    public static final String ACTION_LOAD_SUCCESS = "Load_FunnyPictures_Success";
    public static final String ACTION_LOAD_ERROR = "Load_FunnyPictures_Error";

    FunnyPicturesAction(String type, Object data) {
        super(type, data);
    }
}
