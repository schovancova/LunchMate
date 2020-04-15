package com.schovancova.lunchmate.listing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.global.Status;
import com.schovancova.lunchmate.listing.fragments.Listing;

public class ListingActivity extends AppCompatActivity {
	private static FragmentManager fragmentManager;
	private ListingViewModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listing_layout);
		fragmentManager = getSupportFragmentManager();
		model = new ViewModelProvider(this).get(ListingViewModel.class);
		model.setStatus(Status.LISTING);
		model.getStatus().observe(this, status -> {
			switch (status) {
				case LISTING:
					fragmentManager
							.beginTransaction()
							.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
							.replace(R.id.frameContainer, new Listing()).commit();
					break;
			}
		});
	}



	@Override
	public void onBackPressed() {
//		switch (model.currentStatus) {
//			case INITIAL:
//				super.onBackPressed();
//				break;
//			case LOGIN:
//			case REGISTER:
//				model.setStatus(Status.INITIAL);
//				break;
//			case FORGOT_PASSWORD:
//				model.setStatus(Status.LOGIN);
//				break;
//		}
	}
}
