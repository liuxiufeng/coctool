package com.shadow.coctool.room.modelview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shadow.coctool.common.BaseModelView;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/21.
 */

public class JoinModelView extends BaseObservable implements BaseModelView {
    private String port;

    @Inject
    public JoinModelView() {

    }

    @Override
    public void init() {

    }

    @Bindable
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;

    }
}
