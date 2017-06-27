package com.shadow.coctool.avatar.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.JsonObject;
import com.shadow.coctool.COCToolApplication;
import com.shadow.coctool.R;
import com.shadow.coctool.common.Utils;
import com.shadow.coctool.dice.Dice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


/**
 * Created by lxf on 2017/4/20.
 * COC6th Edition
 */

public class Avatar extends BaseObservable implements Serializable {
    /**
     * 力量
     */
    public static final String STRENGTH = "str";

    /**
     * 体质
     */
    public static final String CONSTITUTION = "con";

    /**
     * 体型
     */
    public static final String SIZE = "size";

    /**
     * 敏捷
     */
    public static final String DEXTERITY = "dex";

    /**
     * 外貌
     */
    public static final String APPEARANCE = "app";

    /**
     * 智力
     */
    public static final String INTELLIGENCE = "int";

    /**
     * 意志
     */
    public static final String POWER = "pow";

    /**
     * 理智
     */
    public static final String SANITY = "sanity";

    /**
     * 教育
     */
    public static final String EDUCATION = "edu";

    /**
     * 幸运
     */
    public static final String LUCKY = "luck";

    /**
     * 生命
     */
    public static final String HP = "hp";

    /**
     * 魔法值
     */
    public static final String MP = "mp";

    /**
     * 灵感
     */
    public static final String IDEA = "idea";

    /**
     * 知识
     */
    public static final String KNOWLEDGE = "know";

    /**
     * 年龄
     */
    public static final String AGE = "age";


    /**
     * 名字
     */
    private String name;

    private String sex;

    private String memo;

    private String tools;

    private Status mStatus;

    private Job job;

    private List<String> freeSkillList;

    @Inject
    public Avatar() {
        mStatus = new Status();
        createSkillMap();
    }

    public void create(List<String> rollList) {
        if (rollList == null || rollList.size() == 0) {
            return;
        }

        //6面骰
        Dice sixDice = new Dice(6);

        for (String status : rollList) {
            //3d6
            switch (status) {
                case Avatar.STRENGTH:
                    setStr(sixDice.roll(3));
                    break;
                case Avatar.DEXTERITY:
                    setDex(sixDice.roll(3));
                    break;
                case Avatar.CONSTITUTION:
                    setCon(sixDice.roll(3));
                    break;
                case Avatar.POWER:
                    setPow(sixDice.roll(3));
                    break;
                case Avatar.APPEARANCE:
                    setApp(sixDice.roll(3));
                    break;

                    //2d6+6
                case Avatar.INTELLIGENCE:
                    setInti(sixDice.roll(2) + 6);
                    break;

                case Avatar.SIZE:
                    setSize(sixDice.roll(2) + 6);
                    break;

                    //2d3
                case Avatar.EDUCATION:
                    setEdu(sixDice.roll(3) + 3);
                    break;
            }
        }

        setCurrentHP(getHp());
        setCurrentMp(getMp());
        setCurrentSan(getSan());
        statusBaseSkillModifier();
    }

    private void createSkillMap() {
        Context context = COCToolApplication.getContext();
        TypedArray idArr = context.getResources().obtainTypedArray(R.array.all_skill);
        for (int i = 0; i < idArr.length(); i++) {
            JsonObject skill = Utils.skillBuilder(idArr.getResourceId(i,0));
            mStatus.set(skill.get("name").getAsString(), skill.get("min").getAsInt());
        }
    }

    public void statusBaseSkillModifier() {
        Map modifier = new HashMap<String, Integer>();
        Context context = COCToolApplication.getContext();
        modifier.put(context.getString(R.string.skill_dodge), getDex() * 2);
        int ownLanguage = getEdu() * 5;
        ownLanguage = ownLanguage > 95 ? 95 : ownLanguage;
        modifier.put(context.getString(R.string.skill_ownLanguage), ownLanguage);
        modifier.put(context.getString(R.string.skill_lucky), getLuk());
        modifier.put(context.getString(R.string.skill_idea), getIdea());
        modifier.put(context.getString(R.string.skill_knowledge), getKnow());
        mStatus.addModifier("StatusBaseSkill", modifier);
    }

    public void set(String id, int value) {
        mStatus.set(id, value);
        notifyPropertyChanged(BR._all);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getStr() {
        return mStatus.get(STRENGTH);
    }

    public void setStr(int str) {
        mStatus.set(STRENGTH, str);
        notifyPropertyChanged(BR.str);
    }

    @Bindable
    public int getCon() {
        return mStatus.get(CONSTITUTION);
    }

    public void setCon(int con) {
        mStatus.set(CONSTITUTION, con);
        notifyPropertyChanged(BR.con);
    }

    @Bindable
    public int getSize() {
        return mStatus.get(SIZE);
    }

    public void setSize(int size) {
        mStatus.set(SIZE, size);
        notifyPropertyChanged(BR.size);
    }

    @Bindable
    public int getDex() {
        return mStatus.get(DEXTERITY);
    }

    public void setDex(int dex) {
        mStatus.set(DEXTERITY, dex);
        notifyPropertyChanged(BR.dex);
    }

    @Bindable
    public int getApp() {
        return mStatus.get(APPEARANCE);
    }


    public void setApp(int app) {
        mStatus.set(APPEARANCE, app);
        notifyPropertyChanged(BR.app);
    }

    @Bindable
    public int getInti() {
        return mStatus.get(INTELLIGENCE);
    }

    public void setInti(int inti) {
        mStatus.set(INTELLIGENCE, inti);
        notifyPropertyChanged(BR.inti);
        notifyPropertyChanged(BR.idea);
    }

    @Bindable
    public int getPow() {
        return mStatus.get(POWER);
    }

    public void setPow(int pow) {
        mStatus.set(POWER, pow);
        notifyPropertyChanged(BR.pow);
        notifyPropertyChanged(BR.san);
        notifyPropertyChanged(BR.luk);
        notifyPropertyChanged(BR.mp);
    }

    @Bindable
    public int getSan() {
        return mStatus.get(POWER) * 5;
    }

    @Bindable
    public int getCurrentSan() {
        return mStatus.get(SANITY);
    }

    public void setCurrentSan(int san) {
        mStatus.set(SANITY, san);
        notifyPropertyChanged(BR.currentSan);
    }

    @Bindable
    public int getEdu() {
        return mStatus.get(EDUCATION);
    }

    public void setEdu(int edu) {
        mStatus.set(EDUCATION, edu);
        notifyPropertyChanged(BR.edu);
        notifyPropertyChanged(BR.know);
    }

    @Bindable
    public int getLuk() {
        return mStatus.get(POWER) * 5;
    }

    @Bindable
    public int getAge() {
        return mStatus.get(AGE);
    }

    public void setAge(int age) {
        mStatus.set(AGE, age);
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public int getHp() {
        return (mStatus.get(CONSTITUTION) + mStatus.get(SIZE)) / 2;
    }

    @Bindable
    public int getCurrentHP() {
       return mStatus.get(HP);
    }

    public void setCurrentHP(int hp) {
        mStatus.set(HP, hp);
        notifyPropertyChanged(BR.currentHP);
    }

    @Bindable
    public String getDmgBounce() {
        int bounce = getStr() + getSize();
        if (bounce >= 2 && bounce < 13) {
            return "-1d6";
        } else if (bounce >= 13 && bounce < 17) {
            return "-1d4";
        } else if (bounce >= 17 && bounce < 25) {
            return "0";
        } else if (bounce >= 25 && bounce < 33) {
            return "+1d4";
        } else if (bounce >= 33 && bounce < 41) {
            return "+1d6";
        } else if (bounce >= 41 && bounce < 57) {
            return "+2d6";
        } else if (bounce >= 57 && bounce < 73) {
            return "+3d6";
        } else {
            return "+4d6";
        }
    }

    @Bindable
    public int getIdea() {
        return mStatus.get(INTELLIGENCE) * 5;
    }

    @Bindable
    public int getKnow() {
        return mStatus.get(EDUCATION) * 5;
    }

    @Bindable
    public int getMp() {
        return mStatus.get(POWER);
    }


    @Bindable
    public int getCurrentMp() {
        return mStatus.get(MP);
    }

    public void setCurrentMp(int mp) {
        mStatus.set(MP, mp);
        notifyPropertyChanged(BR.currentMp);
    }

    @Bindable
    public int getJobSkillPoint() {
        return mStatus.get(EDUCATION) * 20;
    }

    public int getFreeSkillPoint() {
        return mStatus.get(INTELLIGENCE) * 10;
    }

    public void addModifier(String id, Map modifier) {
        mStatus.addModifier(id, modifier);
        notifyPropertyChanged(BR._all);
    }

    public void removeModifier(String id) {
        mStatus.removeModifier(id);
        notifyPropertyChanged(BR._all);
    }

    public Map getModifier(String id) {
        return mStatus.getModifier(id);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getSkill(String id) {
        return mStatus.get(id);
    }

    public Skill getSkillObj(String id) {
        Skill skill = new Skill();
        int total = mStatus.get(id);
        int min = total;

        Map modifier = mStatus.getModifier(Avatar.EDUCATION);
        if (modifier != null) {
            if (modifier.containsKey(id)) {
                min = total - (int) modifier.get(id);
            }
        }

        modifier = mStatus.getModifier(Avatar.INTELLIGENCE);
        if (modifier != null) {
            if (modifier.containsKey(id)) {
                min = total - (int) modifier.get(id);
            }
        }

        skill.setName(id);
        skill.setMin(min);
        skill.setPoint(total - min);

        return skill;
    }

    public List<String> getFreeSkillList() {
        return freeSkillList;
    }

    public void setFreeSkillList(List<String> freeSkillList) {
        this.freeSkillList = freeSkillList;
    }

    @Bindable
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
        notifyPropertyChanged(BR.memo);
    }

    @Bindable
    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
        notifyPropertyChanged(BR.tools);
    }
}
