package com.shadow.coctool.avatar.delegates;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.shadow.coctool.R;
import com.shadow.coctool.avatar.AvatarEditActivity;
import com.shadow.coctool.avatar.AvatarViewActivity;
import com.shadow.coctool.avatar.model.Avatar;
import com.shadow.coctool.common.HawkKey;
import com.shadow.coctool.databinding.ItemAvatarCardBinding;

import org.greenrobot.eventbus.EventBus;

import recycledelegate.Delegate;

/**
 * Created by lxf on 2017/4/24.
 */

public class AvatarCardDelegate implements Delegate {
    private Context mContext;

    public AvatarCardDelegate(Context context) {
        mContext = context;
    }

    @Override
    public boolean forViewType(Object item) {
        return item instanceof Avatar;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_avatar_card, parent, false));
    }

    @Override
    public void bindView(Object item, RecyclerView.ViewHolder vh, int position) {
        ViewHolder mVh = (ViewHolder) vh;
        Avatar avatar = (Avatar) item;
        mVh.mBinding.setAvatar(avatar);

        mVh.mBinding.main.setOnClickListener((view) -> {
            AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
            CharSequence items[] = new CharSequence[]{"查看", "选择", "修改", "删除"};
            adb.setItems(items, (dialog, which) -> {
                switch (which) {
                    case 0:
                        check(avatar);
                        break;
                    case 1:
                        select(avatar);
                        break;
                    case 2:
                        edit(avatar, position);
                        break;
                    case 3:
                        delete(avatar);
                        break;
                }
            });

            adb.create().show();
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ItemAvatarCardBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);

        }
    }

    private void check(Avatar avatar) {
        AvatarViewActivity.Run(mContext, avatar);
    }

    private void select(Avatar avatar) {
        Toast.makeText(mContext, String.format("已选择%s作为使用角色", avatar.getName()), Toast.LENGTH_SHORT).show();
        Hawk.put(HawkKey.KEY_SELECTED_AVATAR, avatar);
    }

    private void edit(Avatar avatar, int position) {
        AvatarEditActivity.Run(mContext, avatar, position);
    }

    private void delete(Avatar avatar) {
        EventBus.getDefault().post(avatar);
    }
}
