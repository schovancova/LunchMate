package com.schovancova.lunchmate.app.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.app.AppModel;
import com.schovancova.lunchmate.app.adapters.ListingAdapter;
import com.schovancova.lunchmate.app.adapters.ListingItemAdapter;
import com.schovancova.lunchmate.global.Locator;
import com.schovancova.lunchmate.global.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Listing extends Fragment implements OnClickListener {
    private AppModel model;
    private LocationManager lm;
    private RecyclerView mRecycleview;
    private List<ListingItemAdapter> mList = new ArrayList<>();
    private ListingAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        mRecycleview = view.findViewById(R.id.recycler_view);
        addList();
        adapter();
        model = new ViewModelProvider(getActivity()).get(AppModel.class);
        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        int radius = 1000;
        ArrayList<Restaurant> list = search(location, radius);
        initViews(view);
        return view;
    }

    // Initiate Views
    private void initViews(View view) {

    }

    private void addList(){
        ListingItemAdapter itemAdapter = new ListingItemAdapter();
        itemAdapter.setImage(R.drawable.login_confirm_password);
        itemAdapter.setText("Tomato");
        mList.add(itemAdapter);
    }

    private void adapter(){
        mAdapter = new ListingAdapter(mList, getActivity());
        mRecycleview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
    }

    @Override
    public void onClick(View v) {

    }
    public ArrayList<Restaurant> search(Location l, int radius) {
        Locator locator = new Locator(radius, l);
        String restaurants = null;
        try {
            restaurants = locator.getNearest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return model.parseRestaurants(restaurants);
    }
}
