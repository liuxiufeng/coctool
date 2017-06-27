package com.shadow.coctool.avatar.modelview;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v7.widget.LinearLayoutManager;

import com.shadow.coctool.avatar.AvatarActivity;
import com.shadow.coctool.avatar.adapters.StatusAdapter;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.avatar.model.StatusItem;
import com.shadow.coctool.databinding.DialogRerollBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/6/26.
 */

public class StatusSelectModelView extends BaseObservable {
    private Context mContext;

    private DialogRerollBinding mBinding;

    private List<StatusItem> itemList;

    private Avatar mAvatar;

    @Inject StatusAdapter mAdapter;

    @Inject
    public StatusSelectModelView(AvatarActivity activity) {
        mContext = activity;
    }

    public void init() {
        mBinding.list.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.list.setAdapter(mAdapter);
        initItemList();
        mAdapter.setList(itemList);
    }

    private void initItemList() {
        itemList = new ArrayList<>();

        itemList.add(new StatusItem("力量", Avatar.STRENGTH, mAvatar.getStr()));

        itemList.add(new StatusItem("敏捷", Avatar.DEXTERITY, mAvatar.getDex()));

        itemList.add(new StatusItem("体质", Avatar.CONSTITUTION, mAvatar.getCon()));

        itemList.add(new StatusItem("意志", Avatar.POWER, mAvatar.getPow()));

        itemList.add(new StatusItem("外貌", Avatar.APPEARANCE, mAvatar.getApp()));

        itemList.add(new StatusItem("智力", Avatar.INTELLIGENCE, mAvatar.getInti()));

        itemList.add(new StatusItem("体型", Avatar.SIZE, mAvatar.getSize()));

        itemList.add(new StatusItem("教育", Avatar.EDUCATION, mAvatar.getEdu()));
    }

    public void setBinding(DialogRerollBinding binding) {
        this.mBinding = binding;
    }

    public List<String> getSelected() {
        List<String> selectList = new ArrayList<String>();

        for(StatusItem statusItem : itemList) {
            if (statusItem.isCheck()) {
                selectList.add(statusItem.getName());
            }
        }

        return selectList;
    }

    public Avatar getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(Avatar mAvatar) {
        this.mAvatar = mAvatar;
    }
}
