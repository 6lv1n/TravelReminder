package com.travelreminder.android22;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TravelReminder extends Activity {

	public static Travel testTravel;
	public static boolean TR_IS_RUNNING;
	public static final String PREFS_NAME = "MyPrefsFile";

	private SharedPreferences mPrefs;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPrefs = getSharedPreferences(PREFS_NAME,0);
        TR_IS_RUNNING = mPrefs.getBoolean("TR_STATE", false);
		setContentView(R.layout.main);
		ToggleButton StartStopButton = (ToggleButton) findViewById(R.id.id_button_start_stop);
		StartStopButton.setChecked(TR_IS_RUNNING);
	}

	@Override
	protected void onPause(){
		super.onPause();
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
        ed.putBoolean("TR_STATE", TR_IS_RUNNING);
        ed.commit();
	};
	
	protected void onStop(){
		super.onStop();
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
        ed.putBoolean("TR_STATE", TR_IS_RUNNING);
        ed.commit();
	}
	
	public void startTravelButtonAction(View view) {
		if (!TR_IS_RUNNING) {
			String txtToast = "TR started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			testTravel = new Travel();
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
		finish();
	}

	public void addStepButtonAction(View view) {
		if (!TR_IS_RUNNING) {
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Intent i = new Intent(TravelReminder.this, AddStepScreen.class);
			startActivity(i);
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
			Intent i = new Intent(TravelReminder.this, ShowTravelScreen.class);
			startActivity(i);
		} else {
			String txtToast = "Travel is empty!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
