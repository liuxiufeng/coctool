package com.shadow.coctool.room.modelview;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.databinding.FragmentJoinBinding;
import com.shadow.coctool.fragmentactivity.FragmentActivity;
import com.shadow.coctool.room.RoomCreator;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/21.
 */

public class JoinModelView extends BaseModelView<FragmentJoinBinding> {
    private String port;

    @Inject
    public JoinModelView() {

    }

    @Override
    public void init() {

    }

    @Override
    protected void setModelView() {
        getBinding().setMv(this);
    }

    public void join() {
        if (port != null && !"".equals(port)) {
            Bundle data = new Bundle();
            data.putInt(RoomModelView.PORT, Integer.valueOf(port));

            FragmentActivity.RunWithData(mActivity, "房间:" + port, RoomCreator.class.getName(), data);
        }
    }

    @Bindable
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;

    }

    public FragmentJoinBinding getBinding() {
        return mBinding;
    }


    @Override
    public void onDestroy() {

    }


}
