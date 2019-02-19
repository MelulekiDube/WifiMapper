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
import android.widget.Toast;

import com.example.bugbusters.wifimapper.listeners.AreaDatabase;
import com.example.bugbusters.wifimapper.listeners.LocationTracker;
import com.example.bugbusters.wifimapper.listeners.ZoomListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    public static GoogleMap mMap;
    private boolean granted = false;
    public static boolean viewingMarkers = false;
    private static ClusterManager<LocationRecord> clusterManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);


    }

    private void setUpClusterer() {

        // Initialize the Cluster Manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = new ClusterManager<>(this, mMap);
        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);
        clusterManager.setRenderer(new CustomClusterRenderer(this, mMap, clusterManager));
    }

    /**
     * This method should update the strength rendered to area with the new strength avg_strength.
     *
     * @param updatedArea the area/segment to be updated
     */
    public static void updateArea(Area updatedArea) {

        Polygon polygon = Orchastrator.areaPolygonMappings.get(updatedArea.getId());
        Orchastrator.polygonAreaMappings.put(polygon,updatedArea);
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
        } else {
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, locationListener);
        }
    }

    /**
     * Renders individual points on the map
     */

    public static void renderMarkers() {
        mMap.clear();
        if (Orchastrator.LOCATION_LIST == null) throw new NullPointerException();
        for (LocationRecord l : Orchastrator.LOCATION_LIST) {
//            mMap.addMarker(new MarkerOptions().position(l.getLatLng()));
                clusterManager.addItem(l);
        }
        viewingMarkers = true;
    }

    public static void renderPolygons() {
        mMap.clear();
        clusterManager.clearItems();
        Orchastrator.areaPolygonMappings.clear();
        Orchastrator.polygonAreaMappings.clear();
        for (Area area : Orchastrator.areas) {
            AreaDatabase.createPolygon(area);
        }
        viewingMarkers = false;
    }

    /**
     *
     * @param googleMap Map that is rendered on screen
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraMoveListener(new ZoomListener());
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                mMap.animateCamera(CameraUpdateFactory.newLatLng(getCenter(polygon.getPoints())));
                Area area=Orchastrator.polygonAreaMappings.get(polygon);
                Toast.makeText(getApplicationContext(),area.getName()+"\nStrength: "+area.getWifiStrength(), Toast.LENGTH_SHORT).show();
            }
        });
        LatLng Jameson = new LatLng(-33.957669, 18.461038);
        mMap.setMinZoomPreference(Values.MIN_ZOOM_LEVEL);
        mMap.setMaxZoomPreference(Values.MAX_ZOOM_LVEL);
        updateLocationManager();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Jameson));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        Orchastrator.setUpDB();
        setUpClusterer();


        //uncomment to write to the database/firebase, this method should be ran once to populate the database with polygons
        DBPopulator.addSegments(mMap);

    }

    public static LatLng getCenter(List<LatLng> coordinates)
    {
         double lat=0;
         double lng=0;
         for(LatLng location:coordinates)
         {
             lat+=location.latitude;
             lng+=location.longitude;
         }
         return new LatLng(lat/coordinates.size(),lng/coordinates.size());
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
                 //Permission was denied or request was cancelled
                Toast.makeText(getApplicationContext(), R.string.DeniedPermisions, Toast.LENGTH_LONG).show();
            }
        }
    }
}
