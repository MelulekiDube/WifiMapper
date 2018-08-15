package com.example.clayza.capstone;

import com.google.firebase.database.Exclude;

public class LocationCapstone {

    private double lat;
    private double lon;
    private long time;
    private double strength;
    private String signalId;


    public LocationCapstone() {
    }

    public LocationCapstone(double lat, double lon, long time, double strength) {
        this.lat = lat;
        this.lon = lon;
        this.time = time;
        this.strength = strength;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Exclude
    public String getSignalId() {
        return signalId;
    }

    public void setSignalId(String signalId) {
        this.signalId = signalId;
    }

    @Override
    public String toString() {
        return "LocationCapstone{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", time=" + time +
                ", strength=" + strength +
                '}';
    }
}
