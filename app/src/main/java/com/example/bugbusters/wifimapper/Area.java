package com.example.bugbusters.wifimapper;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class Area {

    private PolygonOptions segment;
    private int wifiStrength;
    private int numberUpdaters;

    Area(ArrayList<LatLng> coordinates) {
        for (int i = 0; i < coordinates.size(); i++) {
            segment.add(coordinates.get(i));
        }
    }

    void setwifiStrength(int wifiStrength) {

        this.wifiStrength = wifiStrength;
        setColor(wifiStrength);
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
