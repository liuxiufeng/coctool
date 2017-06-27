package com.shadow.coctool.avatar.adapters;

import com.shadow.coctool.avatar.AvatarActivity;
import com.shadow.coctool.avatar.delegates.StatusDelegate;

import javax.inject.Inject;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by lxf on 2017/6/26.
 */

public class StatusAdapter extends AbsBaseAdapter {

    @Inject
    public StatusAdapter(AvatarActivity context) {
        super(context);
    }

    @Override
    protected void init() {
        mDelegateManager.add(new StatusDelegate(mContext));
    }
}
