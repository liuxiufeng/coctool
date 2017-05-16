package com.shadow.coctool.avatar.delegates;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.shadow.coctool.R;
import com.shadow.coctool.avatar.model.Skill;
import com.shadow.coctool.databinding.ItemOccupationSkillBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import recycledelegate.Delegate;

/**
 * Created by lxf on 2017/5/4.
 */

public class OccupationSkillDelegate implements Delegate {

    private Context mContext;

    public OccupationSkillDelegate(Context context) {
        mContext = context;
    }

    @Override
    public boolean forViewType(Object item) {
        return item instanceof Skill;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_occupation_skill, parent, false));
    }

    @Override
    public void bindView(Object item, RecyclerView.ViewHolder vh) {
        Skill skill = (Skill) item;
        ViewHolder mvh = (ViewHolder) vh;
        mvh.mBinding.setSkill(skill);

        List<String> dropdown = new ArrayList<>();
        for(int i = skill.getMin(); i <= 99; i++) {
           dropdown.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item, dropdown);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mvh.mBinding.total.setAdapter(adapter);

        mvh.mBinding.total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int total = Integer.valueOf((String)parent.getSelectedItem());
                skill.setPoint(total - skill.getMin());
                EventBus.getDefault().post(new Integer(0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ItemOccupationSkillBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
