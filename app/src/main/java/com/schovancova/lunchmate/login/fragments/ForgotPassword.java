package com.schovancova.lunchmate.login.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.global.Snacker;
import com.schovancova.lunchmate.global.Status;
import com.schovancova.lunchmate.login.LoginViewModel;

public class ForgotPassword extends Fragment implements
		OnClickListener {

	private LoginViewModel model;
	private EditText emailId;
	private TextView submit, back;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		model = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
		View view = inflater.inflate(R.layout.forgotpassword_layout, container,
				false);
		initViews(view);
		setListeners();
		return view;
	}

	// Initialize the views
	private void initViews(View view) {
		emailId = view.findViewById(R.id.email);
		submit = view.findViewById(R.id.forgot_button);
		back = view.findViewById(R.id.backToLoginBtn);

		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		// Setting text selector over textviews
		@SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.login_text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
			back.setTextColor(csl);
			submit.setTextColor(csl);
		} catch (Exception e) {
		}

	}

	// Set Listeners over buttons
	private void setListeners() {
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backToLoginBtn:
			model.setStatus(Status.LOGIN);
			break;

		case R.id.forgot_button:
			submitButtonTask();
			break;
		}
	}

	private void submitButtonTask() {
		Snacker snacker = new Snacker();
		if (!model.validateEmail(emailId.getText().toString())) {
			snacker.make(getActivity(), "@strings/email_invalid");
		} else {
			snacker.make(getActivity(), "@strings/password_reset_success");
		}
	}
}