package silhouette.constraints;

import silhouette.shapes.*;

/**
 * Bottom align an object with respect to a reference object. 
 **/

public class alignBottom extends constraint {
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
     * Make the left sides line up--there is no offset
     **/
    public alignBottom(Object obj) {
	initializeConstraint(obj, 0);
    }

    /**
     * Make the left sides differ by the amount of the offset
     **/
    public alignBottom(Object obj, int offset) {
	initializeConstraint(obj, offset);
    }

    public void formula(constrainedObject self) {
	BoxShape objToSet = (BoxShape)self;
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();

	// if the reference object is the parent of this object, use
	// relative coordinates to align the objects
	if (objToSet.getParent() == referenceObj) {
	    objToSet.setBottom(referenceObj.getHeight() + displacement);
	}
	// if the reference object is not the parent of this object, use
	// absolute coordinates to align the objects
	else {
	    objToSet.setBottom(referenceObj.getBottom() + displacement);
	}
    }
}
