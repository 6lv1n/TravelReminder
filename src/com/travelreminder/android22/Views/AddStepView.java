package com.travelreminder.android22.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.travelreminder.android22.R;
import com.travelreminder.android22.TravelReminder;
import com.travelreminder.android22.UserLocation;
import com.travelreminder.android22.UserLocation.LocationResult;
import com.travelreminder.android22.Exceptions.NoCooException;

public class AddStepView extends Activity implements Runnable {

	private Location userLocationFound;
	private UserLocation userLocation;
	private TextView mTxtViewlong;
	private TextView mTxtViewlat;
	protected ProgressDialog getCooProgressDialog;
	private SharedPreferences mPrefs;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addstepview);
		mTxtViewlong = (TextView) findViewById(R.id.textlong);
		mTxtViewlat = (TextView) findViewById(R.id.textlat);

		Button b = (Button) findViewById(R.id.btnclose);

		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				setResult(RESULT_OK);
				finish();
			}
		});

		getCooProgressDialog = ProgressDialog.show(this, "",
				"Looking for your position.\nPlease wait...", true, false);

		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void onPause() {
		super.onPause();
		userLocation.timer1.cancel();
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", mPrefs.getBoolean("TR_STATE", false));
		ed.putBoolean("TR_NEW_TRAVEL", mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	};

	@Override
	public void onStop() {
		super.onStop();
		userLocation.timer1.cancel();
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", mPrefs.getBoolean("TR_STATE", false));
		ed.putBoolean("TR_NEW_TRAVEL", mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	}

	private LocationResult locationResult = new LocationResult() {
		@Override
		public void gotLocation(final Location location) {
			if (location != null && !Double.isNaN(location.getLatitude())) {
				userLocationFound = new Location(location);
			} else
				userLocationFound = null;
			TravelReminder.currentTravel.addStep(location);
			getCooProgressDialog.dismiss();
			fillTexteViewWithCoo();
		}
	};

	@Override
	public void run() {
		userLocation = new UserLocation();
		Looper.prepare();
		while(userLocationFound == null) {
			try {
				if (!userLocation.getUserLocation(this, locationResult)) {
					throw new NoCooException("Internal Error");
				}
			} catch (NoCooException e) {
				e.printStackTrace();
			}
		}
	}

	public void fillTexteViewWithCoo() {
		if (userLocationFound != null) {
			mTxtViewlat.getHandler().post(new Runnable() {
				public void run() {
					mTxtViewlat.setText(" " + userLocationFound.getLatitude());
				}
			});
			mTxtViewlong.getHandler().post(new Runnable() {
				public void run() {
					mTxtViewlong.setText(" " + userLocationFound.getLongitude());
				}
			});
		} else {
			mTxtViewlat.getHandler().post(new Runnable() {
				public void run() {
					mTxtViewlat.setText("No latitude found in time");
				}
			});
			mTxtViewlong.getHandler().post(new Runnable() {
				public void run() {
					mTxtViewlong.setText("No longitude found in time");
				}
			});
		}
	}
}
