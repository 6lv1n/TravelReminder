package com.travelreminder.android22;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocation_old {
	
	private Timer timer1;
	private LocationManager lm;

	private LocationResult locationResult;

	private Location userLocation;
	
	// add type location : gps ou réseau ?

	private boolean gps_enabled = false;
	private boolean network_enabled = false;

	public int LOCATION_DELAY = 7000;

	public void setUserLocation(Location userLocation) {
		this.userLocation = userLocation;
	}

	public Location getUserLocation() {
		return userLocation;
	}

	public boolean getLocation(Context context, LocationResult result) {
		locationResult = result;
		if (lm == null)
			lm = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);

		try {
			gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			network_enabled = lm
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!gps_enabled && !network_enabled)
			return false;

		if (gps_enabled) {
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
					locationListenerGps);
		}

		if (network_enabled) {
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
					locationListenerNetwork);
		}

		
		/*timer1 = new Timer();
		timer1.schedule(new GetLastLocation(), LOCATION_DELAY);
		if (new Double(userLocation.getLatitude()) > 0) {
			return true;
		}*/
		// TODO find leaving condition 
		// jouer sur le timer
		return false;

	}

	private LocationListener locationListenerGps = new LocationListener() {
		public void onLocationChanged(Location location) {
			//userLocation = new Location(location);
			//timer1.cancel();
			//locationResult.gotLocation(location);
			lm.removeUpdates(this);
			lm.removeUpdates(locationListenerNetwork);
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	private LocationListener locationListenerNetwork = new LocationListener() {
		public void onLocationChanged(Location location) {
			//userLocation = new Location(location);
			// timer1.cancel();
			//locationResult.gotLocation(location);
			lm.removeUpdates(this);
			lm.removeUpdates(locationListenerGps);
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	private class GetLastLocation extends TimerTask {
		@Override
		public void run() {
			// timer1.cancel();
			lm.removeUpdates(locationListenerGps);
			lm.removeUpdates(locationListenerNetwork);
			Location net_loc = null, gps_loc = null;
			if (gps_enabled)
				gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (network_enabled)
				net_loc = lm
						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (gps_loc != null && net_loc != null) {
				if (gps_loc.getTime() > net_loc.getTime()) {
					userLocation = new Location(gps_loc);
				} else {
					userLocation = new Location(net_loc);
					// locationResult.gotLocation(net_loc);
				}
				// locationFound = true;
				return;
			}
			if (gps_loc != null) {
				userLocation = new Location(gps_loc);
				// locationResult.gotLocation(gps_loc);
				return;
			}
			if (net_loc != null) {
				userLocation = new Location(net_loc);
				// locationResult.gotLocation(net_loc);
				return;
			}
			userLocation = null;
			return;
		}
	}

	public static abstract class LocationResult {
		public abstract void gotLocation(Location location);
	}
}
