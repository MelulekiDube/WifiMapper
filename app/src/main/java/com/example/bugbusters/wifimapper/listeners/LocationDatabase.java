package com.example.bugbusters.wifimapper.listeners;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bugbusters.wifimapper.LocationRecord;
import com.example.bugbusters.wifimapper.Orchastrator;
import com.example.bugbusters.wifimapper.Values;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LocationDatabase implements ValueEventListener, ChildEventListener {
    private final List<LocationRecord> LOCATION_LIST = new ArrayList<>();
    private boolean alreadyLoaded = false;

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (!alreadyLoaded) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                LocationRecord location = snapshot.getValue(LocationRecord.class);
                LOCATION_LIST.add(location);
            }
            Orchastrator.setLocationList(LOCATION_LIST);
            alreadyLoaded = true;
        }
//        MapsActivity.renderMarkers();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        // Failed to read value
        Log.e(Values.TAG, error.getMessage());
    }

    //begin ChildEven methods
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        LocationRecord newLocation = dataSnapshot.getValue(LocationRecord.class);
        if (alreadyLoaded) Orchastrator.LOCATION_LIST.add(newLocation);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }
}
