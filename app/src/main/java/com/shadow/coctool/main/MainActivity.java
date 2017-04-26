package com.shadow.coctool.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shadow.coctool.R;
import com.shadow.coctool.databinding.ActivityMainBinding;
import com.shadow.coctool.main.modelview.MainModelView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        new MainModelView(this, binding);
    }
}
