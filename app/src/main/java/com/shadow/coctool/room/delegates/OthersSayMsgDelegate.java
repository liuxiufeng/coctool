package com.shadow.coctool.room.delegates;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.coctool.R;
import com.shadow.coctool.databinding.ItemMainMenuBinding;
import com.shadow.coctool.databinding.ItemOthersSayBinding;
import com.shadow.coctool.room.models.OthersSayMessage;

import recycledelegate.Delegate;

/**
 * Created by LXF on 2017/7/23.
 */

public class OthersSayMsgDelegate implements Delegate {
    private Context context;

    public OthersSayMsgDelegate(Context context) {
        this.context = context;
    }

    @Override
    public boolean forViewType(Object item) {
        return item instanceof OthersSayMessage;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_others_say, parent, false));
    }

    @Override
    public void bindView(Object item, RecyclerView.ViewHolder vh, int position) {
        OthersSayMessage msg = (OthersSayMessage) item;
        ItemOthersSayBinding binding = (ItemOthersSayBinding) ((ViewHolder)vh).mBinding;

        binding.setMv(msg);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ViewDataBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
