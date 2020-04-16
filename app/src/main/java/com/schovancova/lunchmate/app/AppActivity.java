package com.schovancova.lunchmate.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.app.fragments.Listing;
import com.schovancova.lunchmate.app.fragments.ToolbarFragment;
import com.schovancova.lunchmate.global.Status;

public class AppActivity extends AppCompatActivity {
	private AppModel model;
	private BottomNavigationView bottom_nav;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_layout);
		initClicks();

		bottom_nav = findViewById(R.id.bottom_navigation_main);
		model = new ViewModelProvider(this).get(AppModel.class);

		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.searchb_container, new ToolbarFragment()).commit();

		model.setStatus(Status.LISTING);
		model.getStatus().observe(this, status -> {
			switch (status) {
				case LISTING:
					getSupportFragmentManager()
						.beginTransaction()
						.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
						.replace(R.id.app_fragment_container, new Listing()).commit();
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
				if ((String) iv_add.getTag() == "navb_map_icon") {
					iv_add.setImageResource(R.drawable.navb_list_icon);
					iv_add.setTag("list");
				}
				else {
					iv_add.setImageResource(R.drawable.navb_map_icon);
					iv_add.setTag("navb_map_icon");
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
