package com.shadow.coctool.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.modelview.AvatarEditModelView;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityAvatarEditBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by lxf on 2017/5/5.
 */

public class AvatarEditActivity extends Activity {
    public static final String KEY_AVATAR = "avatar";

    public static final String KEY_POSITION = "position";

    public static final int REQUEST_CODE = 1001;

    @Inject AvatarEditModelView mv;


    public static void Run(Context context, Avatar avatar, int position) {
        Intent intent = new Intent(context, AvatarEditActivity.class);
        intent.putExtra(KEY_AVATAR, avatar);
        intent.putExtra(KEY_POSITION, position);
        ((Activity)context).startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityAvatarEditBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_avatar_edit);
        Avatar avatar = (Avatar) getIntent().getSerializableExtra(KEY_AVATAR);
        int position = getIntent().getIntExtra(KEY_POSITION, -1);
        mv.setAvatar(avatar);
        mv.setPosition(position);
        mv.setBinding(binding);

        mv.init();

        Utils.replaceReturnTitleFragment(this, "修改" + getString(R.string.investigator));
    }
}
