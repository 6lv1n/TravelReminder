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
		
		Step tmpStep = new Step(locationResearched);
		
		while (travelIterator.hasNext()) {
			tmpStep = travelIterator.next(); 
			if (tmpStep.getLocation().equals(locationResearched))
				return tmpStep; 
		}
		
		/*while (travelIterator.hasNext()) {
			if(this.myTravel.comparator().compare(tmpStep, (Step)travelIterator.next()) > 0) 
				return (Step)travelIterator.next();
					
		}*/
		
		return null;
	}
	
	/*public Step getStep(Step stepResearched) {
		
		final Iterator<Step> travelIterator = this.myTravel.iterator();	
		
		while (travelIterator.hasNext()) {
			
			if(this.myTravel.comparator().compare(stepResearched, (Step)travelIterator.next()) > 0) 
				return (Step)travelIterator.next();
					
		}
		
		return null;
	}*/
	
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
