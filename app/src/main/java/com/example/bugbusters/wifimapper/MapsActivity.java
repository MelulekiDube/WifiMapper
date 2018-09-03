package com.example.bugbusters.wifimapper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.bugbusters.wifimapper.listeners.AreaDatabase;
import com.example.bugbusters.wifimapper.listeners.LocationTracker;
import com.example.bugbusters.wifimapper.listeners.ZoomListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    public static GoogleMap mMap;
    private boolean granted = false;
    public static boolean viewingMarkers = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        updateLocationManager();
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    /**
     * This method should update the strength rendered to are a with the new strength avg_strength.
     *
     * @param a            the area/segment to be updated
     * @param avg_strength the new strength for area a
     */
    public static void updateArea(Area a, double avg_strength) {
    }

    /**
     * This method should update the strength rendered to are a with the new strength avg_strength.
     *
     * @param updatedArea the area/segment to be updated
     */
    public static void updateArea(Area updatedArea) {
        Polygon polygon = Orchastrator.areaPolygonMappings.get(updatedArea.getId());
//        Log.i("areaStrength",updatedArea.getId()+" "+updatedArea.getWifiStrength());
        polygon.setFillColor(ColorScheme.evaluateColor(updatedArea.getWifiStrength()));
    }

    private void updateLocationManager() {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationTracker(mMap, this);
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            int REQUEST_LOCATION_PERMISION = 0;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISION);
            if (granted) {
                assert locationManager != null;
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, locationListener);
            }
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        } else {
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, locationListener);
        }
    }

    public static void renderMarkers() {
        mMap.clear();
        if (Orchastrator.LOCATION_LIST == null) throw new NullPointerException();
        for (LocationCapstone l : Orchastrator.LOCATION_LIST) {
            mMap.addMarker(new MarkerOptions().position(l.getLatLng()));
        }
        viewingMarkers = true;
    }

    public static void renderPolygons() {
        mMap.clear();
        Orchastrator.areaPolygonMappings.clear();
        for (Area area : Orchastrator.areas) {
            AreaDatabase.createPolygon(area);
        }
        viewingMarkers = false;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraMoveListener(new ZoomListener());
        LatLng Jameson = new LatLng(-33.957669, 18.461038);
        mMap.setMinZoomPreference(Values.MIN_ZOOM_LEVEL);
        mMap.setMaxZoomPreference(Values.MAX_ZOOM_LVEL);
        updateLocationManager();
        //Orchastrator.getDataFromDatabase(mMap);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Jameson));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        Orchastrator.setUpDB();
    }

    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        int REQUEST_LOCATION_PERMISION = 0;
        if (requestCode == REQUEST_LOCATION_PERMISION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                granted = true;
            } else {
                // Permission was denied or request was cancelled
//                Toast.makeText(getApplicationContext(), R.string.DeniedPermisions, Toast.LENGTH_LONG).show();
            }
        }
    }
}
