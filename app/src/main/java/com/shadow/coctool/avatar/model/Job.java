package com.shadow.coctool.avatar.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxf on 2017/4/26.
 */

public class Job implements Serializable {
    private String name;

    private String memo;

    private List<String> skillList;

    private List<String> freeSkillList;

    public Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<String> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<String> skillList) {
        this.skillList = skillList;
    }

    public List<String> getFreeSkillList() {
        return freeSkillList;
    }

    public void setFreeSkillList(List<String> freeSkillList) {
        this.freeSkillList = freeSkillList;
    }
}
