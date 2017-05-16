package com.shadow.coctool.avatar.modelview;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.shadow.coctool.avatar.AvatarActivity;
import com.shadow.coctool.avatar.adapters.SkillAdapter;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.model.FreeSkill;
import com.shadow.coctool.avatar.model.Skill;
import com.shadow.coctool.databinding.ActivitySkillBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxf on 2017/5/4.
 */

public class SkillModelView extends BaseObservable {
    public static final int MODEL_JOB = 1;

    public static final int MODEL_FREE = 2;

    private Activity mActivity;

    private ActivitySkillBinding mBinding;

    private SkillAdapter mAdapter;

    private int points;

    private Avatar mAvatar;

    private int model;

    private List<Skill> skillList;

    private List<Skill> freeSkillList;

    public SkillModelView(Activity activity, ActivitySkillBinding binding) {
        mActivity = activity;
        mBinding = binding;
        mBinding.setMv(this);

        skillList = new ArrayList<>();
        freeSkillList = new ArrayList<>();
    }

    public void start() {
        mAdapter = new SkillAdapter(mActivity, mAvatar);
        mBinding.list.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.list.setAdapter(mAdapter);

        if (model == MODEL_JOB) {
            setPoints(mAvatar.getJobSkillPoint());
            addJobSkill();
            addFreeSkill();
        } else if (model == MODEL_FREE) {
            setPoints(mAvatar.getFreeSkillPoint());
            addFreeSkill();
        }
    }

    private void addJobSkill() {
        List<String> skillNames = mAvatar.getJob().getSkillList();
        for (String name : skillNames) {
            Skill skill = new Skill();
            skill.setName(name);
            skill.setMin(mAvatar.getSkill(name));
            try {
                skill = skill.clone();
                mAdapter.addItem(skill);
                skillList.add(skill);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void addFreeSkill() {
        int num = mAvatar.getJob().getSkillNum() == 0 ? 8 : mAvatar.getJob().getSkillNum();
        num = model == MODEL_FREE ? 5 : num;
        for (int i = 0; i < num - skillList.size(); i++) {
            mAdapter.addItem(new FreeSkill());
        }
        mAdapter.notifyDataSetChanged();
    }

    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar mAvatar) {
        this.mAvatar = mAvatar;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    @Bindable
    public int getPoints() {
        int left = points;
        for (Skill skill : skillList) {
            left -= skill.getPoint();
        }

        for (Skill skill : freeSkillList) {
            left -= skill.getPoint();
        }
        return left;
    }

    public void setPoints(int points) {
        this.points = points;
        notifyPropertyChanged(BR.points);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Integer event) {
        notifyPropertyChanged(BR.points);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateFreeSkill(FreeSkill freeSkill) {
        if (freeSkillList.contains(freeSkill.getPreSkill())) {
            freeSkillList.remove(freeSkill.getPreSkill());
        }
        if (freeSkill.getSkill() != null) {
            freeSkillList.add(freeSkill.getSkill());
        }
        notifyPropertyChanged(BR.points);
    }

    public void save() {
        if (points < 0) {
            Toast.makeText(mActivity, "点数分配过多", Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, Integer> map = new HashMap();
        for (Skill skill : skillList) {
            map.put(skill.getName(), skill.getPoint());
        }

        List<String> free = new ArrayList<>();
        for (Skill skill : freeSkillList) {
            map.put(skill.getName(), skill.getPoint());
            free.add(skill.getName());
        }

        if (model == MODEL_JOB) {
            mAvatar.addModifier(Avatar.EDUCATION, map);
            mAvatar.getJob().setFreeSkillList(free);
        } else if (model == MODEL_FREE) {
            mAvatar.addModifier(Avatar.INTELLIGENCE, map);
            mAvatar.setFreeSkillList(free);
        }

        Intent intent = new Intent();
        intent.putExtra(AvatarActivity.KEY_AVATAR, mAvatar);
        mActivity.setResult(Activity.RESULT_OK, intent);
        mActivity.finish();
    }
}
