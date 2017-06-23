package com.shadow.coctool.main.modelview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.shadow.coctool.avatar.AvatarListActivity;
import com.shadow.coctool.databinding.ActivityMainBinding;
import com.shadow.coctool.dice.DicesActivity;
import com.shadow.coctool.main.MainActivity;
import com.shadow.coctool.main.adapters.IconMenuAdapter;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/4/20.
 */

public class MainModelView {
    private final int SPAN_COUNT = 3;

    private ActivityMainBinding mBinding;

    private Context mContext;

    @Inject IconMenuAdapter mIconMenuAdapter;

    @Inject
    public MainModelView(MainActivity activity) {
        mContext = activity;
    }

    public void init() {
        mBinding.setMv(this);
        mBinding.grid.setLayoutManager(new LinearLayoutManager(mContext));

        initMenu();
    }

    private void initMenu() {

        addMenu(0, new Intent(mContext, AvatarListActivity.class), "探索者卡片");
        addMenu(0, new Intent(mContext, DicesActivity.class), "工具");
        addMenu(0, null, "跑团");

        mBinding.grid.setAdapter(mIconMenuAdapter);
    }

    private void addMenu(int resId, Intent intent, String text) {
        IconMenu iconMenu = new IconMenu(mContext);
        iconMenu.setResId(resId);
        iconMenu.setIntent(intent);
        iconMenu.setText(text);
        mIconMenuAdapter.addItem(iconMenu);
    }

    public void setBinding(ActivityMainBinding mBinding) {
        this.mBinding = mBinding;
    }
}
