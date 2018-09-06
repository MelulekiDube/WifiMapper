package com.example.bugbusters.wifimapper;


import com.google.firebase.database.Exclude;
import java.util.ArrayList;
import java.util.List;

/**
 * The area class represents the different WLAN zones on the uct campus. Polygons drawn over the map
 * area represented from these area objects
 *
 * @author {David Kheri}, {Clayton Sibanda}, {Meluleki Dube}
 * @version 1.1
 */
public class Area {
    private String id;
    private List<LatLng> coordinates;
    private String name;
    private int wifiStrength;
    private int numLocation;


    /**
     * Default constructor of the Area class which will be mainly used by firebase for creating an
     * Area object from the database.
     */
    public Area() {
    }

    /**
     * Area constructor which takes in the main fields needed to create an Area object
     *
     * @param id           the Area id that uniquely defines an area.
     * @param coordinates  List of cordinates that are required to create A segment on the map which then relates to a WLAN zone
     * @param name         The name given to that area
     * @param wifiStrength the average wifi strength in that area.
     * @param numLocation  The number of locations that have had data recorded from within that area
     */
    public Area(String id, List<LatLng> coordinates, String name, int wifiStrength, int numLocation) {
        //this.segment=segment;
        this.name = name;
        this.coordinates = new ArrayList<>(coordinates);
        this.id = id;
        this.wifiStrength=wifiStrength;
        this.numLocation = numLocation;
    }

    //Beggining of setters and getters

    /**
     * Method to get the area id assigned to that area.
     * @return The ID assigned to the Area.
     */
    public String getId() {
        return id;
    }

    /**
     * Method to set the ID of a particular Area. This method is particular used by firebase.
     * @param id the ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to get the cordinates that make up a WLAN zone
     * @return return a list with all the LatLang objects.
     */
    public List<LatLng> getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the Cordinates of an area.
     * @param coordinates that make an area.
     */
    public void setCoordinates(ArrayList<LatLng> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Used to get the latlng objects that make a WLAN in form of the google latlng object
     *
     * @return a list of the cordinates making up the wlan.
     */
    public List<com.google.android.gms.maps.model.LatLng> getGoogleCoordinates() {
        List<com.google.android.gms.maps.model.LatLng> resultList = new ArrayList<>();

        for (LatLng item : coordinates) {
            resultList.add(item.toGoogleLatLng());
        }

        return resultList;
    }

    /**
     * Gives the nma eo fthe current location
     * @return the Area/Zone name
     */
    public String getName() {
        return name;
    }

    /**
     * Allows the user to set the new name of the Area.
     * @param name the name of the Area.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to give you the average wifi strength recorded for that area.
     * @return the average recorded wifi strenght for the place
     */
    public int getWifiStrength() {
        return wifiStrength;
    }

    /**
     *  Sets the wifi strength of the area.
     * @param wifiStrength which is the wifi strength
     */
    public void setWifiStrength(int wifiStrength) {
        this.wifiStrength = wifiStrength;
    }

    /**
     * Method to get the number of location associated with an area
     * @return the number of location for that area.
     */
    public int getNumLocation() {
        return numLocation;
    }

    /**
     * Method to set the number of location
     * @param numLocation the new number of location
     */
    public void setNumLocation(int numLocation) {
        this.numLocation = numLocation;
    }

    /**
     *  Used by the Transaction handler to ensure concurrent sending of the same area
     * @param wifiStrength which is the new Wi-Fi
     * @return a new updated area object.
     */
    @Exclude
    public void updateArea(int wifiStrength) {
        long cumulativeStrength = (this.numLocation * this.wifiStrength) + wifiStrength;// get the running total and not use the average
        ++numLocation; // increment the number of location
        this.setWifiStrength((int) (cumulativeStrength / numLocation)); //set the new wifiStrength signal.

    }

}

