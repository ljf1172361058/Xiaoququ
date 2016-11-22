package com.xiaoququ.androidFlux.model;

import com.xiaoququ.androidFlux.model.apiService.FunnyPicturesService;
import com.xiaoququ.general.bean.FunnyPicturesBean;
import com.xiaoququ.general.utils.ApiUtils;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 趣图Model
 *
 * Created by Lizhihui on 2016/11/15 14:50.
 */

public class FunnyPicturesModel extends Model{
    /**
     * 每次请求返回的趣图数据
     */
    private List<FunnyPicturesBean.ResultBean> lists = null;

    public List<FunnyPicturesBean.ResultBean> getLists() {
        return lists;
    }

    public void setLists(List<FunnyPicturesBean.ResultBean> lists) {
        this.lists = lists;
    }

    public FunnyPicturesModel() {
    }

    /**
     * 返回查询趣图Service
     */
    public FunnyPicturesService getFunnyPictures() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                // 使用Gson数据转换器(返回实体类是JSON Bean时使用,注意使用前需complie对应的转换器,默认转换为OkHttp的ResponseBody类型,否则返回实体类与转换器类型不一致会报错)
                .addConverterFactory(GsonConverterFactory.create())
                // 使用RxJava作为回调适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // .createWithScheduler(Schedulers.io()) 方法可设置默认在io线程
                .build();
        return retrofit.create(FunnyPicturesService.class);
    }
}
