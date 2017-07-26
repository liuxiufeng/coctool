package com.shadow.coctool.room.modelview;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.widget.Toast;

import com.shadow.coctool.BR;
import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.databinding.FragmentCreateRoomBinding;
import com.shadow.coctool.fragmentactivity.FragmentActivity;
import com.shadow.coctool.repositories.COCRepository;
import com.shadow.coctool.retrofit.ClientModule;
import com.shadow.coctool.retrofit.Result;
import com.shadow.coctool.room.RoomCreator;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxf on 2017/7/20.
 */

public class CreateRoomModelView extends BaseModelView<FragmentCreateRoomBinding> {
    private String name;

    private String port;

   @Inject COCRepository cocRepository;

    @Inject
    public CreateRoomModelView() {
        cocRepository = new COCRepository();
    }

    @Override
    public void init() {

    }

    @Override
    protected void setModelView() {
        getBinding().setMv(this);
    }

    public void create() {
        cocRepository.createRoom(name, port, new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Result result = (Result) o;
                if (result.isSuccess()) {
                    Bundle data = new Bundle();
                    data.putInt(RoomModelView.PORT, Integer.valueOf(port));

                    FragmentActivity.RunWithData(mActivity, "房间:" + port, RoomCreator.class.getName(), data);
                } else {
                    Toast.makeText(getActivity(), "该端口已被占用请使用其他端口", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "无法连接服务器", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
        notifyPropertyChanged(BR.port);
    }


    @Override
    public void onDestroy() {

    }

}

