package com.example.bugbusters.wifimapper.listeners;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bugbusters.wifimapper.LocationCapstone;
import com.example.bugbusters.wifimapper.Orchastrator;
import com.example.bugbusters.wifimapper.Values;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LocationDatabase implements ValueEventListener, ChildEventListener {
    private final List<LocationCapstone> LOCATION_LIST = new ArrayList<>();

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            LocationCapstone location = snapshot.getValue(LocationCapstone.class);
            LOCATION_LIST.add(location);
        }
        Orchastrator.setLocationList(LOCATION_LIST);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        // Failed to read value
        Log.e(Values.TAG, error.getMessage());
    }

    //begin ChildEven methods
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        LocationCapstone newObject = dataSnapshot.getValue(LocationCapstone.class);
        Orchastrator.updateSegmentWithObject(newObject);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        LocationCapstone changedLocationCapstone = dataSnapshot.getValue(LocationCapstone.class);
        //method to update the values on location changes will be placed here
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }
}
