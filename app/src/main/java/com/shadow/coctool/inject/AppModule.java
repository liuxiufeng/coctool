package com.shadow.coctool.inject;

import android.app.Application;
import android.content.Context;

import com.shadow.coctool.COCToolApplication;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lxf on 2017/6/23.
 */

@Module
public abstract class AppModule {

    @Binds
    abstract Application application(COCToolApplication cocToolApplication);

    @Provides static Context provideContext(Application application) {
        return application;
    }

}
