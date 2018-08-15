package com.example.clayza.capstone;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class DatabaseUtils {
    DatabaseReference databaseSignal = FirebaseDatabase.getInstance().getReference().child("location");
    GoogleApiClient mGoogleApiClient;

    private void addSignal() {
        double lon =0;
        double lat =0;

        double strengthValue =0;
        Date  date = new Date();


        //store the values on firebase
        String id =databaseSignal.push().getKey();//creates a unique string ID


        LocationCapstone lc = new LocationCapstone(lat,lon,date.getTime(),strengthValue);
        databaseSignal.child(id).setValue(lc);

    }




}
