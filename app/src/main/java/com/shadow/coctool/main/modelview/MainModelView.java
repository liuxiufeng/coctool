package com.shadow.coctool.main.modelview;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;

import com.shadow.coctool.R;
import com.shadow.coctool.databinding.ActivityMainBinding;
import com.shadow.coctool.main.adapters.IconMenuAdapter;
import com.shadow.coctool.main.model.IconMenu;

/**
 * Created by lxf on 2017/4/20.
 */

public class MainModelView {
    private final int SPAN_COUNT = 3;

    private ActivityMainBinding mBinding;

    private Activity mActivity;

    private IconMenuAdapter mIconMenuAdapter;

    public MainModelView(Activity activity) {
        mBinding = DataBindingUtil.setContentView(activity, R.layout.activity_main);
        mBinding.setMv(this);

        mActivity = activity;
        init();
    }

    private void init() {
        mBinding.grid.setLayoutManager(new LinearLayoutManager(mActivity));

        initMenu();
    }

    private void initMenu() {
        mIconMenuAdapter = new IconMenuAdapter(mActivity);

        addMenu(0, null, "人物卡片");
        addMenu(0, null, "骰子");

        mBinding.grid.setAdapter(mIconMenuAdapter);
    }

    private void addMenu(int resId, Intent intent, String text) {
        IconMenu iconMenu = new IconMenu();
        iconMenu.setResId(resId);
        iconMenu.setIntent(intent);
        iconMenu.setText(text);
        mIconMenuAdapter.addItem(iconMenu);
    }
}
