package com.shadow.coctool.dice.modelview;

import android.app.Activity;
import android.app.AlertDialog;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.ArrayAdapter;

import com.android.databinding.library.baseAdapters.BR;
import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.databinding.ActivityDicesBinding;
import com.shadow.coctool.dice.Dice;
import com.shadow.coctool.dice.DicesActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by lxf on 2017/5/8.
 */

public class DicesModelView extends BaseObservable {
    private Activity mActivity;

    private ActivityDicesBinding mBinding;

    @Named("selectedAvatar")
    private Avatar mAvatar;

    @Inject
    public DicesModelView(DicesActivity activity) {
        mActivity = activity;
        mAvatar = Hawk.get(HawkKey.KEY_SELECTED_AVATAR);
    }

    public void init() {
        if (mAvatar != null) {
            initSkillAdapter();
        }
    }

    private void initSkillAdapter() {

        List<String> jobSkill = mAvatar.getJob().getSkillList();
        if (mAvatar.getJob().getFreeSkillList() != null) {
            jobSkill.addAll(mAvatar.getJob().getFreeSkillList());
        }
        mBinding.spnJobSkill.setAdapter(createArrayAdapter(jobSkill));
        if (mAvatar.getFreeSkillList() != null) {
            mBinding.spnFree.setAdapter(createArrayAdapter(mAvatar.getFreeSkillList()));
        }
    }

    private ArrayAdapter createArrayAdapter(List array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    private void useSkill(String name) {
        int value = mAvatar.getSkill(name);
        Dice dice = new Dice(100);
        int roll = dice.roll();

        String strRst;
        if (value >= roll) {
            strRst = String.format("%d >= %d : 成功", value, roll);
        } else {
            strRst = String.format("%d < %d : 失败", value, roll);
        }

        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);
        adb.setMessage(strRst);
        adb.create().show();
    }

    public void useCommonSkill() {
        String skill = (String) mBinding.spnCommonSkill.getSelectedItem();
        useSkill(skill);
    }

    public void useJobSkill() {
        String skill = (String) mBinding.spnJobSkill.getSelectedItem();
        useSkill(skill);
    }

    public void useFreeJobSkill() {
        String skill = (String) mBinding.spnFree.getSelectedItem();
        useSkill(skill);
    }

    public void useAllSkill() {
        String skill = (String) mBinding.spnAllSkill.getSelectedItem();
        useSkill(skill);
    }

    public void nDmR() {
        int rolls = Integer.valueOf((String) mBinding.spnFaces.getSelectedItem());
        int faces = Integer.valueOf((String) mBinding.spnRolls.getSelectedItem());

        Dice dice = new Dice(faces);
        int value = dice.roll(rolls);

        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);
        adb.setMessage(String.format("%d", value));
        adb.create().show();
    }

    public void vsRoll() {
        int starter = Integer.valueOf((String) mBinding.spnStarter.getSelectedItem());
        int rec = Integer.valueOf((String) mBinding.spnRecipient.getSelectedItem());

        int value = 50 + (starter - rec) * 5;
        String rst;
        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);
        if (value >= 100) {
            rst = "自动成功";
        } else if (value <= 0) {
            rst = "自动失败";
        } else {
            Dice dice = new Dice(100);
            int rollValue = dice.roll();

            if (value < rollValue) {
                rst = String.format("%d < %d : 失败", value, rollValue);
            } else {
                rst = String.format("%d >= %d : 成功", value, rollValue);
            }
        }

        adb.setMessage(rst);
        adb.create().show();
    }

    @Bindable
    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar mAvatar) {
        this.mAvatar = mAvatar;
        notifyPropertyChanged(BR.avatar);
    }

    public void setBinding(ActivityDicesBinding binding) {
        this.mBinding = binding;
        mBinding.setMv(this);
    }
}