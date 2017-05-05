package com.shadow.coctool.main.modelview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.shadow.coctool.avatar.AvatarListActivity;
import com.shadow.coctool.databinding.ActivityMainBinding;
import com.shadow.coctool.main.adapters.IconMenuAdapter;

/**
 * Created by lxf on 2017/4/20.
 */

public class MainModelView {
    private final int SPAN_COUNT = 3;

    private ActivityMainBinding mBinding;

    private Activity mActivity;

    private IconMenuAdapter mIconMenuAdapter;

    public MainModelView(Activity activity, ActivityMainBinding binding) {
        mBinding = binding;
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

        addMenu(0, new Intent(mActivity, AvatarListActivity.class), "探索者卡片");
        addMenu(0, null, "骰子");
        addMenu(0, null, "跑团");

        mBinding.grid.setAdapter(mIconMenuAdapter);
    }

    private void addMenu(int resId, Intent intent, String text) {
        IconMenu iconMenu = new IconMenu(mActivity);
        iconMenu.setResId(resId);
        iconMenu.setIntent(intent);
        iconMenu.setText(text);
        mIconMenuAdapter.addItem(iconMenu);
    }
}
