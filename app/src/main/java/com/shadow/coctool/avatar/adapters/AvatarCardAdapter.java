package com.shadow.coctool.avatar.adapters;

import android.content.Context;

import com.shadow.coctool.avatar.delegates.AvatarCardDelegate;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by lxf on 2017/4/24.
 */

public class AvatarCardAdapter extends AbsBaseAdapter {
    public AvatarCardAdapter(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        mDelegateManager.add(new AvatarCardDelegate(mContext));

    }
}
