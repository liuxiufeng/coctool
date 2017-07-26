package com.shadow.coctool.dice;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityDicesBinding;
import com.shadow.coctool.dice.modelview.DicesModelView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by lxf on 2017/5/8.
 */

public class DicesActivity extends Activity {
    public static final String  MODEL = "model";

    public static void runForResult(Activity activity, int code, int model) {
        Intent intent = new Intent(activity, DicesActivity.class);
        intent.putExtra(MODEL, model);

        activity.startActivityForResult(intent, code);
    }

    @Inject DicesModelView modelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityDicesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dices);
        int model = getIntent().getIntExtra(MODEL, 0);
        modelView.setModel(model);
        modelView.setBinding(binding);
        modelView.init();
        Utils.replaceReturnTitleFragment(this, "工具");
    }
}
