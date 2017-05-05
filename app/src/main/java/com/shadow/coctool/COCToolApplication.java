package com.shadow.coctool;

import android.app.Application;
import android.content.Context;

import com.orhanobut.hawk.Hawk;

/**
 * Created by lxf on 2017/4/26.
 */

public class COCToolApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        Hawk.init(getApplicationContext()).build();
        applicationContext = getApplicationContext();
    }

    public static Context getContext() {
       return applicationContext;
    }
}
