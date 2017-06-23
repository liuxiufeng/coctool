package com.shadow.coctool.avatar.delegates;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.model.FreeSkill;
import com.shadow.coctool.avatar.model.Skill;
import com.shadow.coctool.databinding.ItemFreeSkillBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import recycledelegate.Delegate;

/**
 * Created by lxf on 2017/5/4.
 */

public class FreeSkillDelegate implements Delegate {

    private Context mContext;

    private Avatar mAvatar;

    public FreeSkillDelegate(Context context, Avatar avatar) {
        mContext = context;
        mAvatar = avatar;
    }

    @Override
    public boolean forViewType(Object item) {
        return item instanceof FreeSkill;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_free_skill, parent, false));
    }

    @Override
    public void bindView(Object item, RecyclerView.ViewHolder vh) {
        FreeSkill skill = (FreeSkill) item;
        ViewHolder mvh = (ViewHolder) vh;

        mvh.mBinding.total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int total = Integer.valueOf((String)parent.getSelectedItem());
                skill.getSkill().setPoint(total - skill.getSkill().getMin());
                EventBus.getDefault().post(new Integer(0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mvh.mBinding.spnSkill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String skillId = (String) parent.getSelectedItem();
                if (skillId != null && !skillId.equals("")) {
                    Skill newSkill = new Skill();
                    newSkill.setName(skillId);
                    newSkill.setMin(mAvatar.getSkill(skillId));
                    skill.setPreSkill(skill.getSkill());
                    skill.setSkill(newSkill);
                    createAdapter(skill.getSkill(), mvh.mBinding);
                } else {
                    skill.setPreSkill(skill.getSkill());
                    skill.setSkill(null);
                    mvh.mBinding.total.setAdapter(null);
                }
                EventBus.getDefault().post(skill);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createAdapter(Skill skill, ItemFreeSkillBinding binding) {
        if (skill == null) return;
        List<String> dropdown = new ArrayList<>();
        for(int i = skill.getMin(); i <= 99; i++) {
            dropdown.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item, dropdown);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.total.setAdapter(adapter);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ItemFreeSkillBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}