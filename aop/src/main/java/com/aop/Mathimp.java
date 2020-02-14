package com.aop;

public class Mathimp implements Math
{
    public int add(int i, int j) {
        return i+j;
    }

    public int jian(int i, int j) {
        return i-j;
    }

    public int cheng(int i, int j) {
        return i*j;
    }

    public int chu(int i, int j) {
        return i / j;
    }
}
