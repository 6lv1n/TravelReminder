package com.travelreminder.android22;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.travelreminder.android22.MyLocation.LocationResult;
import com.travelreminder.android22.Exceptions.NoCooException;

public class AddStepScreen extends Activity {

	private MyLocation userLocation;
	private TextView mTxtViewlong;
	private TextView mTxtViewlat;
	protected ProgressDialog getCooProgressDialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addstepscreen);
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
			try {
				if (location != null) {
					mTxtViewlat.setText(" " + location.getLatitude());
					mTxtViewlong.setText(" " + location.getLongitude());
					TravelReminder.testTravel.addStep(location);
				} else
					throw new NoCooException(
							"Invalid location received. Aborting...");
			} catch (NoCooException ex) {
				Toast toast = Toast.makeText(getApplicationContext(),
						ex.getMessage(), Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	};

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
