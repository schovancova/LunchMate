package com.schovancova.lunchmate.global;

import java.util.List;

class Photo {
    public int height;
    public List<String> html_attributions;
    public String photo_reference;
    public int width;
}

class OpeningHours {
    public Boolean open_now;
}

public class Restaurant {
    private String reference;
    private String name;
    private String icon;
    private OpeningHours opening_hours;
    private float rating;
    private List<Photo> photos;

    public String getReference() {
        return reference;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public Boolean getOpenNow() {
        if (opening_hours != null) return opening_hours.open_now;
        else return false;
    }

    public float getRating() {
        return rating;
    }


    public String getPhotoReference() {
        return photos.get(0).photo_reference;
    }
}