package com.shadow.coctool.dice;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityDicesBinding;
import com.shadow.coctool.dice.modelview.DicesModelView;

/**
 * Created by lxf on 2017/5/8.
 */

public class DicesActivity extends Activity {
    private DicesModelView modelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDicesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dices);
        modelView = new DicesModelView(this, binding);
        Utils.replaceReturnTitleFragment(this, "工具");
    }
}
