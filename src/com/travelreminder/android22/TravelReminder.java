package com.travelreminder.android22;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.travelreminder.android22.Views.UserActionView;
import com.travelreminder.android22.Views.MapTabView;

public class TravelReminder extends TabActivity {

	public static Travel testTravel;
	public static boolean TR_IS_RUNNING;
	public static final String PREFS_NAME = "MyPrefsFile";

	private SharedPreferences mPrefs;
	private TabHost mTabHost;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPrefs = getSharedPreferences(PREFS_NAME, 0);

		// FIXME
		TR_IS_RUNNING = (mPrefs.getBoolean("TR_STATE", false) && testTravel != null);

		setContentView(R.layout.main);


		mTabHost = getTabHost();
		TabSpec tabSpec = mTabHost.newTabSpec("tab_actions");
		tabSpec.setIndicator("Actions");
		Context ctx = this.getApplicationContext();
		Intent i = new Intent(ctx, UserActionView.class);
		tabSpec.setContent(i);
		
		mTabHost.addTab(tabSpec);

		tabSpec = mTabHost.newTabSpec("tab_map");
		tabSpec.setIndicator("Map");
		ctx = this.getApplicationContext();
		i = new Intent(ctx, MapTabView.class);
		tabSpec.setContent(i);
		
		mTabHost.addTab(tabSpec);

		/*
		 * tabSpec = mTabHost.newTabSpec("tab_settings");
		 * tabSpec.setIndicator("Settings"); ctx = this.getApplicationContext();
		 * i = new Intent(ctx, Settings.class); tabSpec.setContent(i);
		 */

		mTabHost.addTab(mTabHost.newTabSpec("tab_credits")
				.setIndicator("Credits").setContent(R.id.textview2));
		
		mTabHost.setCurrentTab(0);

	}

	@Override
	protected void onPause() {
		super.onPause();
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", TR_IS_RUNNING);
		ed.commit();
	};

	protected void onStop() {
		super.onStop();
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_STATE", TR_IS_RUNNING);
		ed.commit();
	}

}
