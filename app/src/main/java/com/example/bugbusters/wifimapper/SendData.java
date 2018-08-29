package com.example.bugbusters.wifimapper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

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

    /**
     * Methid to return the name of the wifi the user is connected to
     *
     * @return The wi-fi name that the user is connected to.
     */
    private String getWifiName() {
        return wifiInfo.getSSID().replace("\"","");
    }

    /**
     * Method will build a new LocationCapStone object using the location object that is passed into the constructor
     *
     * @return the LocationObject that is goign to be built
     */
    private LocationCapstone buildLocationCapstone() {
        return new LocationCapstone(location.getLatitude(), location.getLongitude(), location.getTime(), getWifiStrength());
    }




    @Override
    public void run() {
        if (getWifiName().toLowerCase().equals(NETWORK_ID)) {
            DatabaseUtils.addSignal(buildLocationCapstone());
            Log.i("Send_data", "Data return to db");
        } else
            Log.i("SEND_DATA", "Not on eduroam");
    }
}
