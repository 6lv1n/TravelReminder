package com.travelreminder.android22.Screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.travelreminder.android22.R;

// USE MAP VIEW NOW
// TODO print steps on map

public class ShowTravelScreen extends MapActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showtravelscreen);

		Button b = (Button) findViewById(R.id.btnclose);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				setResult(RESULT_OK);
				finish();
			}
		});

		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
