package com.example.bugbusters.wifimapper;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class Area {

    private PolygonOptions segment;
    private double wifiStrength;
    private int numberOfLocations;
    private ArrayList<LatLng> coordinates;


/*This is needed by firebase dont remove
* */
    public Area() {
    }

    public Area(PolygonOptions segment, double wifiStrength, int numberOfLocations, ArrayList<LatLng> coordinates) {
        this.segment=segment;
        this.wifiStrength=wifiStrength;
        this.numberOfLocations =numberOfLocations;
        this.coordinates = new ArrayList<>(coordinates);

//        for (int i = 0; i < coordinates.size(); i++) {
//            segment.add(coordinates.get(i));
//        }
    }

    //Beggining of setters and getters
    void setwifiStrength(int wifiStrength) {

        this.wifiStrength = wifiStrength;
        setColor(wifiStrength);
    }

    public void setSegment(PolygonOptions segment) {
        this.segment = segment;
    }

    public void setWifiStrength(double wifiStrength) {
        this.wifiStrength = wifiStrength;
    }

    public void setNumberOfLocations(int numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public void setCoordinates(ArrayList<LatLng> coordinates) {
        this.coordinates = coordinates;
    }

    public PolygonOptions getSegment() {
        return segment;
    }

    public double getWifiStrength() {
        return wifiStrength;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public ArrayList<LatLng> getCoordinates() {
        return coordinates;
    }



    void setColor(int wifiStrength) {
        int color;
        if(wifiStrength < 30 ){
            color = ColorScheme.RED;
        }else if(wifiStrength  < 50){
            color = ColorScheme.ORANGE;
        }else if(wifiStrength < 60){
            color = ColorScheme.YELLOW;
        }else if(wifiStrength < 80){
            color = ColorScheme.GREEN_LIGHT;
        }else{
            color = ColorScheme.GREEN;
        }
        segment.fillColor(color);
    }

}

