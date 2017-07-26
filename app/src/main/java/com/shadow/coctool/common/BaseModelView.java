package com.shadow.coctool.common;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

/**
 * Created by lxf on 2017/7/21.
 */

public abstract class BaseModelView<T extends ViewDataBinding> extends BaseObservable {
    protected  Activity mActivity;

    protected T mBinding;

    abstract public void init();

    abstract protected void setModelView();

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public void setBinding(T binding) {
        this.mBinding = binding;
        setModelView();
    };

    protected T getBinding() {
        return mBinding;
    }

    public void onDestroy() {

    };

    public void onStop() {

    }

    public void onActivityResult (int requestCode, int resultCode, Intent data) {

    }
}
