package silhouette.constraints;

import java.awt.geom.*;
import silhouette.shapes.*;

/**
 * attach the second endpoint of the line to the point where the line
 * intersects the attaching object,
 * assuming that the line extend from the center of the reference object
 * to the center of the attaching object
 */

public class attachPt2ToClippedCenterPt extends constraint {

    protected SilShape attachObj;
    protected SilShape referenceObj;

    /**
     * In order to calculate where the line intersects the reference
     * object, we need to know the object from which the line originates
     **/
    public attachPt2ToClippedCenterPt(Object obj, Object fromObj) {
	attachObj = (SilShape)obj;
	referenceObj = (SilShape)fromObj;
    }

    public void formula(constrainedObject self) {
	Point2D.Double intersectPt
	    = attachObj.getIntersectPoint(referenceObj);
	((LineShape)self).setX2(intersectPt.getX());
	((LineShape)self).setY2(intersectPt.getY());
    }

    public String toString () {
	return "constraint attachPt2ToClippedCenterPt";
    }

}
