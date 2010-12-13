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

public class UserActionView extends Activity {

	private SharedPreferences mPrefs;

	/*
	 * private String strStorageContent = "[{\"menu\": {\n" +
	 * "\"id\": \"file\",\n" + "\"value\": \"File\",\n" + "\"popup\": {\n" +
	 * "\"menuitem\": [\n" +
	 * "{\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
	 * "{\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
	 * "{\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" + "]\n" + "}\n" +
	 * "}}]\n";
	 */

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useractionview);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_NEW_TRAVEL", mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	};

	@Override
	protected void onStop() {
		super.onStop();
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_NEW_TRAVEL", mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	}

	public void startStopNewTravelButtonAction(View v) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		if (!mPrefs.getBoolean("TR_NEW_TRAVEL", false)) {
			ed.putBoolean("TR_NEW_TRAVEL", true);
			ed.commit();
			String txtToast = "New travel started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			TravelReminder.currentTravel = new Travel();
		} else {
			ed.putBoolean("TR_NEW_TRAVEL", false);
			ed.commit();
			String txtToast = "Travel stopped.";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			TravelReminder.currentTravel = null;
		}
	}

	public void addStepButtonAction(View v) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		if (mPrefs.getBoolean("TR_NEW_TRAVEL", false)) {
			Intent i = new Intent(this, AddStepView.class);
			startActivity(i);
		} else {
			String txtToast = "No travel started.\nNo Step added.";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	public void showTravelButtonAction(View v) {
		TravelReminder.getmTabHost().setCurrentTabByTag("tab_map");
	}
	
	public void exitButtonAction(View v) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_NEW_TRAVEL", false);
		ed.commit();
		TravelReminder.currentTravel = null;
		finish();
	}
}

/*
 * TravelReminderStorage trStorage = new
 * TravelReminderStorage("trStorage.json"); trStorage.initStorage();
 * trStorage.writeStorage(strStorageContent);
 */
// userTravels = trStorage.readStorage();