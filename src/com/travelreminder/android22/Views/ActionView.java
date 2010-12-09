package com.travelreminder.android22.Views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.travelreminder.android22.R;
import com.travelreminder.android22.Travel;
import com.travelreminder.android22.TravelReminder;

public class ActionView extends Activity {

	private SharedPreferences mPrefs;
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useractionsscreen);
	}
	
	public void startTravelButtonAction(View view) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();

		if(!mPrefs.getBoolean("TR_STATE", false)) {
			ed.putBoolean("TR_STATE", true);
			ed.commit();
			
			String txtToast = "TR started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			
			// TODO : interface d'accès aux trajets save et y accéder
			
			//testTravel = new Travel();
		} else {
			ed.putBoolean("TR_STATE", false);
			ed.commit();
			
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}
		
		/*if (!TR_IS_RUNNING) {

		} else {

		}*/
		
	}
	
	public void exitButtonAction(View view) {
		/*TR_IS_RUNNING = false;
		testTravel = null;
		finish();*/
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", false);
		ed.commit();
		finish();
	}

	public void addStepButtonAction(View view) {
		/*if (!TR_IS_RUNNING) {
			String txtToast = "TR is not started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Intent i = new Intent(TravelReminder.this, AddStepScreen.class);
			startActivity(i);
		}*/
	}

	public void showTravelButtonAction(View view) {
		/*if (!TR_IS_RUNNING) {
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
		}*/
	}
	
}

