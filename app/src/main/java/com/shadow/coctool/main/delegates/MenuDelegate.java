package com.shadow.coctool.main.delegates;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.coctool.R;
import com.shadow.coctool.databinding.ItemMainMenuBinding;
import com.shadow.coctool.main.model.IconMenu;

import recycledelegate.Delegate;

/**
 * Created by lxf on 2017/4/20.
 */

public class MenuDelegate implements Delegate {
    private Context mContext;

    public MenuDelegate(Context context) {
        mContext = context;
    }

    @Override
    public boolean forViewType(Object item) {
        return item instanceof IconMenu;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_menu, parent, false));
    }

    @Override
    public void bindView(Object item, RecyclerView.ViewHolder vh) {
        IconMenu iconMenu = (IconMenu) item;
        ViewHolder mVh = (ViewHolder) vh;
        mVh.mBinding.setMenu(iconMenu);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ItemMainMenuBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
