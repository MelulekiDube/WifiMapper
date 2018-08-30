package com.example.bugbusters.wifimapper;

public class LatLng {

    private Double latitude;
    private Double longitude;


    public LatLng() {

    }

    public LatLng(double latitude,double longitude)
    {
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public com.google.android.gms.maps.model.LatLng toGoogleLatLng()
    {
        return new com.google.android.gms.maps.model.LatLng(this.latitude,this.longitude);
    }

}
