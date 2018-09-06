package com.example.bugbusters.wifimapper;

//import android.util.Log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bugbusters.wifimapper.listeners.AreaDatabase;
import com.example.bugbusters.wifimapper.listeners.LocationDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;


import java.util.ArrayList;

public class DatabaseUtils {
    private static DatabaseReference databaseSignal = FirebaseDatabase.getInstance().getReference().child("location");
    private static DatabaseReference databaseArea = FirebaseDatabase.getInstance().getReference().child("areas");

    private final static String TAG = "DATABASE_UTILS";
    private final static String ERROR_MESSAGE = "Error in reading the values";
    public static boolean loadedArea = false;

    public static void setListeners() {
        LocationDatabase locationDatabase = new LocationDatabase();
        AreaDatabase areaDatabase = new AreaDatabase();
        databaseSignal.addListenerForSingleValueEvent(locationDatabase);
        databaseSignal.addChildEventListener(locationDatabase);

        databaseArea.addListenerForSingleValueEvent(areaDatabase);
        databaseArea.addChildEventListener(areaDatabase);
    }

    public static void addSignal(LocationCapstone lc) {
        //store the values on firebase
        String id = databaseSignal.push().getKey();//creates a unique string ID
        assert id != null;
        databaseSignal.child(id).setValue(lc);// this will be replaced with reading ifnormation from the database
    }

    //This method populates the database with areas on the map
    public static void addArea(ArrayList<LatLng> coordinates, String name) {
        //store the values on firebase
        Log.d(TAG, "AreaAdd() test ");
        String areaId = databaseArea.push().getKey();//creates a unique string ID
        Log.d(TAG, areaId);
        assert areaId != null;
        Area mArea = new Area(areaId, coordinates, name,0,0);
        databaseArea.child(areaId).setValue(mArea);

    }

    public static void updateAreaOnDatabase(String id, final int wifiStrength) {
        Log.i("SendTest", "inside updateAreaOnDatabase()");
        DatabaseReference areaRef=databaseArea.child(id);
        areaRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Area area=mutableData.getValue(Area.class);
                assert area != null;//check if area is not null
                area.updateArea(wifiStrength);
                mutableData.setValue(area);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                    Log.i("UPDATE","Data is successfully Updated");
            }
        });

    }
}
