package com.schovancova.lunchmate.login.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.global.Snacker;
import com.schovancova.lunchmate.global.Status;
import com.schovancova.lunchmate.login.LoginViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends Fragment implements OnClickListener {
	private EditText fullName, emailId, mobileNumber, location,
			password, confirmPassword;
	private TextView login;
	private Button signUpButton;
	private CheckBox terms_conditions;
	private LoginViewModel model;

	public SignUp() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		model = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
		View view = inflater.inflate(R.layout.signup_layout, container, false);
		initViews(view);
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews(View view) {
		fullName = view.findViewById(R.id.fullName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
		location = (EditText) view.findViewById(R.id.location);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

		// Setting text selector over textviews
		@SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.login_text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
			login.setTextColor(csl);
			terms_conditions.setTextColor(csl);
		} catch (Exception e) {
		}
	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signUpBtn:
			checkValidation();
			break;

		case R.id.already_user:
			model.setStatus(Status.LOGIN);
			break;
		}
	}

	// Check Validation Method
	private void checkValidation() {
		Snacker snacker = new Snacker();

		// Get all edittext texts
		String getFullName = fullName.getText().toString();
		String getEmailId = emailId.getText().toString();
		String getMobileNumber = mobileNumber.getText().toString();
		String getLocation = location.getText().toString();
		String getPassword = password.getText().toString();
		String getConfirmPassword = confirmPassword.getText().toString();

		// Pattern match for login_email id
		Pattern p = Pattern.compile(model.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getLocation.equals("") || getLocation.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0)
			snacker.make(getActivity(), "@strings/all_fields");
		else if (!m.find())
			snacker.make(getActivity(), "@strings/email_invalid");
		else if (!getConfirmPassword.equals(getPassword))
			snacker.make(getActivity(), "@strings/password_mismatch");
		else if (!terms_conditions.isChecked())
			snacker.make(getActivity(), "@strings/terms_and_cond");
		else
			// TODO signup
			snacker.make(getActivity(), "@strings/signing_up");
	}
}
