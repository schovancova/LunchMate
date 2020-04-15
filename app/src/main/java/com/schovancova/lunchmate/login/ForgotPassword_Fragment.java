package com.schovancova.lunchmate.login;

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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.schovancova.lunchmate.global.CustomToast;
import com.schovancova.lunchmate.MainActivity;
import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.global.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword_Fragment extends Fragment implements
		OnClickListener {
	private View view;

	private EditText emailId;
	private TextView submit, back;

	public ForgotPassword_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.forgotpassword_layout, container,
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
		@SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

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
			new MainActivity().replaceLoginFragment();
			break;

		case R.id.forgot_button:
			submitButtonTask();
			break;
		}
	}

	private void submitButtonTask() {
		String getEmailId = emailId.getText().toString();
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		if (getEmailId.equals("") || getEmailId.length() == 0)
			new CustomToast().Show_Toast(getActivity(), view,
					"Please enter your email.");
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"Your email is invalid.");
		else
			// TODO send reset link
			Toast.makeText(getActivity(), "Get Forgot Password.",
					Toast.LENGTH_SHORT).show();
	}
}