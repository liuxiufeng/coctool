package com.shadow.coctool.retrofit.Clients;


import com.shadow.coctool.retrofit.Result;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by lxf on 2017/7/4.
 */

public interface COCClient {

    @FormUrlEncoded
    @POST("server")
    Observable<Result<Integer>> newRoom(@FieldMap Map<String, String> params);

    @GET("server")
    Observer<Result> getRoomList();

}
