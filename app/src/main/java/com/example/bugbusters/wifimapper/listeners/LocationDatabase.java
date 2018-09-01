package com.example.bugbusters.wifimapper.listeners;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bugbusters.wifimapper.LocationCapstone;
import com.example.bugbusters.wifimapper.Orchastrator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LocationDatabaseListener implements ValueEventListener {
    final List<LocationCapstone> locationList = new ArrayList<>();
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            LocationCapstone location = snapshot.getValue(LocationCapstone.class);
            locationList.add(location);
        }
        Orchastrator.createMappingsFromList(locationList);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        // Failed to read value
        Log.e(Values.TAG,Values.ERROR_MESSAGE, error.toException());
    }
}
