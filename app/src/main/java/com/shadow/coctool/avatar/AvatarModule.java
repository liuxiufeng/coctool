package com.shadow.coctool.avatar;


import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxf on 2017/6/23.
 */

@Module
public abstract class AvatarModule {
    @Nullable
    @Named("selectedAvatar")
    @Provides static Avatar provideSelectedAvatar() {
       return Hawk.get(HawkKey.KEY_SELECTED_AVATAR);
    }
}
