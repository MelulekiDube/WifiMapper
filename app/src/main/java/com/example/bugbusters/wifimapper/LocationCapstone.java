package com.example.bugbusters.wifimapper;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;
import com.google.maps.android.clustering.ClusterItem;

public class LocationCapstone implements ClusterItem {

    private double lat;
    private double lon;
    private long time;
    private double strength;
    private String areaId;

    /*
     * */
    public LocationCapstone() {
    }

    LocationCapstone(double lat, double lon, long time, double strength) {
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Exclude
    public LatLng getLatLng() {
        return new LatLng(lat, lon);
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

    @Override
    @Exclude
    public LatLng getPosition() {
        return getLatLng();
    }

    @Override
    @Exclude
    public String getTitle() {
        return null;
    }

    @Override
    @Exclude
    public String getSnippet() {
        return null;
    }
}
