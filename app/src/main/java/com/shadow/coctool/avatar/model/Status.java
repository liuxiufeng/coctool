package com.shadow.coctool.avatar.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lxf on 2017/5/3.
 */

public class Status implements Serializable {

    Map<String, Integer> baseStatus;

    Map<String, Map<String, Integer>> modifiers;

    public Status() {
        baseStatus = new HashMap<>();
        modifiers = new HashMap<>();
    }

    public Status(Map _baseStatus) {
        baseStatus = _baseStatus;
        modifiers = new HashMap<>();
    }

    public void set(String id, int value) {
        baseStatus.put(id, value);
    }

    public int get(String id) {
        int total = baseStatus.get(id) == null ? 0 : baseStatus.get(id);

        Set keySet = modifiers.keySet();
        for (Object key : keySet) {
            Map modifier = modifiers.get(key);
            if (modifier.containsKey(id)) {
                total += (int) modifier.get(id);
            }
        }

        return total;
    }

    public int getBase(String id) {
        return baseStatus.get(id);
    }

    public void addModifier(String id, Map modifier) {
        modifiers.put(id, modifier);
    }

    public void removeModifier(String id) {
        modifiers.remove(id);
    }

    public Map getModifier(String id) {
        return modifiers.get(id);
    }

    public Map getBaseStatus() {
        return baseStatus;
    }
}
