package com.example.bugbusters.wifimapper;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class Signal {
    private int  strength;
    private int throughPut;

    public Signal(int strength, int tp) {
        this.strength = strength;
        this.throughPut = tp;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setTime(int tp) {
        this.throughPut = tp;
    }

    public int getThroughPut() {
        return throughPut;
    }

    @Override
    public String toString() {
        return "Signal{" +
                ", strength=" + getStrength() +
                ", time=" + getThroughPut() +
                '}';
    }
}
