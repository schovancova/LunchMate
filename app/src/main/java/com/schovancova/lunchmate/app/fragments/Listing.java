package com.schovancova.lunchmate.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.app.AppModel;

public class Listing extends Fragment implements OnClickListener {
    private AppModel model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_layout, container, false);
        model = new ViewModelProvider(getActivity()).get(AppModel.class);
        initViews(view);
        return view;
    }

    // Initiate Views
    private void initViews(View view) {

    }


    @Override
    public void onClick(View v) {

    }
}
