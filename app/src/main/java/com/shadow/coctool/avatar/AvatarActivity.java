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

/**
 * Created by lxf on 2017/4/25.
 */

public class AvatarActivity extends Activity {
    public static final String KEY_AVATAR= "avatar";


    private AvatarModelView mv;

    public static void Run(Context context) {
        Intent intent = new Intent(context, AvatarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAvatarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_avatar);
        mv = new AvatarModelView(this, binding);
        Utils.replaceReturnTitleFragment(this, "探索者");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Avatar avatar = (Avatar) data.getSerializableExtra(KEY_AVATAR);
            mv.setAvatar(avatar);
        }
    }
}
