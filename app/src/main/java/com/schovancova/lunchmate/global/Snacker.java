package com.schovancova.lunchmate.global;

import android.app.Activity;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Snacker {

    public void make(Activity context, String string) {
		View parentLayout = context.findViewById(android.R.id.content);
        Snackbar.make(parentLayout, string, Snackbar.LENGTH_LONG)
			.setAction("CLOSE", view -> {
			})
			.setActionTextColor(context.getResources().getColor(android.R.color.background_light))
			.show();
    }

}
