package com.schovancova.lunchmate.login;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.schovancova.lunchmate.LoginViewModel;
import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.global.Snacker;
import com.schovancova.lunchmate.global.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Fragment extends Fragment implements OnClickListener, OnCheckedChangeListener {
    private View view;

    private EditText emailid, password;
    private Button loginButton;
    private TextView forgotPassword, signUp;
    private CheckBox show_hide_password;
    private LinearLayout loginLayout;
    private Animation shakeAnimation;
    private FragmentManager fragmentManager;
    private LoginViewModel model;


    public Login_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        model = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        initViews(view);
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews(View view) {
        fragmentManager = getActivity().getSupportFragmentManager();
        emailid =  view.findViewById(R.id.login_email);
        password = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.loginBtn);
        forgotPassword = view.findViewById(R.id.forgot_password);
        signUp = view.findViewById(R.id.createAccount);
        show_hide_password = view.findViewById(R.id.show_hide_password);
        loginLayout = view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);
        show_hide_password.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            show_hide_password.setText(R.string.hide_pwd);// change
            password.setInputType(InputType.TYPE_CLASS_TEXT);
            password.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance());// show password
        } else {
            show_hide_password.setText(R.string.show_pwd);// change
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            password.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());// hide password
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;

            case R.id.forgot_password:
                model.setStatus(Status.FORGOT_PASSWORD);
                break;
            case R.id.createAccount:
                model.setStatus(Status.REGISTER);
                break;
        }
    }

    private void checkValidation() {
        Snacker snacker = new Snacker();
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();
        Pattern p = Pattern.compile(model.regEx);
        Matcher m = p.matcher(getEmailId);
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            snacker.make(getActivity(), "Enter both credentials");
        }
        else if (!m.find())
            snacker.make(getActivity(), "Email is invalid");
        else
            // TODO login
            snacker.make(getActivity(), "Logging in");

    }
}
