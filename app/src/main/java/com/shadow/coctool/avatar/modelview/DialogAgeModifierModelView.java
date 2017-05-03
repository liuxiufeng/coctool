package com.shadow.coctool.avatar.modelview;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.model.Status;
import com.shadow.coctool.databinding.DialogAgeModifierBinding;

import java.util.Map;

/**
 * Created by lxf on 2017/5/3.
 */

public class DialogAgeModifierModelView extends BaseObservable {

    private int points;

    private Status mStatus;

    private Activity mActivity;

    private DialogAgeModifierBinding mBinding;

    public DialogAgeModifierModelView(Activity activity, DialogAgeModifierBinding binding) {
        mActivity = activity;
        mBinding = binding;
        mBinding.setMv(this);

        mStatus = new Status();
    }

    public void clear() {
        mStatus.set(Avatar.STRENGTH, 0);
        mStatus.set(Avatar.CONSTITUTION, 0);
        mStatus.set(Avatar.DEXTERITY, 0);
        mStatus.set(Avatar.APPEARANCE, 0);
        notifyPropertyChanged(BR._all);
    }

    public void add(String id) {
        int value = mStatus.get(id);
        if (value < 0) {
            points++;
            setPoints(points);
            value++;
            mStatus.set(id, value);
            notifyPropertyChanged(BR._all);
        }
    }

    public void minus(String id) {
        if (points > 0) {
            int value = mStatus.get(id);
            value--;
            mStatus.set(id, value);
            points--;
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public int getStr() {
       return mStatus.get(Avatar.STRENGTH);
    }

    @Bindable
    public int getCon() {
        return mStatus.get(Avatar.CONSTITUTION);
    }

    @Bindable
    public int getDex() {
        return mStatus.get(Avatar.DEXTERITY);
    }

    @Bindable
    public int getApp() {
        return mStatus.get(Avatar.APPEARANCE);
    }

    public void setEdu(int edu) {
        mStatus.set(Avatar.EDUCATION, edu);
    }

    @Bindable
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        notifyPropertyChanged(BR.points);
    }

    public Map getModifier() {
        return mStatus.getBaseStatus();
    }
}
