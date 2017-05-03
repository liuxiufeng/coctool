package com.shadow.coctool.avatar.model;

import java.util.List;
import java.util.Map;

/**
 * Created by lxf on 2017/4/26.
 */

public class Job {
    private String name;

    private String memo;

    private List<Skill> skillList;

    private Map<String, Skill> skillMap;

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
}
