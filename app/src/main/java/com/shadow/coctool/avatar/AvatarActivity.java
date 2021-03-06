package com.shadow.coctool.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.modelview.AvatarModelView;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityAvatarBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by lxf on 2017/4/25.
 */

public class AvatarActivity extends Activity {
    public static final String KEY_AVATAR = "avatar";

    @Inject
    AvatarModelView mv;

    public static void Run(Context context) {
        Intent intent = new Intent(context, AvatarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityAvatarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_avatar);
        mv.setBinding(binding);
        mv.init();

        Utils.replaceReturnTitleFragment(this, "创建" + getString(R.string.investigator));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Avatar avatar = (Avatar) data.getSerializableExtra(KEY_AVATAR);
            mv.setAvatar(avatar);
        }
    }
}
