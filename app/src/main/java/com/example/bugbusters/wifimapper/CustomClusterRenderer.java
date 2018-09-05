package com.example.bugbusters.wifimapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.data.Layer;
import com.google.maps.android.ui.IconGenerator;

public class CustomClusterRenderer extends DefaultClusterRenderer<LocationCapstone> {

    private Context context;

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<LocationCapstone> clusterManager) {
        super(context, map, clusterManager);
        this.context=context;
    }

    @Override
    protected int getColor(int clusterSize) {
        return super.getColor(clusterSize);
    }

    @Override
    public void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<LocationCapstone> listener) {
        super.setOnClusterItemInfoWindowClickListener(listener);
    }

    @Override
    protected void onBeforeClusterItemRendered(LocationCapstone item, MarkerOptions markerOptions) {
        int hue=ColorScheme.getHue(ColorScheme.evaluateColor((int)item.getStrength()));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(hue));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<LocationCapstone> cluster, MarkerOptions markerOptions) {
        int total=0;
        for(LocationCapstone location:cluster.getItems())
        {
            total+=location.getStrength();
        }
        IconGenerator iconGenerator=new IconGenerator(this.context);
        iconGenerator.setColor(ColorScheme.getNonTransparentColor(total));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon(Integer.toString(cluster.getItems().size()))));
    }

}
