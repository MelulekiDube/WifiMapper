package com.example.bugbusters.wifimapper;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class Signal {
    private String signalId;
    private String location;
    private double  strength;
    private long time;

    public Signal(){
//needed for Firebase
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
@Exclude
    public String getSignalId() {
        return signalId;
    }

    public void setSignalId(String signalId) {
        this.signalId = signalId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "signalId='" + signalId + '\'' +
                ", location='" + location + '\'' +
                ", strength=" + strength +
                ", time=" + time +
                '}';
    }
}
