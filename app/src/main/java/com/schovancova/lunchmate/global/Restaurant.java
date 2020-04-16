package com.schovancova.lunchmate.global;

public class Restaurant {
    private String reference;
    private String name;
    private String icon;
    private Boolean open_now;
    private double rating;
    private double latitude;
    private double longitude;


    public Restaurant(){
        super();
    }
    @Override
    public String toString(){
        return this.name; //This is what returns the name of each restaurant for array list
    }
}