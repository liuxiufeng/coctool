package com.shadow.coctool.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxf on 2017/7/4.
 */

public class ClientFactory {

    private ClientFactory() {

    }

    public static ClientFactory getInstance() {
        return LazyHolder.instance;
    }

    public Object build(String baseUrl, Class cls)  {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit =  builder
                .baseUrl(baseUrl)
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpBuilder.build())
                .build();

        return retrofit.create(cls);
    }

    private static class LazyHolder {
        public static final ClientFactory instance = new ClientFactory();
    }
}
