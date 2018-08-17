package com.example.bugbusters.wifimapper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Orchastrator {

    /**
     * This method intiializes the reading of data from the database together with the listners for data changes in the database.
     *
     * @param map the map object which we will then populate with data points after reading the data from it.
     */
    public static void getDataFromDatabase(Context c, GoogleMap map) {
        DatabaseUtils.readDatabase(c, map);
    }

    /**
     * This method is going to be responsible to spawn the thread that will be sending the data to the database this way the main thread can continue doing what its doing
     *
     * @param c the application context
     * @param l the location
     */
    public static void sendData(Context c, Location l) {
        Log.i("Orchastrator", "Sending Data");
        Thread thread = new Thread(new SendData(c, l));
        thread.start();
    }

    /**
     * This method will updated the method in the map activity with the read dat and any new data that is found by the method.
     *
     * @param list this is the list of data points that are read from the database.
     * @param map  this is the map object where we will be writing the markers to.
     */
    public static void updateMapWithDataPoints(Context c, List<LocationCapstone> list, GoogleMap map) {
        for (LocationCapstone location : list) {
            LatLng loc = new LatLng(location.getLat(), location.getLon());
            float hue = (float) location.getStrength() / 100 * 120;
            map.addMarker(new MarkerOptions().position(loc).icon(BitmapDescriptorFactory.defaultMarker(hue)).title(getAddress(c, loc.latitude, loc.longitude)));
        }
    }

    private static String getAddress(Context c, double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(c, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                result.append(address.getLocality()).append("\n");
                result.append(address.getCountryName());
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }
}
