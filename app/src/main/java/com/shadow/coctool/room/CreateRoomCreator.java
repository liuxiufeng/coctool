package com.shadow.coctool.room;

import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.view.View;

import com.shadow.coctool.R;
import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.databinding.FragmentCreateRoomBinding;
import com.shadow.coctool.fragmentactivity.FragmentModelViewCreator;
import com.shadow.coctool.inject.DaggerModelViewComponent;
import com.shadow.coctool.inject.ModelViewComponent;
import com.shadow.coctool.room.modelview.CreateRoomModelView;

/**
 * Created by LXF on 2017/7/23.
 */

public class CreateRoomCreator implements FragmentModelViewCreator {

    @Override
    public int getViewId() {
        return R.layout.fragment_create_room;
    }

    @Override
    public BaseModelView getModelView() {
        return DaggerModelViewComponent.create().createRoomModelView();
    }


}
