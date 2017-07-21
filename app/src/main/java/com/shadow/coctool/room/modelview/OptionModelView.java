package com.shadow.coctool.room.modelview;

import android.app.Activity;

import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.databinding.FragmentOptionBinding;
import com.shadow.coctool.fragmentactivity.FragmentActivity;
import com.shadow.coctool.room.CreateRoomFragment;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/20.
 */

public class OptionModelView implements BaseModelView {

    private FragmentOptionBinding binding;

    private Activity mActivity;

    @Inject
    public OptionModelView() {
    }

    @Override
    public void init() {

    }

    public void createRoom() {
        FragmentActivity.Run(mActivity, "创建房间", CreateRoomFragment.class.getName());
    }

    public void gotoRoomListFragment() {

    }

    public void setBinding(FragmentOptionBinding binding) {
        this.binding = binding;
        binding.setMv(this);
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }
}
