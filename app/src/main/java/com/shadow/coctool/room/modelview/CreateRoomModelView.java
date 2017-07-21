package com.shadow.coctool.room.modelview;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.shadow.coctool.BR;
import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.databinding.FragmentCreateRoomBinding;
import com.shadow.coctool.fragmentactivity.FragmentActivity;
import com.shadow.coctool.repositories.COCRepository;
import com.shadow.coctool.retrofit.Result;
import com.shadow.coctool.room.RoomFragment;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxf on 2017/7/20.
 */

public class CreateRoomModelView extends BaseObservable implements BaseModelView {
    private String name;

    private String port;

    private FragmentCreateRoomBinding binding;

    private Activity mActivity;

    @Inject
    COCRepository cocRepository;

    @Inject
    public CreateRoomModelView() {
    }

    @Override
    public void init() {

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

                    FragmentActivity.RunWithData(mActivity, "房间", RoomFragment.class.getName(), data);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
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

    public void setBinding(FragmentCreateRoomBinding binding) {
        this.binding = binding;
        binding.setMv(this);
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }
}

