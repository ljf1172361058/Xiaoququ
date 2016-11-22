package com.xiaoququ.androidFlux.model.apiService;

import com.xiaoququ.general.bean.FunnyPicturesBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 查询趣图Service
 * Created by LiZhiHui on 2016/11/4 15:23.
 */
public interface  FunnyPicturesService {
    @GET("")
    Observable<FunnyPicturesBean> getFunnyPictures(@Query("pageNumber") int pageNumber);
}
