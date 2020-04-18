package com.schovancova.lunchmate.global;

import java.util.List;
import java.util.Map;

class Photo {
    public int height;
    public List<String> html_attributions;
    public String photo_reference;
    public int width;
}

class OpeningHours {
    public Boolean open_now;
}

class Geometry {
    public Map<String, Float> location;
    public Map<String, Map<String, Float>> viewport;
}

public class Restaurant {
    private String name;
    private OpeningHours opening_hours;
    private float rating;
    private List<Photo> photos;
    private String vicinity;


    private Geometry geometry;

    public String getName() {
        return name;
    }


    public Boolean getOpenNow() {
        if (opening_hours != null) return opening_hours.open_now;
        else return false;
    }

    public float getRating() {
        return rating;
    }


    public String getVicinity() {
        return vicinity;
    }

    public Map<String, Float> getGeometry() {
        return geometry.location;
    }

    public String getPhotoReference() {
        return photos.get(0).photo_reference;
    }
}