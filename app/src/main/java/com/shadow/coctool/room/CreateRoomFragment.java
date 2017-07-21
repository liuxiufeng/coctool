package com.shadow.coctool.room;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.coctool.R;
import com.shadow.coctool.databinding.FragmentCreateRoomBinding;
import com.shadow.coctool.room.modelview.CreateRoomModelView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by lxf on 2017/7/20.
 */

public class CreateRoomFragment extends Fragment {
    @Inject
    CreateRoomModelView createRoomModelView;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        AndroidInjection.inject(this);
        super.onAttach(activity);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_room, container, false);
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentCreateRoomBinding binding = DataBindingUtil.bind(view);

        createRoomModelView.setBinding(binding);
        createRoomModelView.setActivity(getActivity());
        createRoomModelView.init();
    }
}
