package com.shadow.coctool.room.modelview;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.shadow.coctool.BR;
import com.shadow.coctool.R;
import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.databinding.FragmentRoomBinding;
import com.shadow.coctool.fragmentactivity.FragmentActivity;
import com.shadow.coctool.socket.SocketComp;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/21.
 */

public class RoomModelView extends BaseObservable implements BaseModelView {
    public static String PORT = "port";

    private FragmentRoomBinding binding;

    private String input;

    private String message;


    @Inject
    SocketComp socketComp;

    private Activity activity;

    @Inject
    public RoomModelView() {

    }

    @Override
    public void init() {
        Bundle data = activity.getIntent().getBundleExtra(FragmentActivity.DATA);
        int ip = data.getInt(PORT);
        String host = activity.getString(R.string.cocHost);

        socketComp.addReadObserver((string) -> {
            setMessage(string);
        });

        socketComp.start(host, ip);

    }

    public void onDestroy() {
        socketComp.stop();
    }

    public void send() {
        socketComp.send(input);
        setInput("");
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Bindable
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        notifyPropertyChanged(BR.input);
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    public void setBinding(FragmentRoomBinding binding) {
        this.binding = binding;
        binding.setMv(this);
    }
}
