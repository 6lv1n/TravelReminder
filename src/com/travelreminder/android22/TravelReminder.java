package com.travelreminder.android22;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.travelreminder.android22.Screens.AddStepScreen;
import com.travelreminder.android22.Screens.ShowTravelScreen;

public class TravelReminder extends Activity {

	public static Travel testTravel;
	public static boolean TR_IS_RUNNING = false;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
