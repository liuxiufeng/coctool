package com.shadow.coctool.avatar.delegates;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.StatusItem;
import com.shadow.coctool.databinding.ItemStatusSelectBinding;

import recycledelegate.Delegate;

/**
 * Created by lxf on 2017/6/26.
 */

public class StatusDelegate implements Delegate {
    private Context mContext;

    public StatusDelegate(Context context){
        mContext = context;
    }

    @Override
    public boolean forViewType(Object item) {
        return item instanceof StatusItem;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_status_select, parent, false));
    }

    @Override
    public void bindView(Object item, RecyclerView.ViewHolder vh) {
        StatusItem statusItem = (StatusItem) item;
        ItemStatusSelectBinding binding = ((ViewHolder) vh).mBinding;
        binding.setMv(statusItem);

        binding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            statusItem.setCheck(isChecked);
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ItemStatusSelectBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
