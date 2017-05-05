package com.shadow.coctool.avatar.modelview;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;

import com.android.databinding.library.baseAdapters.BR;
import com.shadow.coctool.avatar.adapters.SkillViewAdapter;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.model.Skill;
import com.shadow.coctool.databinding.ActivityAvatarViewBinding;

import java.util.List;

/**
 * Created by lxf on 2017/4/25.
 */

public class AvatarViewModelView extends BaseObservable {

    private Activity mActivity;

    private ActivityAvatarViewBinding mBinding;

    private Avatar mAvatar;

    public AvatarViewModelView(Activity activity, ActivityAvatarViewBinding binding, Avatar avatar) {
        mActivity = activity;
        mBinding = binding;
        mAvatar = avatar;
        binding.setMv(this);

        mBinding.jobSkillList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.jobSkillList.setNestedScrollingEnabled(false);
        mBinding.jobSkillList.setHasFixedSize(false);

        mBinding.freeSkillList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.freeSkillList.setNestedScrollingEnabled(false);
        mBinding.freeSkillList.setHasFixedSize(false);

        SkillViewAdapter adapter = new SkillViewAdapter(mActivity);
        addSkill(adapter, mAvatar.getJob().getSkillList());
        addSkill(adapter, mAvatar.getJob().getFreeSkillList());
        mBinding.jobSkillList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter = new SkillViewAdapter(mActivity);
        addSkill(adapter, mAvatar.getFreeSkillList());
        mBinding.freeSkillList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void addSkill (SkillViewAdapter adapter, List<String> list) {
        if (list == null) {
            return;
        }
        for (String skillId : list) {
            Skill skill = new Skill();
            skill.setName(skillId);
            skill.setMin(mAvatar.getSkill(skillId));
            adapter.addItem(skill);
        }
    }

    @Bindable
    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar mAvatar) {
        this.mAvatar = mAvatar;
        notifyPropertyChanged(BR.avatar);
    }
}
