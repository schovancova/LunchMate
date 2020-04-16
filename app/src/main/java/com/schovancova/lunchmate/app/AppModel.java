package com.schovancova.lunchmate.app;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.schovancova.lunchmate.global.Restaurant;
import com.schovancova.lunchmate.global.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AppModel extends ViewModel {

    private final MutableLiveData<Status> status = new MutableLiveData<Status>();
    public Status currentStatus;

    public ArrayList<Restaurant> parseRestaurants(String restaurants) {
        ArrayList<Restaurant> resultList = null;
        try {
            JSONObject jsonObj = new JSONObject(restaurants);
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");
            resultList = new ArrayList<Restaurant>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                Gson gson = new Gson();
                Restaurant place = gson.fromJson(predsJsonArray.getJSONObject(i).toString(), Restaurant.class);
                resultList.add(place);
            }
        } catch (JSONException e) {
            Log.e("ListRest", "Error processing JSON results", e);
        }
        return resultList;
    }

    public void setStatus(Status new_status) {
        currentStatus = new_status;
        status.setValue(new_status);
    }

    public LiveData<Status> getStatus() {
        return status;
    }
    }
