package com.shadow.coctool.returntitle.modelviews;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shadow.coctool.BR;
import com.shadow.coctool.databinding.FragmentReturnTitleBinding;

/**
 * Created by lxf on 2017/4/21.
 */

public class ReturnTitleModelView extends BaseObservable {
    public static final int MODEL_NORMAL = 0;

    public static final int MODEL_ADD = 1;

    private Activity mActivity;

    private int model;

    private AddCallback mAddCallback;

    private FragmentReturnTitleBinding mBinding;

    private String title;

    public ReturnTitleModelView() {

    }

    public ReturnTitleModelView(Activity activity, FragmentReturnTitleBinding binding) {
        mActivity = activity;
        mBinding = binding;
        mBinding.setMv(this);
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public void setBinding(FragmentReturnTitleBinding binding) {
        mBinding = binding;
        mBinding.setMv(this);
    }

    @Bindable
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void onClick() {
        mActivity.finish();
    }

    public void onAdd() {
        if (mAddCallback!= null ) {
            mAddCallback.execute();
        }
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public AddCallback getAddCallback() {
        return mAddCallback;
    }

    public void setAddCallback(AddCallback mAddCallback) {
        this.mAddCallback = mAddCallback;
    }

    public interface AddCallback {
        void execute();
    }
}
