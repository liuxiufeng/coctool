package com.shadow.coctool.fragmentactivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.common.Utils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Created by lxf on 2017/7/20.
 */

public class FragmentActivity extends Activity implements HasFragmentInjector {
    public static final String TITLE = "title";

    public static final String CLASS_NAME = "className";

    public static final String DATA = "data";

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    public static void Run(Context context, String title, String className) {
        Intent intent = new Intent(context, FragmentActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(CLASS_NAME, className);

        context.startActivity(intent);
    }

    public static void RunWithData(Context context, String title, String className, Bundle data) {
        Intent intent = new Intent(context, FragmentActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(CLASS_NAME, className);
        intent.putExtra(DATA, data);

        context.startActivity(intent);
    }

    public static void RunForResult(Context context, String title, String className, int code) {
        Intent intent = new Intent(context, FragmentActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(CLASS_NAME, className);

        ((Activity)context).startActivityForResult(intent, code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        String title = getIntent().getStringExtra(TITLE);
        String clsName = getIntent().getStringExtra(CLASS_NAME);

        Utils.replaceReturnTitleFragment(this, title);

        Fragment fragment = getFragment(clsName);
        replaceFragment(fragment);

    }

    private Fragment getFragment(String className) {
        try {
            Class cls = Class.forName(className);
            return (Fragment) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ftr = fm.beginTransaction();
        ftr.replace(R.id.content, fragment);
        ftr.commit();
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
}
