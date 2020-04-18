package com.schovancova.lunchmate.global;


import android.graphics.Bitmap;
import android.location.Location;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.schovancova.lunchmate.BuildConfig;

public class Locator {
    private static final String API_KEY = BuildConfig.GOOGLE_PLACES_API;
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_SEARCH = "/nearbysearch";
    private static final String PHOTO_SEARCH = "/photo";
    private static final String OUT_JSON = "/json?";

    RequestQueue queue;

    public Locator(RequestQueue queue) {
        this.queue = queue;

    }

    private String buildPlacesUrl(int radius, Location l) {
        StringBuilder sb = new StringBuilder()
                .append(PLACES_API_BASE)
                .append(TYPE_SEARCH)
                .append(OUT_JSON)
                .append("location=" + l.getLatitude() + "," + l.getLongitude())
                .append("&radius=" + radius)
                .append("&type=restaurant")
                .append("&key=" + API_KEY);
        return sb.toString();
    }

    private String buildPictureUrl(String photo_ref) {
        StringBuilder sb = new StringBuilder()
                .append(PLACES_API_BASE)
                .append(PHOTO_SEARCH)
                .append("?maxwidth=" + 300)
                .append("&photoreference="+ photo_ref)
                .append("&key=" + API_KEY);
        return sb.toString();
    }

    public interface VolleyCallback{
        void onSuccess(String string);
    }
    public interface VolleyCallbackImg extends Response.Listener {
        void onResponse(Object response);
    }

    public void getNearest(int radius, Location l, final VolleyCallback callback) {
        String address = buildPlacesUrl(radius, l);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, address,
                response -> callback.onSuccess(response), error -> error.printStackTrace());
        queue.add(stringRequest);
    }

    public void getPicture(final VolleyCallbackImg callback, String photo_ref) {
        String address = buildPictureUrl(photo_ref);
        ImageRequest imageRequest = new ImageRequest(
                address, callback,
                0, // Image width
                0, // Image height
                ImageView.ScaleType.CENTER_CROP, // Image scale type
                Bitmap.Config.RGB_565, null
        );
        queue.add(imageRequest);

    }
}


