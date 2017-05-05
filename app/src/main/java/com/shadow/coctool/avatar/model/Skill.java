package com.shadow.coctool.avatar.model;

import java.io.Serializable;

/**
 * Created by lxf on 2017/4/26.
 */

public class Skill implements Serializable, Cloneable {

    private String name;

    private int min;

    private int point;

    public Skill() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getSkillLevel() {
       return min + point;
    }

    @Override
    public Skill clone() throws CloneNotSupportedException {
        Skill skill = (Skill) super.clone();
        return skill ;
    }
}
