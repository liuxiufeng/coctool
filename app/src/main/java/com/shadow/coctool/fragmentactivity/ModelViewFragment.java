package com.shadow.coctool.fragmentactivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.coctool.R;
import com.shadow.coctool.common.BaseModelView;

import dagger.android.AndroidInjection;

/**
 * Created by LXF on 2017/7/23.
 */

public class ModelViewFragment extends Fragment {

    FragmentModelViewCreator creator;

    BaseModelView modelView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String className = getActivity().getIntent().getStringExtra(FragmentActivity.CLASS_NAME);
        creator = getFragmentModelViewCreator(className);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        String className = getActivity().getIntent().getStringExtra(FragmentActivity.CLASS_NAME);
        creator = getFragmentModelViewCreator(className);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(creator.getViewId(), container, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        modelView.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment 1", "onDestroyView");
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        modelView.onDestroy();
   }

   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.bind(view);
        modelView = creator.getModelView();
        modelView.setBinding(binding);
        modelView.setActivity(getActivity());
        modelView.init();
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        modelView.onActivityResult(requestCode, resultCode, data);
    }

    private FragmentModelViewCreator getFragmentModelViewCreator(String className) {
        try {
            Class cls = Class.forName(className);
            return (FragmentModelViewCreator) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
