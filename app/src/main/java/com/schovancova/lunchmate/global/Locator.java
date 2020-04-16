package com.schovancova.lunchmate.global;


import android.location.Location;

import com.schovancova.lunchmate.BuildConfig;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Locator {
    private static final String API_KEY = BuildConfig.GOOGLE_PLACES_API;
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json?";
    private Thread communicator;

    private Location l;
    private int radius;

    public Locator(int radius, Location l) {
        this.l = l;
        this.radius = radius;
    }

    private String buildUrl() {
        StringBuilder sb = new StringBuilder(PLACES_API_BASE)
                .append(PLACES_API_BASE)
                .append(TYPE_SEARCH)
                .append(OUT_JSON)
                .append("location=" + l.getLatitude() + "," + l.getLongitude())
                .append("&radius=" + radius)
                .append("&type=restaurant")
                .append("&key=" + API_KEY);
        return sb.toString();
    }

    public String getNearest() throws InterruptedException {
        String address = buildUrl();
        StringBuilder jsonResults = new StringBuilder();
        communicator = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    int read;
                    char[] buff = new char[1024];
                    while ((read = in.read(buff)) != -1) {
                        jsonResults.append(buff, 0, read);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        });
        communicator.start();
        communicator.join(5000);
        return jsonResults.toString();
    }
}
