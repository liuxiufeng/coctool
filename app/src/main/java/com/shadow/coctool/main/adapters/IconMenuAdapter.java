package com.shadow.coctool.main.adapters;

import com.shadow.coctool.main.MainActivity;
import com.shadow.coctool.main.delegates.MenuDelegate;

import javax.inject.Inject;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by lxf on 2017/4/20.
 */

public class IconMenuAdapter extends AbsBaseAdapter {

    @Inject
    public IconMenuAdapter(MainActivity activity) {
        super(activity);
        init();
    }

    @Override
    protected void init() {
       mDelegateManager.add(new MenuDelegate(mContext));
    }
}
