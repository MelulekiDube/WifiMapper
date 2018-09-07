package com.example.bugbusters.wifimapper;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class CustomClusterRenderer extends DefaultClusterRenderer<LocationRecord> {

    private Context context;

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<LocationRecord> clusterManager) {
        super(context, map, clusterManager);
        this.context=context;
    }

    @Override
    protected int getColor(int clusterSize) {
        return super.getColor(clusterSize);
    }

    @Override
    public void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<LocationRecord> listener) {
        super.setOnClusterItemInfoWindowClickListener(listener);
    }

    @Override
    protected void onBeforeClusterItemRendered(LocationRecord item, MarkerOptions markerOptions) {
        int hue=ColorScheme.getHue(ColorScheme.evaluateColor((int)item.getStrength()));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(hue));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<LocationRecord> cluster, MarkerOptions markerOptions) {
        int total=0;
        for (LocationRecord location : cluster.getItems())
        {
            total+=location.getStrength();
        }
        IconGenerator iconGenerator=new IconGenerator(this.context);
        iconGenerator.setColor(ColorScheme.getNonTransparentColor(total));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon(Integer.toString(cluster.getItems().size()))));
    }

}
