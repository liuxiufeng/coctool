package com.shadow.coctool.avatar.modelview;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;

import com.android.databinding.library.baseAdapters.BR;
import com.shadow.coctool.avatar.AvatarEditActivity;
import com.shadow.coctool.avatar.adapters.SkillViewAdapter;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.model.Skill;
import com.shadow.coctool.databinding.ActivityAvatarEditBinding;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/4/25.
 */

public class AvatarEditModelView extends BaseObservable {

    private Activity mActivity;

    private ActivityAvatarEditBinding mBinding;

    private int position;

    private Avatar mAvatar;

    @Inject
    public AvatarEditModelView(AvatarEditActivity activity) {
        mActivity = activity;
    }

    public void init() {
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

    public void edit() {
        Intent intent = new Intent();
        intent.putExtra(AvatarEditActivity.KEY_AVATAR, mAvatar);
        intent.putExtra(AvatarEditActivity.KEY_POSITION, position);

        mActivity.setResult(Activity.RESULT_OK, intent);
        mActivity.finish();
    }

    @Bindable
    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar mAvatar) {
        this.mAvatar = mAvatar;
        notifyPropertyChanged(BR.avatar);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setBinding(ActivityAvatarEditBinding binding) {
        this.mBinding = binding;
        mBinding.setMv(this);
    }
}
