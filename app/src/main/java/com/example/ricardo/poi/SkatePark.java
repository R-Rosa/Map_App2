package com.example.ricardo.poi;

import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ricardo on 08/05/2015.
 */
public class SkatePark {

    private Location location;
    private String skateParkTitle;
    private Bitmap skateParkPhoto;
    private String parkDescription;
    private String parkDescriptionTitle;
    private String parkDirectionsTitle;
    private String parkDirections;
    private int markerId;


    public SkatePark(Location location, Bitmap skateParkPhoto, String skateParkTitle, String parkDescription, String parkDescriptionTitle, String parkDirectionsTitle,
                     String parkDirections){
        this.location = location;
        this.skateParkPhoto = skateParkPhoto;
        this.skateParkTitle = skateParkTitle;
        this.parkDescription = parkDescription;
        this.parkDescriptionTitle = parkDescriptionTitle;
        this.parkDirectionsTitle = parkDirectionsTitle;
        this.parkDirections = parkDirectionsTitle;
        this.parkDirections = parkDirections;
    }

    //location
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSkateParkTitle() {
        return skateParkTitle;
    }

    public void setSkateParkTitle(String skateParkTitle) {
        this.skateParkTitle = skateParkTitle;
    }

    public Bitmap getSkateParkPhoto() {
        return skateParkPhoto;
    }

    public void setSkateParkPhoto(Bitmap skateParkPhoto) {
        this.skateParkPhoto = skateParkPhoto;
    }

    public String getParkDescription() {
        return parkDescription;
    }

    public void setParkDescription(String parkDescription) {
        this.parkDescription = parkDescription;
    }

    public String getParkDescriptionTitle() {
        return parkDescriptionTitle;
    }

    public void setParkDescriptionTitle(String parkDescriptionTitle) {
        this.parkDescriptionTitle = parkDescriptionTitle;
    }

    public String getParkDirectionsTitle() {
        return parkDirectionsTitle;
    }

    public void setParkDirectionsTitle(String parkDirectionsTitle) {
        this.parkDirectionsTitle = parkDirectionsTitle;
    }

    public String getParkDirections() {
        return parkDirections;
    }

    public void setParkDirections(String parkDirections) {
        this.parkDirections = parkDirections;
    }

    public int getMarkerId() {
        return markerId;
    }

    public void setMarkerId(int markerId) {
        this.markerId = markerId;
    }
}
