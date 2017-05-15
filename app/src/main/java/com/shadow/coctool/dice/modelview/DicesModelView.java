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

import java.util.List;

/**
 * Created by lxf on 2017/5/8.
 */

public class DicesModelView extends BaseObservable {
    private Activity mActivity;

    private ActivityDicesBinding mBinding;

    private Avatar mAvatar;

    public DicesModelView(Activity activity, ActivityDicesBinding binding) {
        mActivity = activity;
        mBinding = binding;

        mAvatar = Hawk.get(HawkKey.KEY_SELECTED_AVATAR);

        mBinding.setMv(this);

        init();
    }

    private void init() {
        if (mAvatar != null) {
            initSkillAdapter();
        }
    }

    private void initSkillAdapter() {

        List<String> jobSkill = mAvatar.getJob().getSkillList();
        jobSkill.addAll(mAvatar.getJob().getFreeSkillList());
        mBinding.spnJobSkill.setAdapter(createArrayAdapter(jobSkill));

        mBinding.spnFree.setAdapter(createArrayAdapter(mAvatar.getFreeSkillList()));
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
        String skill = (String)mBinding.spnCommonSkill.getSelectedItem();
        useSkill(skill);
    }

    public void useJobSkill() {
        String skill = (String)mBinding.spnJobSkill.getSelectedItem();
        useSkill(skill);
    }

    public void useFreeJobSkill() {
        String skill = (String)mBinding.spnFree.getSelectedItem();
        useSkill(skill);
    }

    public void useAllSkill() {
        String skill = (String)mBinding.spnAllSkill.getSelectedItem();
        useSkill(skill);
    }

    public void nDmR() {
        int faces = Integer.valueOf((String) mBinding.spnFaces.getSelectedItem());
        int rolls = Integer.valueOf((String) mBinding.spnRolls.getSelectedItem());

        Dice dice = new Dice(faces);
        int value = dice.roll(rolls);

        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);
        adb.setMessage(String.format("%d", value));
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
}
