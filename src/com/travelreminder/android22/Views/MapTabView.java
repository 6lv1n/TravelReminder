package com.travelreminder.android22.Views;

import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.travelreminder.android22.R;

public class MapTabView extends MapActivity {
	private MapView mapView;
	private double lat = 0;
    private double lng = 0;
    private LocationManager lm;
    private MapController mc;
    private GeoPoint p;
    
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.showtravelview);
		
		mapView = (MapView) findViewById(R.id.mapview);
		mc = mapView.getController();
		lat = 48.8157060;
		lng = 2.3628730;
		p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		mc.animateTo(p);
		mc.setCenter(p);
		mc.setZoom(17);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
