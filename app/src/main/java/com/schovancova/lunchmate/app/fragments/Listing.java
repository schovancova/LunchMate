package com.schovancova.lunchmate.app.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.app.AppModel;
import com.schovancova.lunchmate.app.adapters.ListingAdapter;
import com.schovancova.lunchmate.app.adapters.ListingItemAdapter;
import com.schovancova.lunchmate.global.Locator;
import com.schovancova.lunchmate.global.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Listing extends Fragment {
    private AppModel model;
    private LocationManager lm;
    private RecyclerView mRecycleview;
    private List<ListingItemAdapter> mList = new ArrayList<>();
    private ListingAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        mRecycleview = view.findViewById(R.id.recycler_view);
        model = new ViewModelProvider(getActivity()).get(AppModel.class);
        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        int radius = 1000;
        search(location, radius);
        return view;
    }


    private void addList(ArrayList<Restaurant> restaurants) {
        ListingItemAdapter itemAdapter;
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant r = restaurants.get(i);
            itemAdapter = new ListingItemAdapter();
            itemAdapter.name = r.getName();
            if (r.getOpenNow()) itemAdapter.open_now = "open";
            else itemAdapter.open_now = "closed";
            itemAdapter.rating = r.getRating();
            mList.add(itemAdapter);
        }

        adapter();
    }

    private void addPictures(ArrayList<Restaurant> restaurants) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Locator locator = new Locator(queue);
        for (int i = 0; i < restaurants.size(); i++) {
            ListingItemAdapter itemAdapter = mList.get(i);
            Restaurant r = restaurants.get(i);
            int finalI = i;
            locator.getPicture(new Locator.VolleyCallbackImg() {
                @Override
                public void onResponse(Object response) {
                    int x = finalI;
                    ListingItemAdapter la = mList.get(x);
                    la.image = (Bitmap) response;
                    mList.set(finalI, itemAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }, r.getPhotoReference());
        }

    }

    private void adapter() {
        mAdapter = new ListingAdapter(mList, getActivity());
        mRecycleview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }


    public void search(Location l, int radius) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Locator locator = new Locator(queue);
        locator.getNearest(radius, l, new Locator.VolleyCallback() {
            @Override
            public void onSuccess(String data) {
                ArrayList<Restaurant> restaurants = model.parseRestaurants(data);
                addList(restaurants);
                addPictures(restaurants);
            }
        });
    }
}
