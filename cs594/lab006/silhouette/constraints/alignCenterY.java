package silhouette.constraints;

import silhouette.shapes.*;

/**
 * Center an object vertically with respect to a reference object. 
 **/

public class alignCenterY extends constraint {
    protected SilShape referenceObj;
    protected objectProperty referenceProperty;

    public alignCenterY(Object obj) {
	if (obj instanceof SilShape)
	    referenceObj = (SilShape)obj;
	else if (obj instanceof objectProperty)
	    referenceProperty = (objectProperty)obj;
	else {
	    System.out.println("Wrong type of object passed to " + this + ": The argument");
	    System.out.println("must be either a SilShape or a property variable that returns a SilShape");
	}
    }

    public void formula(constrainedObject self) {
	BoxShape objToSet = (BoxShape)self;
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();

	// if the reference object is the parent of this object, use
	// relative coordinates to center the objects
	if (objToSet.getParent() == referenceObj) {
	    objToSet.setCenterY(referenceObj.getHeight() / 2);
	}
	// if the reference object is not the parent of this object, use
	// absolute coordinates to center the objects
	else {
	    objToSet.setCenterY(referenceObj.getCenterY());
	}
    }
}
