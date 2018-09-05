package com.example.bugbusters.wifimapper.listeners;

import android.util.Log;

import com.example.bugbusters.wifimapper.MapsActivity;
import com.google.android.gms.maps.GoogleMap;

public class ZoomListener implements GoogleMap.OnCameraMoveListener {
    @Override
    public void onCameraMove()
    {
        if (MapsActivity.mMap.getCameraPosition().zoom > 18) {
            if (!MapsActivity.viewingMarkers) {
                Log.i("CameraChange", "rendering markers.");
                MapsActivity.renderMarkers();
            }
        }
        if (MapsActivity.mMap.getCameraPosition().zoom <= 18) {
            if (MapsActivity.viewingMarkers) {
                Log.i("CameraChange", "rendering polygons.");
                MapsActivity.renderPolygons();
            }
        }
    }
}
