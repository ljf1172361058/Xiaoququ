package com.xiaoququ.androidFlux.action;

import com.xiaoququ.androidFlux.dispatcher.Dispatcher;
import com.xiaoququ.androidFlux.model.FunnyPicturesModel;
import com.xiaoququ.androidFlux.model.JokeModel;
import com.xiaoququ.general.bean.FunnyPicturesBean;
import com.xiaoququ.general.bean.JokeBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * AndroidFlux架构模式的ActionCreator模块
 * PS;ActionCreator属于数据的预处理和准备阶段.所以像网络请求、文件处理、数据预处理等等都应该在这里完成，最后产出一个结果通过Action启动整个数据流.
 *    反正就是UI逻辑在Store中处理,业务逻辑负责准备数据的事情交给ActionCreator处理.
 * Created by LiZhiHui on 2016/11/12..
 */
public class ActionCreator {

    private static ActionCreator mInstance;
    private final Dispatcher dispatcher;

    private ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionCreator getInstance(Dispatcher dispatcher) {
        if (mInstance == null) {
            mInstance = new ActionCreator(dispatcher);
        }
        return mInstance;
    }

    /**
     * 发送一段消息
     * @param type
     * @param message
     */
    public void sendMessage(String type, String message) {
        dispatcher.dispatch(new MessageAction(type, message));
    }

    /**
     * 获取趣图数据准备阶段
     */
    public void loadFunnyPictures(int pageNumber) {
        // 这里不直接显示对话框,直接发给Store让它处理显示对话框的事情
        dispatcher.dispatch(new FunnyPicturesAction(FunnyPicturesAction.ACTION_LOAD_START, null));
        FunnyPicturesModel FunnyPicturesModel = new FunnyPicturesModel();
        FunnyPicturesModel.getFunnyPictures()
                .getFunnyPictures(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FunnyPicturesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // 请求失败
                        dispatcher.dispatch(new FunnyPicturesAction(FunnyPicturesAction.ACTION_LOAD_ERROR, e));
                    }

                    @Override
                    public void onNext(FunnyPicturesBean funnyPicturesBean) {
                        // 请求成功
                        dispatcher.dispatch(new FunnyPicturesAction(FunnyPicturesAction.ACTION_LOAD_SUCCESS, funnyPicturesBean.getResult()));
                    }
                });
    }
    /**
     * 获取趣段数据准备阶段
     */
    public void loadJoke(int pageNumber) {
        // 这里不直接显示对话框,直接发给Store让它处理显示对话框的事情
        dispatcher.dispatch(new JokeAction(JokeAction.ACTION_LOAD_START, null));
        JokeModel jokeModel = new JokeModel();
        jokeModel.getJoke()
                .getJoke(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JokeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // 请求失败
                        dispatcher.dispatch(new JokeAction(JokeAction.ACTION_LOAD_ERROR, e));
                    }

                    @Override
                    public void onNext(JokeBean jokeBean) {
                        // 请求成功
                        dispatcher.dispatch(new JokeAction(JokeAction.ACTION_LOAD_SUCCESS, jokeBean.getResult()));
                    }
                });
    }
}
