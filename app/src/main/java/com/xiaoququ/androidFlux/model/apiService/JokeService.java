package com.xiaoququ.androidFlux.model.apiService;

import com.xiaoququ.general.bean.JokeBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 查询趣段Service
 * Created by LiZhiHui on 2016/11/18 14:00
 */
public interface JokeService {
    @GET("")
    Observable<JokeBean> getJoke(@Query("pageNumber") int pageNumber);
}
