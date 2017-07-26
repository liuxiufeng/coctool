package com.shadow.coctool.room.modelview;

import android.app.Activity;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.databinding.FragmentOptionBinding;
import com.shadow.coctool.fragmentactivity.FragmentActivity;
import com.shadow.coctool.room.CreateRoomCreator;
import com.shadow.coctool.room.JoinCreator;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/20.
 */

public class OptionModelView extends BaseModelView<FragmentOptionBinding> {

    @Inject
    public OptionModelView() {
    }

    @Override
    public void init() {
        if (Hawk.get(HawkKey.KEY_SELECTED_AVATAR) == null) {
            Toast.makeText(mActivity, "请选择要使用的调查员", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }

    }

    @Override
    protected void setModelView() {
        mBinding.setMv(this);
    }

    public void createRoom() {
        FragmentActivity.Run(mActivity, "创建房间", CreateRoomCreator.class.getName());
    }

    public void gotoRoomListFragment() {
        FragmentActivity.Run(mActivity, "加入房间", JoinCreator.class.getName());
    }


    @Override
    public void onDestroy() {

    }

}
