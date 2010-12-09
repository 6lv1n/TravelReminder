package com.travelreminder.android22.Views;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.travelreminder.android22.R;
import com.travelreminder.android22.StepItemizedOverlay;
import com.travelreminder.android22.TravelReminder;

// USE MAP VIEW NOW
// TODO print steps on map

public class ShowTravelScreen extends MapActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showtravelview);

		/*Button b = (Button) findViewById(R.id.btnclose);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				setResult(RESULT_OK);
				finish();
			}
		});*/

		/*MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		StepItemizedOverlay itemizedoverlay = new StepItemizedOverlay(drawable);
		
		int i = (int)TravelReminder.testTravel.getTravel().last().getLocation().getLatitude() * 1000000;
		int j = (int)TravelReminder.testTravel.getTravel().last().getLocation().getLongitude() * 1000000;
		
		GeoPoint point = new GeoPoint(i,j);
		OverlayItem overlayitem = new OverlayItem(point, "Last step", "Ola chikca!");
		
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);*/
		
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
