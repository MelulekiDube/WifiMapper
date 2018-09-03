package com.example.bugbusters.wifimapper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.bugbusters.wifimapper.listeners.Values;
import com.google.maps.android.PolyUtil;
//import com.google.maps.android.PolyUtil;
//import com.google.maps.android.PolyUtil;

import java.util.List;

public class SendData implements Runnable {

    private Location location;
    private final static int NUMBER_OF_LEVELS = 100;
    private WifiInfo wifiInfo;
    private static final String NETWORK_ID = "eduroam";

    @SuppressLint("MissingPermission")
    SendData(Context c, Location l) {
        Log.i("Send Data", l.getLatitude() + " " + l.getLongitude());
        location = l;
        WifiManager wifiManager = (WifiManager) c.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        assert wifiManager != null;
        wifiInfo = wifiManager.getConnectionInfo();
    }

    /**
     * This method is responsible for the getting the Wi-Fi strenght of the current network
     *
     * @return returns an integer representing the speed of the wifi.
     */
    private int getWifiStrength() {
        return WifiManager.calculateSignalLevel(wifiInfo.getRssi(), NUMBER_OF_LEVELS);
    }

    /**
     * Method to get the speed of the wifi that the user is connected to
     *
     * @return the Wi-Fi speed.
     */
    private int getWifiSpeed() {
        return wifiInfo.getLinkSpeed();
    }

    private static String getLocationAreaId(LocationCapstone location) {
        List<Area> areaList = Orchastrator.areas;
        Area locationArea = null;
        for (int i = 0; i < areaList.size(); i++) {
            Area area = areaList.get(i);
            if (PolyUtil.containsLocation(location.getLatLng(), area.getGoogleCoordinates(), false)) {
                locationArea = area;
                break;
            }
        }
        return (locationArea != null) ? locationArea.getId() : null;
    }

    /**
     * Methid to return the name of the wifi the user is connected to
     *
     * @return The wi-fi name that the user is connected to.
     */
    private String getWifiName() {
        return wifiInfo.getSSID().replace("\"", "");
    }

    /**
     * Method will build a new LocationCapStone object using the location object that is passed into the constructor
     *
     * @return the LocationObject that is goign to be built
     */
    private LocationCapstone buildLocationCapstone() {
        LocationCapstone locationCapstone = new LocationCapstone(location.getLatitude(), location.getLongitude(), location.getTime(), getWifiStrength());
//        locationCapstone.setAreaId(getLocationAreaId(locationCapstone));
        return locationCapstone;
    }

    @Override
    public void run() {
        Log.i("SendTest", "SendData Thread Run() is called");
        if (true) {//getWifiName().toLowerCase().equals(NETWORK_ID)) {
            while (!DatabaseUtils.loadedArea) ;
            LocationCapstone sentLocation = buildLocationCapstone();
            DatabaseUtils.addSignal(sentLocation);
            String areaToUpdate = getLocationAreaId(sentLocation);
            areaToUpdate = "-LLMPHfAnjSuNoI7NbBu";
            if (areaToUpdate == null) {
                Log.e(Values.TAG, "id is not  of a valid area");
                return;
            }
            Log.i("SendTest", "Before Update is called");
            DatabaseUtils.updateArea(areaToUpdate, getWifiStrength());
            Log.i(SendData.class.getName(), "Data return to db");
        } else
            Log.i(SendData.class.getName(), "Not on eduroam");
    }
}
