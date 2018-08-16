package com.example.bugbusters.wifimapper;

import android.location.Location;

public class Record {
    private Location location;
    private Signal signal;
    private long time;


    public Record() {
    }

    Record(Location l, Signal s) {
        location = l;
        this.time = l.getTime();
        this.signal = s;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal strength) {
        this.signal = strength;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "LocationCapstone{" +
                ", time=" + time +
                ", strength=" + signal +
                '}';
    }
}
