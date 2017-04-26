package com.shadow.coctool.avatar.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.io.Serializable;
import java.util.List;


/**
 * Created by lxf on 2017/4/20.
 * COC7th 3rd Edition
 */

public class Avatar extends BaseObservable implements Serializable{
    /**
     * 名字
     */
    private String name;

    private String sex;

    private int baseStr;

    /**
     * 力量
     */
    private int str;

    private int baseCon;

    /**
     * 体质
     */
    private int con;

    private int baseSize;

    /**
     * 体型
     */
    private int size;

    private int baseDex;

    /**
     * 敏捷
     */
    private int dex;

    private int baseApp;

    /**
     * 外貌
     */
    private int app;

    private int baseInti;

    /**
     * 智力
     */
    private int inti;

    private int basePow;

    /**
     * 意志
     */
    private int pow;

    /**
     * 最大理智点
     */
    private int maxSan;

    /**
     * 理智点
     */
    private int san;

    private int baseEdu;

    /**
     * 教育
     */
    private int edu;

    private int baseLuk;

    /**
     * 幸运
     */
    private int luk;

    /**
     * 年龄
     */
    private int age;

    /**
     * 最大生命值
     */
    private int maxHp;

    /**
     * 当前生命值
     */
    private int hp;


    /**
     * 最大MP
     */
    private int maxMp;

    /**
     * MP
     */
    private int mp;

    /**
     * 灵感
     */
    private int idea;

    /**
     * 知识
     */
    private int know;

    /**
     * 增伤
     */
    private String dmgBounce;

    private int jobSkillPoint;

    private int intSkillPoint;

    private Job job;

    private List<Skill> intSkillList;

    public void cacFixed() {
        maxSan = pow * 5;
        maxHp = (con + size) / 2;
        maxMp = pow;
        setSan(maxSan);
        setHp(maxHp);
        setMp(maxMp);

        setIdea(inti * 5);
        setLuk(pow * 5);
        setKnow(edu * 5);
        setJobSkillPoint(edu * 20);
        setIntSkillPoint(inti * 10);

        cacDmgBounce();
    }

    public void cacDmgBounce() {
        int sum = str + size;
        if (sum >= 2 && sum <= 12) {
            setDmgBounce("-1d6");
        } else if (sum > 12 && sum <= 16) {
            setDmgBounce("-1d4");
        } else if (sum > 16 && sum <= 24) {
            setDmgBounce("0");
        } else if (sum > 24 && sum <= 32) {
            setDmgBounce("+1d4");
        } else if (sum > 32 && sum <= 40) {
            setDmgBounce("+1d6");
        } else if (sum > 40 && sum <= 56) {
            setDmgBounce("+2d6");
        } else if (sum > 56 && sum <= 72) {
            setDmgBounce("+3d6");
        } else if (sum <= 88) {
            setDmgBounce("+4d6");
        }
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
        return str;
    }

    public void setStr(int str) {
        this.str = str;
        notifyPropertyChanged(BR.str);
    }

    @Bindable
    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
        notifyPropertyChanged(BR.con);
    }

    @Bindable
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        notifyPropertyChanged(BR.size);
    }

    @Bindable
    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
        notifyPropertyChanged(BR.dex);
    }

    @Bindable
    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
        notifyPropertyChanged(BR.app);
    }

    @Bindable
    public int getInti() {
        return inti;
    }

    public void setInti(int inti) {
        this.inti = inti;
        notifyPropertyChanged(BR.inti);
    }

    @Bindable
    public int getPow() {
        return pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
        notifyPropertyChanged(BR.pow);
    }

    @Bindable
    public int getSan() {
        return san;
    }

    public void setSan(int san) {
        this.san = san;
        notifyPropertyChanged(BR.san);
    }

    @Bindable
    public int getEdu() {
        return edu;
    }

    public void setEdu(int edu) {
        this.edu = edu;
        notifyPropertyChanged(BR.edu);
    }

    @Bindable
    public int getLuk() {
        return luk;
    }

    public void setLuk(int luk) {
        this.luk = luk;
        notifyPropertyChanged(BR.luk);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Bindable
    public String getDmgBounce() {
        return dmgBounce;
    }

    public void setDmgBounce(String dmgBounce) {
        this.dmgBounce = dmgBounce;
        notifyPropertyChanged(BR.dmgBounce);
    }

    public int getBaseStr() {
        return baseStr;
    }

    public void setBaseStr(int baseStr) {
        this.baseStr = baseStr;
    }

    public int getBaseCon() {
        return baseCon;
    }

    public void setBaseCon(int baseCon) {
        this.baseCon = baseCon;
    }

    public int getBaseSize() {
        return baseSize;
    }

    public void setBaseSize(int baseSize) {
        this.baseSize = baseSize;
    }

    public int getBaseDex() {
        return baseDex;
    }

    public void setBaseDex(int baseDex) {
        this.baseDex = baseDex;
    }

    public int getBaseApp() {
        return baseApp;
    }

    public void setBaseApp(int baseApp) {
        this.baseApp = baseApp;
    }

    public int getBaseInti() {
        return baseInti;
    }

    public void setBaseInti(int baseInti) {
        this.baseInti = baseInti;
    }

    public int getBasePow() {
        return basePow;
    }

    public void setBasePow(int basePow) {
        this.basePow = basePow;
    }

    public int getMaxSan() {
        return maxSan;
    }

    public void setMaxSan(int maxSan) {
        this.maxSan = maxSan;
    }

    public int getBaseEdu() {
        return baseEdu;
    }

    public void setBaseEdu(int baseEdu) {
        this.baseEdu = baseEdu;
    }

    public int getBaseLuk() {
        return baseLuk;
    }

    public void setBaseLuk(int baseLuk) {
        this.baseLuk = baseLuk;
    }

    public int getIdea() {
        return idea;
    }

    public void setIdea(int idea) {
        this.idea = idea;
    }

    public int getKnow() {
        return know;
    }

    public void setKnow(int know) {
        this.know = know;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getJobSkillPoint() {
        return jobSkillPoint;
    }

    public int getIntSkillPoint() {
        return intSkillPoint;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setJobSkillPoint(int jobSkillPoint) {
        this.jobSkillPoint = jobSkillPoint;
    }

    public void setIntSkillPoint(int intSkillPoint) {
        this.intSkillPoint = intSkillPoint;
    }
}
