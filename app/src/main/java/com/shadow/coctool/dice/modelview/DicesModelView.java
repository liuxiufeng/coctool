package com.shadow.coctool.dice.modelview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import com.android.databinding.library.baseAdapters.BR;
import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.R;
import com.shadow.coctool.avatar.AvatarViewActivity;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.databinding.ActivityDicesBinding;
import com.shadow.coctool.databinding.DialogStatusChangeBinding;
import com.shadow.coctool.dice.Dice;
import com.shadow.coctool.dice.DicesActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by lxf on 2017/5/8.
 */

public class DicesModelView extends BaseObservable {
    public static final String MODIFIER = "current";

    public static final int NORMAL = 0;

    public static final String RESULT = "result";

    public static final int FOR_RESULT = 1;

    private Activity mActivity;

    private ActivityDicesBinding mBinding;

    private int model;

    @Inject DialogStatusChangeModelView dialogStatusChangeModelView;

    AlertDialog changeDialog;


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
            createStatusChangeDialog();
        }
    }

    private void initSkillAdapter() {

        List<String> jobSkill = new ArrayList<>();
        jobSkill.addAll(mAvatar.getJob().getSkillList());
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

        if (model == FOR_RESULT) {
            setResult(String.format("%s 使用了 %s : %s", mAvatar.getName(), name, strRst));
            return;
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
        int total = 0;
        String result = "";

        for(int i = 0; i < rolls; i++) {
            if(!"".equals(result)) {
                result += "+";
            }
            int value = dice.roll();
            total += value;
            result += String.valueOf(value);
        }

        result = String.format("投掷%dD%d结果为:(%s) = %d", rolls, faces, result, total);

        if (model == FOR_RESULT) {
            setResult(mAvatar.getName() + result);
            return;
        }

        AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);
        adb.setMessage(result);
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
                rst = String.format("%d < %d : 对抗失败", value, rollValue);
            } else {
                rst = String.format("%d >= %d : 对抗成功", value, rollValue);
            }
        }

        if (model == FOR_RESULT) {
            setResult(mAvatar.getName() + "对抗结果" + rst);
            return;
        }

        adb.setMessage(rst);
        adb.create().show();
    }

    private void setResult(String result) {
        Intent data = new Intent();
        data.putExtra(RESULT,result);
        mActivity.setResult(Activity.RESULT_OK, data);
        mActivity.finish();
    }

    public void viewAvatar() {
        AvatarViewActivity.Run(mActivity, mAvatar);
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

    private void createStatusChangeDialog() {
        View dialogView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_status_change, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        DialogStatusChangeBinding binding = DataBindingUtil.bind(dialogView);

        dialogStatusChangeModelView.setBinding(binding);

        builder.setView(dialogView)
                .setCancelable(false)
                .setNegativeButton("取消", (dialog, id) -> {
                })
                .setPositiveButton("确定",
                        (dialog, id) -> {
                            Map modifier = mAvatar.getModifier(MODIFIER);
                            if (modifier == null) {
                                modifier = new HashMap();
                            }

                            int bonus = modifier.get(dialogStatusChangeModelView.getId()) == null ? 0 : (int) modifier.get(dialogStatusChangeModelView.getId());

                            modifier.put(dialogStatusChangeModelView.getId(),bonus + dialogStatusChangeModelView.getModifier());


                            if (dialogStatusChangeModelView.getId().equals(Avatar.POWER)) {
                                mAvatar.setCurrentSan(mAvatar.getSan());
                                mAvatar.setCurrentMp(mAvatar.getMp());
                            }

                            String skillId = mActivity.getString(R.string.skill_cthulhuMythos);

                            mAvatar.addModifier(MODIFIER, modifier);

                            Hawk.put(HawkKey.KEY_SELECTED_AVATAR, mAvatar);
                            mAvatar.notifyPropertyChanged(BR._all);
                        }
                );

        changeDialog = builder.create();
    }

    public void changeHP() {
        dialogStatusChangeModelView.setId(Avatar.HP);
        dialogStatusChangeModelView.setCurrentValue(mAvatar.getCurrentHP());
        dialogStatusChangeModelView.setBaseValue(mAvatar.getHp());
        dialogStatusChangeModelView.setModifier(0);

        changeDialog.show();
    }

    public void changeMP() {
        dialogStatusChangeModelView.setId(Avatar.MP);
        dialogStatusChangeModelView.setCurrentValue(mAvatar.getCurrentMp());
        dialogStatusChangeModelView.setBaseValue(mAvatar.getMp());
        dialogStatusChangeModelView.setModifier(0);

        changeDialog.show();
    }

    public void changeSAN() {
        dialogStatusChangeModelView.setId(Avatar.SANITY);
        dialogStatusChangeModelView.setCurrentValue(mAvatar.getCurrentSan());
        dialogStatusChangeModelView.setBaseValue(mAvatar.getSan());
        dialogStatusChangeModelView.setModifier(0);

        changeDialog.show();
    }

    public void changePow() {
        dialogStatusChangeModelView.setId(Avatar.POWER);
        dialogStatusChangeModelView.setCurrentValue(mAvatar.getPow());
        dialogStatusChangeModelView.setBaseValue(18);
        dialogStatusChangeModelView.setModifier(0);

        changeDialog.show();
    }

    public void changeCth() {
        String id = mActivity.getString(R.string.skill_cthulhuMythos);
        dialogStatusChangeModelView.setId(id);
        dialogStatusChangeModelView.setCurrentValue(mAvatar.getSkill(id));
        dialogStatusChangeModelView.setBaseValue(99);
        dialogStatusChangeModelView.setModifier(0);

        changeDialog.show();
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }
}
