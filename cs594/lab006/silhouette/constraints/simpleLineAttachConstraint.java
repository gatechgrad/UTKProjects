package silhouette.constraints;

import silhouette.shapes.*;

/**
 * base class for the simple line attachment constraints
 */

abstract public class simpleLineAttachConstraint extends constraint {

    protected SilShape referenceObj = null;
    protected objectProperty referenceProperty = null;

    protected simpleLineAttachConstraint(Object obj) {
	if (obj instanceof SilShape)
	    referenceObj = (SilShape)obj;
	else if (obj instanceof objectProperty)
	    referenceProperty = (objectProperty)obj;
	else {
	    System.out.println("Wrong type of object passed to " + this + ": The argument");
	    System.out.println("must be either a SilShape or a property variable that returns a SilShape");
	}
    }
}
