package com.shadow.coctool.avatar.modelview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.avatar.adapters.AvatarCardAdapter;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.databinding.ActivityAvatarListBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxf on 2017/4/24.
 */

public class AvatarListModelView {
    private ActivityAvatarListBinding mBinding;

    private Activity mActivity;

    private AvatarCardAdapter mAdapter;

    private List mAvatarList;

    public AvatarListModelView(Activity activity, ActivityAvatarListBinding binding) {
        mActivity = activity;
        mBinding = binding;

        mAdapter = new AvatarCardAdapter(mActivity); mBinding.list.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.list.setAdapter(mAdapter);
    }

    public void onResume(){
        mAvatarList = Hawk.get(HawkKey.KEY_AVATAR_LIST);
        if (mAvatarList == null) {
            mAvatarList = new ArrayList();
            Hawk.put(HawkKey.KEY_AVATAR_LIST, mAvatarList);
        }
        updateList();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    private void updateList() {
        mAdapter.setList(mAvatarList);
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void delete(Avatar avatar) {
        mAvatarList.remove(avatar);
        Hawk.put(HawkKey.KEY_AVATAR_LIST, mAvatarList);
        updateList();
    }
}
