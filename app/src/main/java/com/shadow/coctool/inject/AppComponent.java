package com.shadow.coctool.inject;

import com.shadow.coctool.COCToolApplication;
import com.shadow.coctool.retrofit.ClientModule;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by lxf on 2017/6/23.
 */
@Component(
        modules = {
                AndroidBindingModule.class,
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ClientModule.class
        }
)
public interface AppComponent extends AndroidInjector<COCToolApplication>{
        @Component.Builder
        abstract class Builder extends AndroidInjector.Builder<COCToolApplication> {
        }
}
