package silhouette.constraints;

import silhouette.shapes.*;

/** 
 * attach Y1 to the bottom of the reference object. 
 */

public class attachY1ToBottom extends simpleLineAttachConstraint {

    /** 
     * Attach Y1 to the bottom of the reference object
     **/
    public attachY1ToBottom(SilShape obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setY1(referenceObj.getBottom());
    }

    public String toString () {
	return "constraint attachY1ToBottom";
    }

}

