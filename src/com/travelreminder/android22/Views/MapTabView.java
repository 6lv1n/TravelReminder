package com.travelreminder.android22.Views;

import java.util.Iterator;
import java.util.List;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.travelreminder.android22.R;
import com.travelreminder.android22.Step;
import com.travelreminder.android22.StepItemizedOverlay;
import com.travelreminder.android22.Travel;
import com.travelreminder.android22.TravelReminder;
import com.travelreminder.android22.UserLocation;
import com.travelreminder.android22.UserLocation.LocationResult;
import com.travelreminder.android22.Exceptions.NoCooException;

public class MapTabView extends MapActivity implements Runnable {
	private MapView mapView;
	private StepItemizedOverlay stepItemizedOverlay;
	private SharedPreferences mPrefs;
	protected ProgressDialog getCooProgressDialog;
	private List<Overlay> mapOverlays;
	private UserLocation userLocation;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.showtravelview);
		Toast.makeText(this, "onCreate.", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		if (mPrefs.getBoolean("STEP_MODE", false)) {
			setUserLocationOnMap();
			SharedPreferences.Editor ed = mPrefs.edit();
			ed.putBoolean("STEP_MODE", false);
			ed.commit();
		} else {
			displayTravelOnMap(TravelReminder.currentTravel);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			userLocation.timer1.cancel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", mPrefs.getBoolean("TR_STATE", false));
		ed.putBoolean("TR_NEW_TRAVEL",
				mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	};

	@Override
	protected void onStop() {
		super.onStop();
		try {
			userLocation.timer1.cancel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", mPrefs.getBoolean("TR_STATE", false));
		ed.putBoolean("TR_NEW_TRAVEL",
				mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void displayTravelOnMap(Travel traveltoDisplay) {
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(
				R.drawable.androidmarker);
		stepItemizedOverlay = new StepItemizedOverlay(drawable);
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		if (mPrefs.getBoolean("TR_NEW_TRAVEL", true)) {
			if (traveltoDisplay.getTravel().size() > 0) {
				final Iterator<Step> travelIterator = traveltoDisplay
						.getTravel().iterator();
				Step tmpStep;
				while (travelIterator.hasNext()) {
					tmpStep = travelIterator.next();
					GeoPoint p = new GeoPoint((int) (tmpStep.getLocation()
							.getLatitude() * 1E6), (int) (tmpStep.getLocation()
							.getLongitude() * 1E6));
					OverlayItem overlayItem = new OverlayItem(p, "XXX", "YYY");
					stepItemizedOverlay.addOverlay(overlayItem);
				}
				mapOverlays.add(stepItemizedOverlay);
			} else {
				Toast.makeText(this, "Travel is empty.", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(this,
					"No travel started or selected.\nNo step to display.",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void setUserLocationOnMap() {
		getCooProgressDialog = ProgressDialog.show(this, "",
				"Looking for your position.\nPlease wait...", true, false);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		userLocation = new UserLocation();
		Looper.prepare();
		try {
			if (!userLocation.getUserLocation(this, locationResult)) {
				throw new NoCooException("Internal Error");
			}
		} catch (NoCooException e) {
			e.printStackTrace();
		}
	}

	private LocationResult locationResult = new LocationResult() {
		@Override
		public void gotLocation(final Location location) {
			if (location != null && !Double.isNaN(location.getLatitude())) {
				TravelReminder.currentTravel.addStep(location);
			} else {
				Toast.makeText(MapTabView.this,
						"Invalid coo received.\nNo step added.",
						Toast.LENGTH_SHORT).show();
			}
			// TODO controle sur accuracy
			getCooProgressDialog.dismiss();
			displayTravelOnMap(TravelReminder.currentTravel);
		}
	};

}
