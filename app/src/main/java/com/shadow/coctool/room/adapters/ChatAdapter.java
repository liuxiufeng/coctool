package com.shadow.coctool.room.adapters;

import android.content.Context;

import com.shadow.coctool.room.delegates.ISayMsgDelegate;
import com.shadow.coctool.room.delegates.InfoMsgDelegate;
import com.shadow.coctool.room.delegates.OthersSayMsgDelegate;

import recycledelegate.AbsBaseAdapter;

/**
 * Created by LXF on 2017/7/23.
 */

public class ChatAdapter extends AbsBaseAdapter{

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        mDelegateManager.add(new OthersSayMsgDelegate(mContext))
        .add(new ISayMsgDelegate(mContext))
        .add(new InfoMsgDelegate(mContext));

    }
}
