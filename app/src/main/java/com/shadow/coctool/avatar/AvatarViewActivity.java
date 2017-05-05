package com.shadow.coctool.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.modelview.AvatarViewModelView;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityAvatarViewBinding;

/**
 * Created by lxf on 2017/5/5.
 */

public class AvatarViewActivity extends Activity {
    public static final String KEY_AVATAR= "avatar";

    private AvatarViewModelView mv;

    public static void Run(Context context, Avatar avatar) {
        Intent intent = new Intent(context, AvatarViewActivity.class);
        intent.putExtra(KEY_AVATAR, avatar);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAvatarViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_avatar_view);
        Avatar avatar = (Avatar) getIntent().getSerializableExtra(KEY_AVATAR);
        mv = new AvatarViewModelView(this, binding, avatar);
        Utils.replaceReturnTitleFragment(this, "探索者");
    }
}
