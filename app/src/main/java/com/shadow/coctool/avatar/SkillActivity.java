package com.shadow.coctool.avatar;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.modelview.SkillModelView;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivitySkillBinding;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lxf on 2017/5/4.
 */

public class SkillActivity extends Activity {

    public static final String KEY_AVATAR = "avatar";

    public static final String KEY_MODEL = "model";

    private SkillModelView mModelView;

    public static void run(Activity activity, Avatar avatar, int model, int code) {
        Intent intent = new Intent(activity, SkillActivity.class);
        intent.putExtra(KEY_AVATAR, avatar);
        intent.putExtra(KEY_MODEL, model);
        activity.startActivityForResult(intent, code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySkillBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_skill);
        Avatar avatar = (Avatar) getIntent().getSerializableExtra(KEY_AVATAR);
        int model = getIntent().getIntExtra(KEY_MODEL, 0);

        SkillModelView skillModelView = new SkillModelView(this, binding);
        skillModelView.setModel(model);
        skillModelView.setAvatar(avatar);
        skillModelView.start();
        mModelView = skillModelView;

        EventBus.getDefault().register(mModelView);

        Utils.replaceReturnTitleFragment(this, "技能分配");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mModelView);
    }
}
