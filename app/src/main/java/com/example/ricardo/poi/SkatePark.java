package com.example.ricardo.poi;

import android.location.Location;

/**
 * Created by Ricardo on 08/05/2015.
 */
public class SkatePark {

    private Location Location;
    private String MarkerText;
    private String Snippet;

    public SkatePark(){
        MarkerText="";
    }


    //location
    public Location getLocation() {
        return Location;
    }
    public void setLocation(Location location) {
        this.Location = location;
    }

    //MarkerText
    public String getMarkerText() {
        return MarkerText;
    }
    public void setMarkerText(String markerText) {
        if(markerText!=null)
            MarkerText = markerText;
    }

    //Snippet
    public String getSnippet() {
        return Snippet;
    }
    public void setSnippet(String snippet) {
        this.Snippet = snippet;
    }
}
