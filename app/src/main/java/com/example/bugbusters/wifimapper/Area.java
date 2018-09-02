package com.example.bugbusters.wifimapper;


import com.google.android.gms.maps.model.PolygonOptions;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

public class Area {

    protected String id;
    private ArrayList<LatLng> coordinates;
    private String name;
    private int wifiStrength;
    private int numLocation;



    /*This is needed by firebase dont remove
     * */
    public Area() {
    }

    public Area(String id, List<LatLng> coordinates, String name,int wifiStrength,int numLocation) {
        //this.segment=segment;
        this.name = name;
        this.coordinates = new ArrayList<>(coordinates);
        this.id = id;
        this.wifiStrength=wifiStrength;
        this.numLocation=numLocation;
    }

    public String getId() {
        return id;
    }

    //Beggining of setters and getters
    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<LatLng> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<LatLng> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<com.google.android.gms.maps.model.LatLng> getGoogleCoordinates() {
        ArrayList<com.google.android.gms.maps.model.LatLng> resultList = new ArrayList<>();

        for (LatLng item : coordinates) {
            resultList.add(item.toGoogleLatLng());
        }

        return resultList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWifiStrength() {
        return wifiStrength;
    }

    public void setWifiStrength(int wifiStrength) {
        this.wifiStrength = wifiStrength;
    }

    public int getNumLocation() {
        return numLocation;
    }

    public void setNumLocation(int numLocation) {
        this.numLocation = numLocation;
    }

    @Exclude
    public Area updateArea(int wifiStrength)
    {
        long cummulativeStrength=(this.numLocation*this.wifiStrength)+wifiStrength;
        numLocation++;
        return new Area(this.id,this.coordinates,this.name,(int)cummulativeStrength/(numLocation),numLocation);

    }

}

