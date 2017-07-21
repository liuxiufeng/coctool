package com.shadow.coctool.inject;

import com.shadow.coctool.room.CreateRoomFragment;
import com.shadow.coctool.room.OptionFragment;
import com.shadow.coctool.room.RoomFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by lxf on 2017/7/20.
 */


@Module
public abstract class AndroidFragmentBindingModule {
    @ContributesAndroidInjector
    abstract OptionFragment optionFragment();

    @ContributesAndroidInjector
    abstract CreateRoomFragment createRoomFragment();

    @ContributesAndroidInjector
    abstract RoomFragment roomFragment();
}
