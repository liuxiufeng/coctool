package com.shadow.coctool.dice.modelview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shadow.coctool.BR;
import com.shadow.coctool.databinding.DialogStatusChangeBinding;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/6/30.
 */

public class DialogStatusChangeModelView extends BaseObservable {

    private String id;

    private int currentValue;

    private int baseValue;

    private int modifier;

    private DialogStatusChangeBinding mBinding;

    @Inject
    public DialogStatusChangeModelView(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public int getCurrentValue() {
        return currentValue + modifier;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        notifyPropertyChanged(BR.currentValue);
    }

    @Bindable
    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
        notifyPropertyChanged(BR.baseValue);
    }

    public void add() {
        if (getCurrentValue() < baseValue) {
            modifier++;
            notifyPropertyChanged(BR.currentValue);
        }
    }

    public void minus() {
        if (getCurrentValue() > 0) {
            modifier--;
            notifyPropertyChanged(BR.currentValue);
        }
    }

    public DialogStatusChangeBinding getBinding() {
        return mBinding;
    }

    public void setBinding(DialogStatusChangeBinding binding) {
        this.mBinding = binding;
        mBinding.setMv(this);
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
}
