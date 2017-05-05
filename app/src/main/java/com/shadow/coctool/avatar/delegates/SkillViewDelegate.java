package com.shadow.coctool.avatar.delegates;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Skill;
import com.shadow.coctool.databinding.ItemSkillViewBinding;

import recycledelegate.Delegate;

/**
 * Created by lxf on 2017/5/4.
 */

public class SkillViewDelegate implements Delegate {

    private Context mContext;

    public SkillViewDelegate(Context context) {
        mContext = context;
    }

    @Override
    public boolean forViewType(Object item) {
        return item instanceof Skill;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_skill_view, parent, false));
    }

    @Override
    public void bindView(Object item, RecyclerView.ViewHolder vh) {
        Skill skill = (Skill) item;
        ViewHolder mvh = (ViewHolder) vh;
        mvh.mBinding.setSkill(skill);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ItemSkillViewBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
