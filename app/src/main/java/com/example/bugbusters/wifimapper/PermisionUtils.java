package com.example.bugbusters.wifimapper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public final class PermisionUtils implements ActivityCompat.OnRequestPermissionsResultCallback{
    private static  String activityName;
    private static Activity activity;
    private static final int REQUEST_LOCATION_PERMISION = 0;
    private static View layout;

    public  static void requestLocationPermision(Activity act, String ac){
        layout = (View) act.findViewById(R.id.map);
        activityName = ac;
        activity =act;
        if (ActivityCompat.checkSelfPermission(act.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(act,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE},
                    REQUEST_LOCATION_PERMISION);
        } else {
            // permission has been granted, continue as usual
            Snackbar.make(activity.findViewById(R.id.map), R.string.permision_location_available, Snackbar.LENGTH_LONG);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISION) {
            if(grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We can now safely use the API we requested access to
                Snackbar.make(activity.findViewById(R.id.map), R.string.permision_location_available, Snackbar.LENGTH_LONG);
            } else {
                // Permission was denied or request was cancelled
                Snackbar.make(activity.findViewById(R.id.map), R.string.location_permision_unavailable, Snackbar.LENGTH_LONG);
            }
        }
    }
}
