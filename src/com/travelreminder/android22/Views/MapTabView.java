package com.travelreminder.android22.Views;

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.travelreminder.android22.R;
import com.travelreminder.android22.R.layout;

public class MapTabView extends MapActivity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.showtravelscreen);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
