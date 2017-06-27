package com.shadow.coctool.avatar;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.modelview.AvatarListModelView;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.databinding.ActivityAvatarListBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by lxf on 2017/4/20.
 */

public class AvatarListActivity extends Activity {
    @Inject AvatarListModelView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityAvatarListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_avatar_list);
        mv.setBinding(binding);
        mv.init();

        Utils.replaceReturnTitleFragment(this, "探索者卡片", ()->{
            AvatarActivity.Run(this);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mv.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mv.onStop();
    }
}
