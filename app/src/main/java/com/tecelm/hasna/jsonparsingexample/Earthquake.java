package com.tecelm.hasna.jsonparsingexample;

/**
 * Created by Admin on 23/02/2018.
 */

public class Earthquake {
    // @param magnitude earth quake magnitude
    private String magnitude;

    // @param city location of earthquake
    private String location;

    // @param date , the  date of the earthquake
    private String date;

    public Earthquake(String magnitude, String location, String date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
