package com.example.bugbusters.wifimapper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.google.maps.android.PolyUtil;
//import com.google.maps.android.PolyUtil;
//import com.google.maps.android.PolyUtil;

import java.util.List;

public class RecordProcessor implements Runnable {

    private Location location;
    private final static int NUMBER_OF_LEVELS = 100;
    private WifiInfo wifiInfo;
    private static final String NETWORK_ID = "eduroam";

    @SuppressLint("MissingPermission")
    RecordProcessor(Context c, Location l) {
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
     * Given a locationcapstone project this method returns the area id that this object belongs to
     *
     * @param location the object we want to get which area it belongs to
     * @return area id to which the location object belongs to
     */
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
        return locationCapstone;
    }

    @Override
    public void run() {
        Log.i("SendTest", "RecordProcessor Thread Run() is called");
        if (getWifiName().toLowerCase().equals(NETWORK_ID)) {
            while (!DatabaseUtils.loadedArea);
            LocationCapstone sentLocation = buildLocationCapstone();
            String areaToUpdate = getLocationAreaId(sentLocation);
            if (areaToUpdate == null) {
                Log.e("AREATEST", "id is not  of a valid area");
                return;
            }
            Log.i("SendTest", "Before Update is called");
            DatabaseUtils.addSignal(sentLocation);
            DatabaseUtils.updateAreaOnDatabase(areaToUpdate, getWifiStrength());
            Log.i(RecordProcessor.class.getName(), "Data return to db");
        } else
            Log.i(RecordProcessor.class.getName(), "Not on eduroam");
    }
}
