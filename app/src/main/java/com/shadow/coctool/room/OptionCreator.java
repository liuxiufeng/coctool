package com.shadow.coctool.room;

import com.shadow.coctool.R;
import com.shadow.coctool.common.BaseModelView;
import com.shadow.coctool.fragmentactivity.FragmentModelViewCreator;
import com.shadow.coctool.inject.DaggerModelViewComponent;
import com.shadow.coctool.room.modelview.OptionModelView;

import javax.inject.Inject;

/**
 * Created by LXF on 2017/7/23.
 */

public class OptionCreator implements FragmentModelViewCreator {

    public void OptionCreator () {
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_option;
    }

    @Override
    public BaseModelView getModelView() {
        return DaggerModelViewComponent.create().optionModelView();
    }
}
