package com.schovancova.lunchmate.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.app.fragments.Listing;
import com.schovancova.lunchmate.app.fragments.ToolbarFragment;
import com.schovancova.lunchmate.global.Snacker;
import com.schovancova.lunchmate.global.Status;

public class AppActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_LOCATION = 0;
	private AppModel model;
	private BottomNavigationView bottom_nav;
	private Snacker snacker;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_layout);
		initClicks();
		initToolbar();
		snacker = new Snacker();
		bottom_nav = findViewById(R.id.bottom_navigation_main);
		model = new ViewModelProvider(this).get(AppModel.class);
		model.setStatus(Status.LISTING);
		model.getStatus().observe(this, status -> {
			switch (status) {
				case LISTING:
                    showRestaurants();
					break;
			}
		});

	}

	private void initToolbar() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.searchb_container, new ToolbarFragment()).commit();
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


    public void showRestaurants(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
			getSupportFragmentManager()
					.beginTransaction()
					.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
					.replace(R.id.app_fragment_container, new Listing()).commit();
        } else {
            requestLocationPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showRestaurants();
            } else {
                snacker.make(this, "@strings/location_permission_denied");
            }
        }
    }
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            snacker.make(this, "@strings/location_permission_required");
            ActivityCompat.requestPermissions(AppActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_LOCATION);
        } else {
            snacker.make(this,  "@strings/location_permission_incoming");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_LOCATION);
        }
    }
}
