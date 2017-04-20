package com.shadow.coctool.main.adapters;

import android.content.Context;

import com.shadow.coctool.main.delegates.MenuDelegate;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by lxf on 2017/4/20.
 */

public class IconMenuAdapter extends AbsBaseAdapter {

    public IconMenuAdapter(Context context) {
        super(context);
    }

    @Override
    protected void init() {
       mDelegateManager.add(new MenuDelegate(mContext));
    }
}
