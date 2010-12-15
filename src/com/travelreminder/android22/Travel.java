/**
 * 
 */
package com.travelreminder.android22;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import android.location.Location;

public class Travel {
	
	private TreeSet<Step> myTravel;
	static final Comparator<Step> STEP_ORDER = new StepComparator();
	private static final long serialVersionUID = -4248127581150201874L;
	
	public Travel() {
		this.myTravel = new TreeSet<Step>(STEP_ORDER);
	}

	public TreeSet<Step> clone(Travel travelToClone) {
		try {
			return (TreeSet<Step>) travelToClone.getTravel().clone();
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addStep(Location newLocation) {
		this.myTravel.add(new Step(newLocation)); 
	}
	
	/*public void addStep(Step newStep) {
		this.myTravel.add(newStep);
	}*/
	
	public void delStep(Step lazzyStep) {
		this.myTravel.remove(lazzyStep); 
	}

	public Step getStep(Location locationResearched) {	
		final Iterator<Step> travelIterator = this.myTravel.iterator();
		Step tmpStep;
		while (travelIterator.hasNext()) {
			tmpStep = travelIterator.next(); 
			if (tmpStep.getLocation().equals(locationResearched))
				return tmpStep; 
		}
		return null;
	}
	
	public boolean hasStep(Step stepResearched) {
		final Iterator<Step> travelIterator = this.myTravel.iterator();	
		Step tmpStep;
		while (travelIterator.hasNext()) {
			tmpStep = travelIterator.next(); 
			if(tmpStep.equals(stepResearched)) 
				return true;
		}
		return false;
	}
	
	public TreeSet<Step> getTravel() {
		return myTravel;
	}

	public void setTravel(TreeSet<Step> newTravel) {
		this.myTravel = newTravel;
	}
	
	public String toString() {
		final Iterator<Step> travelIterator = this.myTravel.iterator();
		String tmpTravelStr = "";
		while (travelIterator.hasNext()) {			
			tmpTravelStr = tmpTravelStr + travelIterator.next().toString() + " // ";
		}
		return tmpTravelStr;
	}
	
}
