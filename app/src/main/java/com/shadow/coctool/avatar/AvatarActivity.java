package com.shadow.coctool.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.modelview.AvatarModelView;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityAvatarBinding;

/**
 * Created by lxf on 2017/4/25.
 */

public class AvatarActivity extends Activity {
    public static final String KEY_AVATAR= "avatar";

    public static final String KEY_MODEL = "model";



    public static void Run(Context context) {
        Intent intent = new Intent(context, AvatarActivity.class);
        intent.putExtra(KEY_MODEL, AvatarModelView.MODEL_NEW);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAvatarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_avatar);
        AvatarModelView mv = new AvatarModelView(this, binding);
        mv.setModel(getIntent().getIntExtra(KEY_MODEL, 0));
        Utils.replaceReturnTitleFragment(this, "人物");
    }
}
