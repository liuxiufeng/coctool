package com.shadow.coctool.avatar.modelview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.R;
import com.shadow.coctool.avatar.SkillActivity;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityAvatarBinding;
import com.shadow.coctool.databinding.DialogAgeModifierBinding;

import java.util.List;
import java.util.Map;

/**
 * Created by lxf on 2017/4/25.
 */

public class AvatarModelView extends BaseObservable {

    public static final int REQUEST_SKILL = 101;

    private Activity mActivity;

    private ActivityAvatarBinding mBinding;

    private Avatar mAvatar;

    private DialogAgeModifierModelView mDialogMV;

    private AlertDialog mAgeDialog;

    public AvatarModelView(Activity activity, ActivityAvatarBinding binding) {
        mActivity = activity;
        mBinding = binding;
        binding.setMv(this);

        createAgeModifierDialog();
        newAvatar();
    }

    private void newAvatar() {
        mAvatar = new Avatar();
        mAvatar.create();

        mBinding.spnSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String sex = (String) parent.getSelectedItem();
                mAvatar.setSex(sex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mBinding.spnAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int age = Integer.valueOf((String) parent.getSelectedItem());
                Map modifier = mAvatar.getModifier(Avatar.AGE);
                int edu = mAvatar.getEdu();
                if (modifier != null) {
                    edu = edu - (int) modifier.get(Avatar.EDUCATION);
                }

                int minAge = edu + 4;

                if (age < minAge) {
                    mBinding.spnAge.setSelection(minAge - 14);
                    return;
                }
                mAvatar.setAge(age);

                int eduBounce = (age - minAge) / 10;
                mDialogMV.clear();
                mDialogMV.setEdu(eduBounce);

                if (age > 50) {
                    int points = (age - 40) / 10;
                    mDialogMV.setPoints(points);
                    mAgeDialog.show();
                } else {
                    mAvatar.addModifier(Avatar.EDUCATION, mDialogMV.getModifier());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBinding.spnJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TypedArray idArr = mActivity.getResources().obtainTypedArray(R.array.jobs_id);
                mAvatar.setJob(Utils.jobBuilder(idArr.getResourceId(position, 0)));
                mAvatar.removeModifier(Avatar.EDUCATION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createAgeModifierDialog() {
        View dialogView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_age_modifier, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        DialogAgeModifierBinding binding = DataBindingUtil.bind(dialogView);

        mDialogMV = new DialogAgeModifierModelView(mActivity, binding);

        builder.setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                mAvatar.addModifier(Avatar.EDUCATION, mDialogMV.getModifier());
                            }
                        });

        mAgeDialog = builder.create();
    }

    @Bindable
    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar mAvatar) {
        this.mAvatar = mAvatar;
        notifyPropertyChanged(BR.avatar);
    }

    public void save() {
        if (mAvatar.getName() == null || mAvatar.getName().equals("")) {
            Toast.makeText(mActivity, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        List list = Hawk.get(HawkKey.KEY_AVATAR_LIST);
        list.add(mAvatar);
        Hawk.put(HawkKey.KEY_AVATAR_LIST, list);
        mActivity.finish();
    }

    public void startSkillActivity(int model) {
        SkillActivity.run(mActivity, mAvatar, model, REQUEST_SKILL);
    }
}
