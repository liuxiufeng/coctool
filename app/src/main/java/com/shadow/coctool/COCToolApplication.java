package com.shadow.coctool;

import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.inject.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by lxf on 2017/4/26.
 */

public class COCToolApplication extends DaggerApplication{

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext()).build();
        applicationContext = getApplicationContext();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    public static Context getContext() {
       return applicationContext;
    }
}
