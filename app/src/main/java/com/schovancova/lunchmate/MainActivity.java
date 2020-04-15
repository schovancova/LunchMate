package com.schovancova.lunchmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.schovancova.lunchmate.global.FragmentChanger;
import com.schovancova.lunchmate.global.Utils;

public class MainActivity extends AppCompatActivity {
	public static FragmentChanger fragmentChanger;
	private static FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getSupportFragmentManager();
		fragmentChanger = new FragmentChanger(fragmentManager);

		if (savedInstanceState == null) {
			fragmentChanger.change_initial();
		}

		// On close icon click finish activity
		findViewById(R.id.close_activity).setOnClickListener(
				arg0 -> finish());



	}

	// Replace Login Fragment with animation
	public void replaceLoginFragment() {
		fragmentChanger.change_login();
	}

	public void replaceInitialFragment() {
		fragmentChanger.change_initial();
	}

	@Override
	public void onBackPressed() {
		Fragment Login_Fragment = fragmentManager.findFragmentByTag(Utils.Login_Fragment);
		Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Utils.SignUp_Fragment);
		Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils.ForgotPassword_Fragment);

		if (SignUp_Fragment != null || Login_Fragment != null)
			replaceInitialFragment();
		else if (ForgotPassword_Fragment != null)
			replaceLoginFragment();
		else
			super.onBackPressed();
	}
}
