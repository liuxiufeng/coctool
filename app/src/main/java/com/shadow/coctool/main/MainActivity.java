package com.shadow.coctool.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shadow.coctool.R;
import com.shadow.coctool.databinding.ActivityMainBinding;
import com.shadow.coctool.main.modelview.MainModelView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject MainModelView mModelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mModelView.setBinding(binding);
        mModelView.init();
    }
}
