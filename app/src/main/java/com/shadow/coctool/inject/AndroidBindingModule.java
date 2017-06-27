package com.shadow.coctool.inject;

import com.shadow.coctool.avatar.AvatarActivity;
import com.shadow.coctool.avatar.AvatarEditActivity;
import com.shadow.coctool.avatar.AvatarListActivity;
import com.shadow.coctool.avatar.AvatarViewActivity;
import com.shadow.coctool.avatar.SkillActivity;
import com.shadow.coctool.dice.DicesActivity;
import com.shadow.coctool.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by lxf on 2017/6/23.
 */

@Module
public abstract class AndroidBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract AvatarListActivity avatarListActivity();

    @ContributesAndroidInjector
    abstract AvatarActivity avatarActivity();

    @ContributesAndroidInjector
    abstract AvatarEditActivity avatarEditActivity();

    @ContributesAndroidInjector
    abstract AvatarViewActivity avatarViewActivity();

    @ContributesAndroidInjector
    abstract SkillActivity skillActivity();

    @ContributesAndroidInjector
    abstract DicesActivity dicesActivity();
}
