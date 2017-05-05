package com.shadow.coctool.avatar.adapters;

import android.content.Context;

import com.shadow.coctool.avatar.delegates.FreeSkillDelegate;
import com.shadow.coctool.avatar.delegates.OccupationSkillDelegate;
import com.shadow.coctool.avatar.model.Avatar;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by lxf on 2017/5/4.
 */

public class SkillAdapter extends AbsBaseAdapter {
    private Avatar mAvatar;

    public SkillAdapter(Context context, Avatar avatar) {
        super(context);
        mAvatar = avatar;
        mDelegateManager.add(new OccupationSkillDelegate(mContext))
                .add(new FreeSkillDelegate(mContext, mAvatar));
    }

    @Override
    protected void init() {

    }
}
