package com.travelreminder.android22;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
//import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.travelreminder.android22.MyLocation.LocationResult;

public class AddStepScreen extends Activity {

	private MyLocation userLocation;
	
	private TextView mTxtViewlong;
	private TextView mTxtViewlat;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addstepscreen);
		mTxtViewlong = (TextView) findViewById(R.id.textlong);
		mTxtViewlat = (TextView) findViewById(R.id.textlat);
		Button b = (Button) findViewById(R.id.btnClick2);
	
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				setResult(RESULT_OK);
				finish();
			}
		});
		
		ProgressDialog dialog = ProgressDialog.show(this, "", 
                "Looking for your location...\nPlease wait.", true);
		
		userLocation = new MyLocation();
		getUserPosition();
		dialog.dismiss();
	}
	
	public LocationResult locationResult = new LocationResult() {
		@Override
		public void gotLocation(final Location location) {
			if (location != null) {
				mTxtViewlat.setText(" " + location.getLatitude());
				mTxtViewlong.setText(" " + location.getLongitude());
				TravelReminder.testTravel.addStep(location);
			} else {
				String txtToast = "No valid coo!";
				Toast toast = Toast.makeText(getApplicationContext(), txtToast,
						Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	};
	
	public void getUserPosition() {
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