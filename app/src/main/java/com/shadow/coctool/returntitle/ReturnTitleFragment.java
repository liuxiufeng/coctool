package com.shadow.coctool.returntitle;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.coctool.R;
import com.shadow.coctool.databinding.FragmentReturnTitleBinding;
import com.shadow.coctool.returntitle.modelviews.ReturnTitleModelView;

/**
 * Created by lxf on 2017/4/21.
 */

public class ReturnTitleFragment extends Fragment {
    private ReturnTitleModelView mModelView;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_return_title, container, false);
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentReturnTitleBinding binding = DataBindingUtil.bind(view);
        if (mModelView == null) {
            getActivity().finish();
            return;
        }
        mModelView.setActivity(getActivity());
        mModelView.setBinding(binding);
    }

    public void setModelView(ReturnTitleModelView modelView) {
        mModelView = modelView;
    }
}
