package com.shadow.coctool.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shadow.coctool.main.modelview.MainModelView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MainModelView(this);
    }
}
