package com.shadow.coctool.dice;

/**
 * Created by lxf on 2017/4/25.
 */

public class Dice {
    private int mFaceNumbers;

    public Dice(int faceNumbers) {
        mFaceNumbers = faceNumbers;
    }

    public int roll() {
        return (int) Math.floor(Math.random() * mFaceNumbers + 1);
    }

    public int roll(int num) {
        int rst = 0;
        for (int i = 0; i < num; i++) {
            rst += roll();
        }
        return rst;
    }
}
