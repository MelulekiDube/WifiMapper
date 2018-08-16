package com.example.bugbusters.wifimapper;

//import android.util.Log;

import com.example.bugbusters.wifimapper.LocationCapstone;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseUtils {
    static DatabaseReference databaseSignal = FirebaseDatabase.getInstance().getReference().child("location");


    protected void addSignal(double lon, double lat,double strengthValue) {


            Date  date = new Date();


            //store the values on firebase
            String id =databaseSignal.push().getKey();//creates a unique string ID


            LocationCapstone lc = new LocationCapstone(lat,lon,date.getTime(),strengthValue);
            databaseSignal.child(id).setValue(lc);

    }

    public List<LocationCapstone> readDatabase(){

        final List<LocationCapstone> signalList = new ArrayList<>();

        // Read from the database
        databaseSignal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    LocationCapstone location =snapshot.getValue(LocationCapstone.class);
                    signalList.add(location);
                    //  Log.d("TAG", "Value is: " + location);
                }

            }

            @Override
            public void onCancelled (DatabaseError error){
                // Failed to read value
                //Log.w("TAG", "Failed to read value.", error.toException());
                System.out.println(error.toException());
            }
        });
  return signalList;  }



}
