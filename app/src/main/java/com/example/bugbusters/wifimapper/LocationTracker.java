package com.example.bugbusters.wifimapper;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class LocationTracker implements LocationListener{

    @Override
    public void onLocationChanged(Location location) {
        MapsActivity.updateLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
