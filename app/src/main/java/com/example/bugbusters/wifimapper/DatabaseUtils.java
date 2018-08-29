package com.example.bugbusters.wifimapper;

//import android.util.Log;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DatabaseUtils {
    static DatabaseReference databaseSignal = FirebaseDatabase.getInstance().getReference().child("location");
    static DatabaseReference databaseArea = FirebaseDatabase.getInstance().getReference().child("area");
    private final static String TAG = "DATABASE_UTILS";
    private final static String ERROR_MESSAGE = "Error in reading the values";

    public static void addSignal(LocationCapstone lc) {
        //store the values on firebase
        String id = databaseSignal.push().getKey();//creates a unique string ID
        assert id != null;
        databaseSignal.child(id).setValue(lc);
    }


//This method saves area to the database
public static void addArea(Area area) {
    //store the values on firebase
    String areaId = databaseArea.push().getKey();//creates a unique string ID

    assert areaId != null;
    databaseArea.child(areaId).setValue(area);

}


    public static void readDatabase(final GoogleMap map) {
        final List<LocationCapstone> locationList = new ArrayList<>();
        databaseSignal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LocationCapstone location = snapshot.getValue(LocationCapstone.class);
                    locationList.add(location);
                }
                Orchastrator.updateMapWithDataPoints(locationList, map);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.e(TAG, ERROR_MESSAGE, error.toException());
            }
        });

    }

}
