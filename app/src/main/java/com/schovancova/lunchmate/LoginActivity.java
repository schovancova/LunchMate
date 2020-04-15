package com.schovancova.lunchmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.schovancova.lunchmate.global.Status;
import com.schovancova.lunchmate.login.ForgotPassword_Fragment;
import com.schovancova.lunchmate.login.Initial_Fragment;
import com.schovancova.lunchmate.login.Login_Fragment;
import com.schovancova.lunchmate.login.SignUp_Fragment;

public class LoginActivity extends AppCompatActivity {
	private static FragmentManager fragmentManager;
	private LoginViewModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getSupportFragmentManager();

		// On close icon click finish activity
		findViewById(R.id.close_activity).setOnClickListener(
				arg0 -> finish());
		model = new ViewModelProvider(this).get(LoginViewModel.class);
		model.setStatus(Status.INITIAL);
		model.getStatus().observe(this, status -> {
			switch (status) {
				case INITIAL:
					fragmentManager
						.beginTransaction()
						.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
						.replace(R.id.frameContainer, new Initial_Fragment()).commit();
					break;
				case LOGIN:
					fragmentManager
							.beginTransaction()
							.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
							.replace(R.id.frameContainer, new Login_Fragment()).commit();
					break;
				case REGISTER:
					fragmentManager
							.beginTransaction()
							.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
							.replace(R.id.frameContainer, new SignUp_Fragment()).commit();
					break;
				case FORGOT_PASSWORD:
					fragmentManager
							.beginTransaction()
							.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
							.replace(R.id.frameContainer, new ForgotPassword_Fragment()).commit();
					break;
			}
		});
	}


	@Override
	public void onBackPressed() {
		switch (model.currentStatus) {
			case INITIAL:
				super.onBackPressed();
				break;
			case LOGIN:
			case REGISTER:
				model.setStatus(Status.INITIAL);
				break;
			case FORGOT_PASSWORD:
				model.setStatus(Status.LOGIN);
				break;
		}
	}
}
