package com.travelreminder.android22.Views;

import java.util.Iterator;
import java.util.List;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.travelreminder.android22.R;
import com.travelreminder.android22.Step;
import com.travelreminder.android22.StepItemizedOverlay;
import com.travelreminder.android22.TravelReminder;

public class MapTabView extends MapActivity {
	private MapView mapView;
	private LocationManager lm;
	private MapController mc;
	private StepItemizedOverlay stepItemizedOverlay;
	private SharedPreferences mPrefs;
	private List<Overlay> mapOverlays;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.showtravelview);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "onResume.", Toast.LENGTH_SHORT).show();
		test();
	};

	public void test() {

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(
				R.drawable.androidmarker);
		stepItemizedOverlay = new StepItemizedOverlay(drawable);

		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		if (mPrefs.getBoolean("TR_NEW_TRAVEL", true)) {
			if (TravelReminder.currentTravel.getTravel().size() > 0) {
				final Iterator<Step> travelIterator = TravelReminder.currentTravel
						.getTravel().iterator();
				Step tmpStep;
				//Looper.prepare();
				while (travelIterator.hasNext()) {
					tmpStep = travelIterator.next();
					GeoPoint p = new GeoPoint((int) (tmpStep.getLocation()
							.getLatitude() * 1E6), (int) (tmpStep.getLocation()
							.getLongitude() * 1E6));
					OverlayItem overlayItem = new OverlayItem(p, "XXX", "YYY");
					stepItemizedOverlay.addOverlay(overlayItem);
				}
				mapOverlays.add(stepItemizedOverlay);
			}
		} else {
			Toast.makeText(this,
					"No travel started or selected.\nNo step to display.",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
	};

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
