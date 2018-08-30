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
    static DatabaseReference databaseArea = FirebaseDatabase.getInstance().getReference().child("areas");
    private final static String TAG = "DATABASE_UTILS";
    private final static String ERROR_MESSAGE = "Error in reading the values";

    public static void addSignal(LocationCapstone lc) {
        //store the values on firebase
        String id = databaseSignal.push().getKey();//creates a unique string ID
        assert id != null;
        databaseSignal.child(id).setValue(lc);
    }


//This method populates the database with areas on the map
public static void addArea(Area area) {
    //store the values on firebase
    String areaId = databaseArea.push().getKey();//creates a unique string ID

    assert areaId != null;
    databaseArea.child(areaId).setValue(area);

}

public static List<Area> getAreaList(){
    final List<Area> areaList = new ArrayList<>();

    databaseArea.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Area area = snapshot.getValue(Area.class);
                areaList.add(area);
            }
            Log.d("AreaTest",areaList.size()+"");
            Orchastrator.renderSegements(areaList,MapsActivity.mMap);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            // Failed to read value
            Log.e(TAG, ERROR_MESSAGE, databaseError.toException());
        }
    });
    return areaList;//might cause problems with asynchronous nature of db queries
}

//
/**
 * method for updating average strength value and the number of locations for an area
 * @param area
 * @param locationStrength
 */
 static  void updateStrength(Area area, double locationStrength){

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
//                Orchastrator.updateMapWithDataPoints(locationList, map);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.e(TAG, ERROR_MESSAGE, error.toException());
            }
        });

    }

}
