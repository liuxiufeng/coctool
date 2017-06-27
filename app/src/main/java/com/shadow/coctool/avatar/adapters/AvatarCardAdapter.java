package com.shadow.coctool.avatar.adapters;

import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.avatar.AvatarListActivity;
import com.shadow.coctool.avatar.delegates.AvatarCardDelegate;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;

import javax.inject.Inject;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by lxf on 2017/4/24.
 */

public class AvatarCardAdapter extends AbsBaseAdapter {

    @Inject
    public AvatarCardAdapter(AvatarListActivity context) {
        super(context);
    }

    @Override
    protected void init() {
        mDelegateManager.add(new AvatarCardDelegate(mContext));
    }

    public void replace(int position, Avatar avatar) {
        if (position < 0 || position > mItems.size()) {
            return;
        }

        mItems.remove(position);
        mItems.add(position, avatar);

        Hawk.put(HawkKey.KEY_AVATAR_LIST, mItems);
        notifyDataSetChanged();
    }
}
