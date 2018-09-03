package com.example.bugbusters.wifimapper;

import android.content.Context;
import android.location.Location;
import android.util.ArrayMap;
import android.util.Log;

import com.google.android.gms.maps.model.Polygon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orchastrator {

    public final static Map<Area, Double> areaStrengthMappings = new ArrayMap<>();
    public static List<Area> areas = null;
    public static Map<String, Polygon> areaPolygonMappings = new HashMap<>();
    public static List<LocationCapstone> LOCATION_LIST = null;
    public static void setUpDB() {
        DatabaseUtils.setListeners();
    }

    /**
     * This method is going to be responsible to spawn the thread that will be sending the data to the database this way the main thread can continue doing what its doing
     *
     * @param c the application context
     * @param l the location
     */
    public static void sendData(Context c, Location l) {
        Log.i("Orchastrator", "Sending Data");
        Thread thread = new Thread(new RecordProcessor(c, l));
        thread.start();
    }

    /**
     * @param list
     */
    public static void setAreaList(List<Area> list) {
        areas = list;
    }

    /**
     * This method will look for the location
     *
     * @param newObject the new object added to the database
     */
    public static void updateSegmentWithObject(LocationCapstone newObject) {
        for (Area a : areas) {
            if (a.id.equals(newObject.getAreaId())) {
                double avg_strength = areaStrengthMappings.get(a);
                avg_strength = (avg_strength + newObject.getStrength()) / 2;
                MapsActivity.updateArea(a, avg_strength);
            }
        }
    }

    /**
     * This method maps each area object to its strength
     *
     * @param locationList list of locations read from the database
     */
    public static void setLocationList(List<LocationCapstone> locationList) {
        LOCATION_LIST = locationList;
    }

    /**
     * Given a locationcapstone project this method returns the area that this object belongs to
     *
     * @param locationCapstone the object we want to get which area it belongs to
     * @return are to which the location object belongs to
     */
    private static Area getArea(LocationCapstone locationCapstone) {
        for (Area a : areas) {
            if (a.id.equals(locationCapstone.getAreaId())) {
                return a;
            }
        }
        return null;
    }

    /**
     * This methods inserts appropriately to the list
     *
     * @param a  area to insert to
     * @param ll location that will update the area object
     */

    private static void insert(Area a, LocationCapstone ll) {
        if (a != null) {
            Double strength = (areaStrengthMappings.containsKey(a)) ? areaStrengthMappings.get(a) : 0;
            strength = (strength + ll.getStrength()) / 2;
            areaStrengthMappings.put(a, strength);
        }
    }



}
