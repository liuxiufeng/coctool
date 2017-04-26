package com.shadow.coctool.avatar.modelview;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.AdapterView;

import com.android.databinding.library.baseAdapters.BR;
import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.databinding.ActivityAvatarBinding;
import com.shadow.coctool.dice.Dice;

import java.util.List;

/**
 * Created by lxf on 2017/4/25.
 */

public class AvatarModelView extends BaseObservable {
    public static final int MODEL_NEW = 0;

    public static final int MODEL_VIEW = 1;

    private Activity mActivity;

    private ActivityAvatarBinding mBinding;

    private Avatar mAvatar;

    private int model;

    public AvatarModelView(Activity activity, ActivityAvatarBinding binding) {
        mActivity = activity;
        mBinding = binding;
        binding.setMv(this);
    }



    private void newAvatar() {
        mAvatar = new Avatar();
        //6面骰
        Dice sixDice = new Dice(6);

        //3d6
        mAvatar.setBaseStr(sixDice.roll(3));
        mAvatar.setStr(mAvatar.getBaseStr());

        mAvatar.setBaseDex(sixDice.roll(3));
        mAvatar.setDex(mAvatar.getBaseDex());

        mAvatar.setBaseCon(sixDice.roll(3));
        mAvatar.setCon(mAvatar.getBaseCon());

        mAvatar.setBasePow(sixDice.roll(3));
        mAvatar.setPow(mAvatar.getBasePow());

        mAvatar.setBaseApp(sixDice.roll(3));
        mAvatar.setApp(mAvatar.getBaseApp());

        //2d6+6
        mAvatar.setBaseInti(sixDice.roll(2) + 6);
        mAvatar.setInti(mAvatar.getBaseInti());

        mAvatar.setBaseSize(sixDice.roll(2) + 6);
        mAvatar.setSize(mAvatar.getBaseSize());

        //2d3
        mAvatar.setBaseEdu(sixDice.roll(2) + 3);
        mAvatar.setEdu(mAvatar.getBaseEdu());

        mAvatar.cacFixed();

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
                int age = Integer.valueOf((String)parent.getSelectedItem());
                mAvatar.setAge(age);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar mAvatar) {
        this.mAvatar = mAvatar;
    }

    @Bindable
    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
        notifyPropertyChanged(BR.model);
        if (model == AvatarModelView.MODEL_NEW) {
            newAvatar();
        }
    }

    public void save() {
        List list = Hawk.get(HawkKey.KEY_AVATAR_LIST);
        list.add(mAvatar);
        Hawk.put(HawkKey.KEY_AVATAR_LIST, list);
        mActivity.finish();
    }
}
