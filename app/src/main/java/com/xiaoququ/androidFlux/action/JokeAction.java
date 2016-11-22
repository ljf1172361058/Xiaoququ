package com.xiaoququ.androidFlux.action;

/**
 * 趣段的Action
 * Created by LiZhiHui on 2016/11/18 14:00
 */

public class JokeAction extends Action<Object> {
    public static final String ACTION_LOAD_START = "Load_Joke_Start";
    public static final String ACTION_LOAD_SUCCESS = "Load_Joke_Success";
    public static final String ACTION_LOAD_ERROR = "Load_Joke_Error";

    JokeAction(String type, Object data) {
        super(type, data);
    }
}
