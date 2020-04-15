package com.schovancova.lunchmate.login.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.global.Status;
import com.schovancova.lunchmate.login.LoginViewModel;

public class Initial extends Fragment implements OnClickListener {
    private LoginViewModel model;

    public Initial() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.initial_layout, container, false);
        model = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        initViews(view);
        return view;
    }

    // Initiate Views
    private void initViews(View view) {
        Button loginButton = view.findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this::onClick);
//
        Button RegisterButton = view.findViewById(R.id.registerBtn);
        RegisterButton.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                model.setStatus(Status.LOGIN);
                break;
            case R.id.registerBtn:
                model.setStatus(Status.REGISTER);
                break;
        }
    }
}
