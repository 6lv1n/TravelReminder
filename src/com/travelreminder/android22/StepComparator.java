package com.travelreminder.android22;

import java.util.Comparator;

public class StepComparator implements Comparator<Step> {

	public int compare(Step object1, Step object2) {
        final Step 	s0 		= object1;
        final Step 	s1 		= object2;
		/*final int	status 	= 0;
		
		if(s0.getLocation().getLatitude() == s1.getLocation().getLatitude())
			status = 1;
		
		if(s0.getLocation().getLongitude() == s1.getLocation().getLongitude())
			status = 1;
		
        if (s0.getTime().compareTo(s1.getTime()) == 0) 
			return 0;*/
        
        if(s0.getLocation().equals(s1.getLocation()))
        	return 1;
        
        return -1;
        
		//return status;
	}
	
}
/*
public int compare(Object obj1, Object obj2){
    return ((Comparable)obj2).compareTo(obj1);
  }
  public boolean equals(Object obj){
    return this.equals(obj);
  }
*/