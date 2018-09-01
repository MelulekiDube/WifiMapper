package com.example.bugbusters.wifimapper;


import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class Area {

   // private PolygonOptions segment;
    private double wifiStrength;
    private int numberOfLocations;
    private ArrayList<LatLng> coordinates;
    private String name;
    protected  String id;


/*This is needed by firebase dont remove
* */
    public Area() {
    }

    public Area( String id,double wifiStrength, int numberOfLocations, ArrayList<LatLng> coordinates,String name) {
        //this.segment=segment;
        this.name=name;
        this.wifiStrength=wifiStrength;
        this.numberOfLocations =numberOfLocations;
        this.coordinates = new ArrayList<>(coordinates);
        this.id=id;
    }

    //Beggining of setters and getters


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
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


    public double getWifiStrength() {
        return wifiStrength;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public ArrayList<LatLng> getCoordinates() {
        return coordinates;
    }


    public ArrayList<com.google.android.gms.maps.model.LatLng> getGoogleCoordinates() {
      ArrayList<com.google.android.gms.maps.model.LatLng> resultList = new ArrayList<>();

       for(LatLng item:coordinates){
           resultList.add(item.toGoogleLatLng());
       }

        return resultList;
    }


    public void updateAreaInfo(double strength)
    {
        double CummulativeWifiStrength=(numberOfLocations*wifiStrength)+strength;
        numberOfLocations++;
        this.wifiStrength=CummulativeWifiStrength/numberOfLocations;
    }





    public String getName() {
        return name;
    }
//Ending of setters and getters
//    void setColor(int wifiStrength) {
//        int color;
//        if(wifiStrength < 30 ){
//            color = ColorScheme.RED;
//        }else if(wifiStrength  < 50){
//            color = ColorScheme.ORANGE;
//        }else if(wifiStrength < 60){
//            color = ColorScheme.YELLOW;
//        }else if(wifiStrength < 80){
//            color = ColorScheme.GREEN_LIGHT;
//        }else{
//            color = ColorScheme.GREEN;
//        }
//        //segment.fillColor(color);
//    }

}

