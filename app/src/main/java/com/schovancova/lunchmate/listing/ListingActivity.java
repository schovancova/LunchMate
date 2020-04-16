package com.schovancova.lunchmate.listing;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.global.Status;

public class ListingActivity extends AppCompatActivity {
	private ListingViewModel model;
	private BottomNavigationView bottom_nav;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listing_layout);
		initClicks();
		bottom_nav = findViewById(R.id.bottom_navigation_main);
		model = new ViewModelProvider(this).get(ListingViewModel.class);
		model.setStatus(Status.LISTING);
		model.getStatus().observe(this, status -> {
			switch (status) {
				case LISTING:
//					getSupportFragmentManager()
//							.beginTransaction()
//							.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
//							.replace(R.id.frameContainer, new Listing()).commit();
					break;
			}
		});
	}

	private void initClicks() {
		ImageView iv_add = findViewById(R.id.iv_add);
		iv_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bottom_nav.setSelectedItemId(R.id.nav_hidden_option);
				if ((String) iv_add.getTag() == "map_pin") {
					iv_add.setImageResource(R.drawable.list);
					iv_add.setTag("list");
				}
				else {
					iv_add.setImageResource(R.drawable.map_pin);
					iv_add.setTag("map_pin");
					}
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
