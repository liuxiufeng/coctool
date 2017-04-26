package com.shadow.coctool.common;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.shadow.coctool.R;
import com.shadow.coctool.returntitle.ReturnTitleFragment;
import com.shadow.coctool.returntitle.modelviews.ReturnTitleModelView;

/**
 * Created by lxf on 2017/4/21.
 */

public class Utils {
    public static void replaceReturnTitleFragment(Activity activity, String title, int resId, int model, ReturnTitleModelView.AddCallback callback) {
        ReturnTitleModelView modelView = new ReturnTitleModelView();
        modelView.setTitle(title);
        modelView.setModel(model);
        modelView.setAddCallback(callback);

        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ftr = fm.beginTransaction();
        ReturnTitleFragment fragment = new ReturnTitleFragment();
        fragment.setModelView(modelView);
        ftr.replace(resId, fragment);
        ftr.commit();

    }

    public static void replaceReturnTitleFragment(Activity activity, String title) {
        replaceReturnTitleFragment(activity, title, R.id.title, ReturnTitleModelView.MODEL_NORMAL, null);
    }

    public static void replaceReturnTitleFragment(Activity activity, String title, ReturnTitleModelView.AddCallback callback) {
        replaceReturnTitleFragment(activity, title, R.id.title, ReturnTitleModelView.MODEL_ADD, callback);
    }
}
