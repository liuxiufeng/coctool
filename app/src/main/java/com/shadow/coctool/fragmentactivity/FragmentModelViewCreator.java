package com.shadow.coctool.fragmentactivity;

import android.view.View;

import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.inject.AppModule;
import com.shadow.coctool.retrofit.ClientModule;

import dagger.Component;

/**
 * Created by LXF on 2017/7/23.
 */

public interface FragmentModelViewCreator {
    int getViewId();

    BaseModelView getModelView();
}
