package com.example.bugbusters.wifimapper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    public GoogleMap mMap;
    private boolean granted = false;

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
            if(granted) {
                assert locationManager != null;
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, locationListener);
            }
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }else{
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, locationListener);
        }
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
        LatLng Jameson = new LatLng(-33.957669, 18.461038);
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(20.0f);
        updateLocationManager();
        //Orchastrator.getDataFromDatabase(mMap);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Jameson));
        addSegments();
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    void addSegments()
    {
        Random rand=new Random();
        Polygon Pre_PD = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.954874, 18.460117),new LatLng(-33.954874, 18.460732),
                        new LatLng(-33.955385, 18.460748), new LatLng(-33.955384, 18.460117))
                .fillColor(Color.argb(100,0,0,100))
                .strokeWidth(0)
        );

        Polygon PD = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.955384, 18.460117),new LatLng(-33.955385, 18.460748),
                        new LatLng(-33.956498, 18.460750), new LatLng(-33.956450,  18.459849))
                .fillColor(Color.argb(100,0,150,0))
                .strokeWidth(0)
        );

        Polygon Career_Service = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956450,  18.459849),new LatLng(-33.956475,  18.460444),
                        new LatLng(-33.956950, 18.460371), new LatLng(-33.956872, 18.459690))
                .fillColor(Color.argb(100,200,0,0))
                .strokeWidth(0)
        );

        Polygon Climate_sys_Group = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956872, 18.459690),new LatLng(-33.956950, 18.460371),
                        new LatLng(-33.958305, 18.459975), new LatLng(-33.958123, 18.459178))
                .fillColor(Color.argb(100,100,80,40))
                .strokeWidth(0)
        );

        Polygon NEB = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958123, 18.459178),new LatLng(-33.958305, 18.459975),
                        new LatLng(-33.959899, 18.459160),new LatLng(-33.959118, 18.458773))
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon NSLT = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.954874, 18.460732),new LatLng(-33.954990, 18.461412),
                        new LatLng(-33.955658, 18.461396),new LatLng(-33.955658, 18.460750))
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Chris_Hani = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.955658, 18.460750),new LatLng(-33.955658, 18.461396),
                        new LatLng(-33.955925, 18.461399),new LatLng(-33.955925, 18.460750))
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon FitzPatrick_Institute = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.955925, 18.460750),new LatLng(-33.955925, 18.461399),
                     new LatLng(-33.956520, 18.461399),new LatLng(-33.956498, 18.460750))
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon FoodCourt = mMap.addPolygon(new PolygonOptions()
            .add(new LatLng(-33.956475,  18.460444),
                    new LatLng(-33.956950, 18.460371),
                    new LatLng(-33.957077, 18.460340),
                    new LatLng(-33.957141, 18.460817),
                    new LatLng(-33.956506, 18.460900))
            .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
            .strokeWidth(0)
    );

        Polygon CompSci = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956506, 18.460900), new LatLng(-33.956520, 18.461399),
                        new LatLng(-33.957211, 18.461331), new LatLng(-33.957141, 18.460817)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Library = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957077, 18.460340), new LatLng(-33.957211, 18.461331),
                        new LatLng(-33.957500, 18.461281), new LatLng(-33.957365, 18.460250)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );
        Polygon Jameson = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957365, 18.460250), new LatLng(-33.957500, 18.461281),
                        new LatLng(-33.958330, 18.461081), new LatLng(-33.958121, 18.460031)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Menzies = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958121, 18.460031),
                        new LatLng(-33.958305, 18.459975),
                        new LatLng(-33.958922, 18.459650),
                        new LatLng(-33.959250, 18.460750),
                        new LatLng(-33.958330, 18.461081)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Leslie = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958922, 18.459650),
                        new LatLng(-33.959899, 18.459160),
                        new LatLng(-33.960763, 18.459929),
                        new LatLng(-33.960435, 18.460415),
                        new LatLng(-33.960266, 18.460231),
                        new LatLng(-33.959250, 18.460750)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Centlivres = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.960435, 18.460415),
                        new LatLng(-33.960266, 18.460231),
                        new LatLng(-33.959250, 18.460750),
                        new LatLng(-33.959360, 18.461190),
                        new LatLng(-33.959928, 18.460924),
                        new LatLng(-33.959982, 18.460955),
                        new LatLng(-33.960150, 18.460800)
//                        new LatLng(-33.960447, 18.460394)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Beattie = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958588, 18.460990),new LatLng(-33.958700, 18.461458),
                        new LatLng(-33.959360, 18.461190),new LatLng(-33.959250, 18.460750)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon AC_Jordan = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957740, 18.461225),new LatLng(-33.957809, 18.461715),
                        new LatLng(-33.958315, 18.461569),
                        new LatLng(-33.958700, 18.461458),
                        new LatLng(-33.958588, 18.460990),
                        new LatLng(-33.958330, 18.461081)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Math_Bldng = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957740, 18.461225),new LatLng(-33.957809, 18.461715),
                        new LatLng(-33.956825, 18.461858),
                        new LatLng(-33.956807, 18.461372),
                        new LatLng(-33.957500, 18.461281)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon Herbariuam_Library = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956825, 18.461858),
                        new LatLng(-33.956807, 18.461372),
                        new LatLng(-33.956525, 18.461398),
                        new LatLng(-33.955987, 18.461400),
                        new LatLng(-33.955973, 18.461884)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        Polygon RW_James = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956825, 18.461858),
                        new LatLng(-33.956807, 18.461372),
                        new LatLng(-33.956525, 18.461398),
                        new LatLng(-33.955987, 18.461400),
                        new LatLng(-33.955973, 18.461884)
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

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
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
