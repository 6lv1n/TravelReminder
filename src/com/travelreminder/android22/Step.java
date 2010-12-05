package com.travelreminder.android22;

import android.location.Location;
import android.text.format.Time;

public class Step {

	private	Location	location;
	private Time		time;

	public Step(Location location) {
		//super();
		this.location = location;
		this.time = new Time();
		this.time.setToNow();
	}


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTime() {
		return time.toString();
	}
	
	public String toString() {		
		return (this.location.getLatitude() + " " + this.location.getLongitude());
	}
}
