package com.travelreminder.android22;
//test mat
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.travelreminder.android22.MyLocation.LocationResult;

public class TravelReminder extends Activity {

	public static boolean TR_IS_RUNNING = false;

	private TextView 	mTxtViewlong;
	private TextView	mTxtViewlat;
	private TextView 	mTxtViewtrav;
	private Travel 		testTravel;
	private MyLocation 	myLocation;

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
		if(!TR_IS_RUNNING) {
			String txtToast = "TR started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast, Toast.LENGTH_SHORT);
			toast.show();
			testTravel = new Travel();
			myLocation = new MyLocation();
			TR_IS_RUNNING = true;
		} else {
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast, Toast.LENGTH_SHORT);
			toast.show();
			testTravel = null;
			TR_IS_RUNNING = false;
		}
	}

	public void addStepButtonAction(View view) {
		if(!TR_IS_RUNNING) {
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast, Toast.LENGTH_SHORT);
			toast.show();
		} else {
			getUserPosition();
		}
	}
	
	public void exitButtonAction(View view) {
		finish();
	}
	
	public void getUserPosition() {
		myLocation.getLocation(this, locationResult);
	}

	public LocationResult locationResult = new LocationResult() {
		@Override
		public void gotLocation(final Location location) {
			if(location != null) {
				testTravel.addStep(location);
				mTxtViewlat.setText(" "	+ location.getLatitude());
				mTxtViewlong.setText(" " + location.getLongitude());
				mTxtViewtrav.setText(" " + testTravel.getStep(location).toString());
			} else {
				mTxtViewlat.setText("Bordel !");
			}
		} 
	};
}
