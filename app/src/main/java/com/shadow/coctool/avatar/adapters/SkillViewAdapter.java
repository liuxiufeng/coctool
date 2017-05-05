package com.shadow.coctool.avatar.adapters;

import android.content.Context;

import com.shadow.coctool.avatar.delegates.SkillViewDelegate;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by lxf on 2017/4/24.
 */

public class SkillViewAdapter extends AbsBaseAdapter {
    public SkillViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        mDelegateManager.add(new SkillViewDelegate(mContext));
    }
}
