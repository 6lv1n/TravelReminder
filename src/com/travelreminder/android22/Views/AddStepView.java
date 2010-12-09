package com.travelreminder.android22.Views;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.Overlay;
import com.travelreminder.android22.MyLocation;
import com.travelreminder.android22.MyLocation.LocationResult;
import com.travelreminder.android22.R;
import com.travelreminder.android22.StepItemizedOverlay;
import com.travelreminder.android22.TravelReminder;

public class AddStepView extends Activity {

	private MyLocation userLocation;
	private TextView mTxtViewlong;
	private TextView mTxtViewlat;
	protected ProgressDialog getCooProgressDialog;

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
		getUserPosition();
	}

	public LocationResult locationResult = new LocationResult() {
		@Override
		public void gotLocation(final Location location) {
			if (location != null) {
				mTxtViewlat.getHandler().post(new Runnable() {
					public void run() {
						mTxtViewlat.setText(" " + location.getLatitude());
					}
				});
				mTxtViewlong.getHandler().post(new Runnable() {
					public void run() {
						mTxtViewlong.setText(" " + location.getLongitude());
					}
				});
				TravelReminder.testTravel.addStep(location);
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
	};

	protected void onStop(){
		super.onStop();
		userLocation.timer1.cancel();
	}
	
	public void getUserPosition() {
		userLocation = new MyLocation();
		if (!userLocation.getLocation(this, locationResult)) {
			String txtToast = "Error";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}
		/*
		 * { throw new NoCooException("No Coo :X"); } } catch (NoCooException
		 * ex) { String txtToast = ex.getMessage(); Toast toast =
		 * Toast.makeText(getApplicationContext(), txtToast,
		 * Toast.LENGTH_SHORT); toast.show(); }
		 */
	}

}

/*
 * dialog = ProgressDialog.show(this, "",
 * "Looking for your location...\nPlease wait.", true); dialog.dismiss();
 */