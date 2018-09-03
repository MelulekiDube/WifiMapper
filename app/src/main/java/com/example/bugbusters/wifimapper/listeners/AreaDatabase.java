package com.example.bugbusters.wifimapper.listeners;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bugbusters.wifimapper.Area;
import com.example.bugbusters.wifimapper.ColorScheme;
import com.example.bugbusters.wifimapper.DatabaseUtils;
import com.example.bugbusters.wifimapper.MapsActivity;
import com.example.bugbusters.wifimapper.Orchastrator;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AreaDatabase implements ValueEventListener, ChildEventListener {
    private List<Area> areaList = new ArrayList<>();

    public static void createPolygon(Area area) {
        PolygonOptions polygon =
                new PolygonOptions().addAll(area.getGoogleCoordinates())
                        .strokeWidth(2.2f)
                        .fillColor(ColorScheme.evaluateColor(area.getWifiStrength()));


        Orchastrator.areaPolygonMappings.put(area.getId(), MapsActivity.mMap.addPolygon(polygon));
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Area area = dataSnapshot.getValue(Area.class);
        MapsActivity.updateArea(area);
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        // Failed to read value
        Log.e(Values.TAG, databaseError.getMessage());
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Area area = snapshot.getValue(Area.class);
            areaList.add(area);
            createPolygon(area);
        }
        Orchastrator.setAreaList(areaList);
        DatabaseUtils.loadedArea = true;
    }

}
