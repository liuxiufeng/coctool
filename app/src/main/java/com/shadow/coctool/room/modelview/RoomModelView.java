package com.shadow.coctool.room.modelview;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.BR;
import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.databinding.FragmentRoomBinding;
import com.shadow.coctool.dice.Dice;
import com.shadow.coctool.dice.DicesActivity;
import com.shadow.coctool.dice.modelview.DicesModelView;
import com.shadow.coctool.fragmentactivity.FragmentActivity;
import com.shadow.coctool.room.adapters.ChatAdapter;
import com.shadow.coctool.room.models.ISayMessage;
import com.shadow.coctool.room.models.InfoMessage;
import com.shadow.coctool.room.models.Message;
import com.shadow.coctool.room.models.OthersSayMessage;
import com.shadow.coctool.socket.SocketComp;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/21.
 */

public class RoomModelView extends BaseModelView<FragmentRoomBinding> {
    public static String PORT = "port";

    public final static int ROLL_REQUEST_CODE = 1001;

    private String input;

    @Inject
    SocketComp socketComp;

    private ChatAdapter chatAdapter;

    private Avatar avatar;

    private Gson gson;

    @Inject
    public RoomModelView() {
        socketComp = new SocketComp();
        avatar = Hawk.get(HawkKey.KEY_SELECTED_AVATAR);
        gson = new Gson();
    }

    @Override
    public void init() {
        Bundle data = getActivity().getIntent().getBundleExtra(FragmentActivity.DATA);
        int ip = data.getInt(PORT);
        String host = getActivity().getString(R.string.cocHost);

        socketComp.addReadObserver((string) -> {
            Message msg = gson.fromJson(string, Message.class);
            Handler mainHandler = new Handler(getActivity().getMainLooper());
            if (msg.getType() == Message.TLAK) {
                OthersSayMessage othersSayMessage = gson.fromJson(msg.getContent(), OthersSayMessage.class);

                mainHandler.post(() -> {
                    chatAdapter.addItem(othersSayMessage);
                    chatAdapter.notifyDataSetChanged();
                    mBinding.list.scrollToPosition(chatAdapter.getItemCount() - 1);
                });
            } else if (msg.getType() == Message.INFO) {
                InfoMessage infoMessage = gson.fromJson(msg.getContent(), InfoMessage.class);

                mainHandler.post(() -> {
                    chatAdapter.addItem(infoMessage);
                    chatAdapter.notifyDataSetChanged();
                    mBinding.list.scrollToPosition(chatAdapter.getItemCount() - 1);
                });

            }
        });

        socketComp.start(host, ip);
        initList();

        socketComp.send(gson.toJson(new Message(Message.JOIN, avatar.getName())));
    }

    private void initList() {
        chatAdapter = new ChatAdapter(getActivity());
        getBinding().list.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().list.setAdapter(chatAdapter);
    }

    @Override
    protected void setModelView() {
        getBinding().setMv(this);
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
        socketComp.stop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ROLL_REQUEST_CODE) {
                String msg = data.getStringExtra(DicesModelView.RESULT);
                sendInfo(msg);
            }
        }
    }

    public void gotoRoll() {
        DicesActivity.runForResult(getActivity(), ROLL_REQUEST_CODE, DicesModelView.FOR_RESULT);
    }

    private String warpSayMessage(String message) {
        OthersSayMessage othersSayMessage = new OthersSayMessage();
        othersSayMessage.setName(avatar.getName());
        othersSayMessage.setMessage(message);

        return gson.toJson(othersSayMessage);
    }

    public void send() {
        socketComp.send(gson.toJson(new Message(Message.TLAK, warpSayMessage(input))));

        ISayMessage msg = new ISayMessage();
        msg.setMessage(input);
        chatAdapter.addItem(msg);
        chatAdapter.notifyDataSetChanged();
        mBinding.list.scrollToPosition(chatAdapter.getItemCount() - 1);

        setInput("");
    }

    public void sendInfo(String info) {
        InfoMessage message = new InfoMessage();
        message.setMessage(info);

        chatAdapter.addItem(message);
        chatAdapter.notifyDataSetChanged();
        mBinding.list.scrollToPosition(chatAdapter.getItemCount() - 1);

        socketComp.send(gson.toJson(new Message(Message.INFO, gson.toJson(message))));
    }

    @Bindable
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        notifyPropertyChanged(BR.input);
    }

}
