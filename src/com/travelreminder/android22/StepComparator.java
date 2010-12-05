package com.travelreminder.android22;

import java.util.Comparator;
import android.text.format.Time;

import android.text.format.Time;

public class StepComparator implements Comparator<Step> {
	public int compare(Step object1, Step object2) {
<<<<<<< HEAD
        final Step 	s0 		= object1;
        final Step 	s1 		= object2;
        return Time.compare(s0.getTime(), s1.getTime());
=======
        final 	Step 	s0	=	object1;
        final 	Step 	s1	=	object2;
        return 	Time.compare(s0.getTime(), s1.getTime());
>>>>>>> e0a22aa3d59fef9ab6dca21f70024b82800ec542
	}
}
