package silhouette.constraints;

import silhouette.shapes.*;

/**
 * Align the left side of this object after the right side of the
 * reference object
 **/

public class alignUnder extends constraint {
    protected SilShape referenceObj;
    protected objectProperty referenceProperty;
    protected int displacement;

    void initializeConstraint(Object obj, int offset) {
	if (obj instanceof SilShape)
	    referenceObj = (SilShape)obj;
	else if (obj instanceof objectProperty)
	    referenceProperty = (objectProperty)obj;
	else {
	    System.out.println("Wrong type of object passed to " + this + ": The argument");
	    System.out.println("must be either a SilShape or a property variable that returns a SilShape");
	}
	displacement = offset;
    }

    /**
     * Make the left side line up with the right--there is no offset
     **/
    public alignUnder(SilShape obj) {
	initializeConstraint(obj, 0);
    }

    /**
     * Make the left side be offset pixels to the right of the reference
     * object
     **/
    public alignUnder(SilShape obj, int offset) {
	initializeConstraint(obj, offset);
    }

    public void formula(constrainedObject self) {
	BoxShape objToSet = (BoxShape)self;
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	objToSet.setTop(referenceObj.getBottom() + displacement);
    }
}
