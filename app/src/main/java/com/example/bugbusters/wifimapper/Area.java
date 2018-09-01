package com.example.bugbusters.wifimapper;


import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

public class Area {

    protected String id;
    // private PolygonOptions segment;
    private ArrayList<LatLng> coordinates;
    private String name;


    /*This is needed by firebase dont remove
     * */
    public Area() {
    }

    public Area(String id, List<LatLng> coordinates, String name) {
        //this.segment=segment;
        this.name = name;
        this.coordinates = new ArrayList<>(coordinates);
        this.id = id;
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

