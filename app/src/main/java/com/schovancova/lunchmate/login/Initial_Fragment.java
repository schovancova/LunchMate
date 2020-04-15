package com.schovancova.lunchmate.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.schovancova.lunchmate.MainActivity;
import com.schovancova.lunchmate.R;

public class Initial_Fragment extends Fragment implements OnClickListener {
    private static View view;

    private static Button loginButton;
    private static Button RegisterButton;

    public Initial_Fragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.initial_layout, container, false);
        initViews(view);
        return view;
    }

    // Initiate Views
    private void initViews(View view) {
        loginButton = view.findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this::onClick);
//
        RegisterButton = view.findViewById(R.id.registerBtn);
        RegisterButton.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                new MainActivity().fragmentChanger.change_login();
                break;
            case R.id.registerBtn:
                new MainActivity().fragmentChanger.change_register();
                break;
        }
    }
}
