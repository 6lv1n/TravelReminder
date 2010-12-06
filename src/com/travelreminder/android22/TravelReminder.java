package com.travelreminder.android22;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.travelreminder.android22.MyLocation.LocationResult;

public class TravelReminder extends Activity {

	public static boolean TR_IS_RUNNING = false;

	private TextView mTxtViewlong;
	private TextView mTxtViewlat;
	private TextView mTxtViewtrav;
	private Travel testTravel;
	private MyLocation userLocation;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mTxtViewlong = (TextView) findViewById(R.id.textlong);
		mTxtViewlat = (TextView) findViewById(R.id.textlat);
		mTxtViewtrav = (TextView) findViewById(R.id.texttrav);
	}

	public void startTravelButtonAction(View view) {
		if (!TR_IS_RUNNING) {
			String txtToast = "TR started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			testTravel = new Travel();
			userLocation = new MyLocation();
			TR_IS_RUNNING = true;
		} else {
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			testTravel = null;
			TR_IS_RUNNING = false;
		}
	}

	public void exitButtonAction(View view) {
		TR_IS_RUNNING = false;
		testTravel = null;
		userLocation = null;
		finish();
	}

	public void addStepButtonAction(View view) {
		if (!TR_IS_RUNNING) {
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			String txtToast = "Add step!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			Intent i = new Intent(TravelReminder.this, AddStepScreen.class);
			startActivity(i);
			
			//getUserPosition();
		}
	}

	public void showTravelButtonAction(View view) {
		if (!TR_IS_RUNNING) {
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		if (testTravel.getTravel().size() > 0) {
			String txtToast = "Show travel: " + testTravel.getTravel().size() + " étapes";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			printUserTravel();
		} else {
			String txtToast = "Travel is empty!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	public void printUserTravel() {
		mTxtViewtrav.setText(testTravel.toString());
	}

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

	public LocationResult locationResult = new LocationResult() {
		@Override
		public void gotLocation(final Location location) {
			if (location != null)
				testTravel.addStep(location);
			else {
				String txtToast = "No valid coo!";
				Toast toast = Toast.makeText(getApplicationContext(), txtToast,
						Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	};
}
