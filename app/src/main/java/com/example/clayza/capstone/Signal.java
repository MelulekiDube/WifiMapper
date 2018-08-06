package com.example.clayza.capstone;

import java.util.Date;

public class Signal {
    private String signalId;
    private String location;
    private double  strength;
    private long time;

    Signal(){

    }

    public Signal(String signalId,String location, double strength, long time) {
        this.signalId = signalId;
        this.location = location;
        this.strength = strength;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public double getStrength() {
        return strength;
    }

    public long getTime() {
        return time;
    }
}
