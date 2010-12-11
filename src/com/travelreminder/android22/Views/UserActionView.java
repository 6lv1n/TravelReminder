package com.travelreminder.android22.Views;

import java.util.TreeSet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.travelreminder.android22.R;
import com.travelreminder.android22.Travel;
import com.travelreminder.android22.TravelReminder;
import com.travelreminder.android22.TravelReminderStorage;

public class UserActionView extends Activity {

	private SharedPreferences mPrefs;
	private String strStorageContent =  		
		"[{\"menu\": {\n" + 
			  "\"id\": \"file\",\n" + 
			  "\"value\": \"File\",\n" + 
			  "\"popup\": {\n" +
			    "\"menuitem\": [\n" +
			      "{\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" + 
			      "{\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
			      "{\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" + 
			    "]\n" +
			  "}\n" +
			"}}]\n";

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useractionview);
	}
	
	public void startStopNewTravelButtonAction(View view) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();

		if(!mPrefs.getBoolean("TR_STATE", false)) {
			ed.putBoolean("TR_STATE", true);
			ed.commit();
			
			String txtToast = "New travel started!";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
			
			TravelReminderStorage trStorage = new TravelReminderStorage("trStorage.json");
			trStorage.initStorage();
			trStorage.writeStorage(strStorageContent);
			TreeSet<Travel> testTravel = trStorage.readStorage();
			testTravel.size();
			
		} else {
			ed.putBoolean("TR_STATE", false);
			ed.commit();
			
			String txtToast = "Travel stopped.";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}

	}
	
	public void exitButtonAction(View view) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", false);
		ed.commit();
		finish();
	}

	/*
	 * FIXME
	 */
	
	public void addStepButtonAction(View view) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		if(mPrefs.getBoolean("TR_STATE", false)) {
			
			// FIXME TODO 
			// Trouver le moyen d'ouvrir l'écran de captures de coo gps
			
      //} 
			
			Intent i = new Intent(this, AddStepView.class);
			startActivity(i);
			
			
			
			
		} else {
			String txtToast = "No travel started.\nNo Step added.";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	/*
	public void addStepButtonAction(View view) {
		mPrefs = getSharedPreferences(TravelReminder.PREFS_NAME, 0);
		if(mPrefs.getBoolean("TR_STATE", false)) {
			Intent i = new Intent(UserActionView.this, AddStepView.class);
			startActivity(i);
		} else {
			String txtToast = "No travel started.\nNo Step added.";
			Toast toast = Toast.makeText(getApplicationContext(), txtToast,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}*/

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

