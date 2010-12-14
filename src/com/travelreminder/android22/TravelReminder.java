package com.travelreminder.android22;

import java.util.ArrayList;
import java.util.TreeSet;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.travelreminder.android22.Views.CreditsView;
import com.travelreminder.android22.Views.MapTabView;
import com.travelreminder.android22.Views.UserActionView;

public class TravelReminder extends TabActivity {

	public static final String PREFS_NAME = "MyPrefsFile";
	public SharedPreferences mPrefs;
	public static TabHost mTabHost;
	public static TreeSet<Travel> userTravels;
	public static Travel currentTravel;
	public static ArrayList<TabSpec> tabSpecList;	

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Context ctx = this.getApplicationContext();
		
		mTabHost = getTabHost();
		tabSpecList = new ArrayList<TabSpec>();

		TabSpec tabSpec = mTabHost.newTabSpec("tab_actions");
		tabSpec.setIndicator("Actions");
		Intent i = new Intent(ctx, UserActionView.class);
		tabSpec.setContent(i);
		mTabHost.addTab(tabSpec);
		tabSpecList.add(tabSpec);

		tabSpec = mTabHost.newTabSpec("tab_map");
		tabSpec.setIndicator("Map");
		Bundle mapParams = new Bundle();
		mapParams.putBoolean("STEP_MODE", false);
		i = new Intent(ctx, MapTabView.class);
		i.putExtra("EXTRA_PARAMS",  mapParams);
		tabSpec.setContent(i);
		mTabHost.addTab(tabSpec);
		tabSpecList.add(tabSpec);
		
		/* FIXME mettre les credits dans un menu */
		tabSpec = mTabHost.newTabSpec("tab_credits");
		tabSpec.setIndicator("Credits");
		ctx = this.getApplicationContext();
		i = new Intent(ctx, CreditsView.class);
		tabSpec.setContent(i);
		mTabHost.addTab(tabSpec);
		tabSpecList.add(tabSpec);

		/*
		 * tabSpec = mTabHost.newTabSpec("tab_settings");
		 * tabSpec.setIndicator("Settings"); ctx = this.getApplicationContext();
		 * i = new Intent(ctx, Settings.class); tabSpec.setContent(i);
		 */

		/*
		 * mTabHost.addTab(mTabHost.newTabSpec("tab_credits")
		 * .setIndicator("Credits").setContent(R.id.textview2));
		 */

		mTabHost.setCurrentTab(0);

		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_NEW_TRAVEL", false);
		ed.putBoolean("STEP_MODE", false);
		ed.commit();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_NEW_TRAVEL",
				mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putBoolean("TR_NEW_TRAVEL",
				mPrefs.getBoolean("TR_NEW_TRAVEL", false));
		ed.commit();
	}

}
