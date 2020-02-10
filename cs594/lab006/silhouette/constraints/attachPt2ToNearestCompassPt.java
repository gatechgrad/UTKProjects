package silhouette.constraints;

import java.awt.geom.*;

import silhouette.shapes.*;

/** 
 * Attaches the second endpoint to one of the eight compass points on
 * the attaching object. The reference object is used to determine which
 * compass point would look most pleasing. If the attach object is smaller
 * than or equal in size to the reference object, then the constraint tries 
 * to attach the endpoint to the corner of the attaching object unless the 
 * leading edge of the attaching object is near the center of the reference 
 * object.
 *
 * If the attach object is bigger than the reference object, then the
 * endpoint is attached to the corner of the attaching object if the
 * attaching object does not overlap the reference object; otherwise the
 * endpoint is attached to the center of the attaching object (the endpoint
 * is also attached to the center of the attaching object if the two objects
 * are about to overlap--moving the attach point just before the objects
 * overlap prevents the line between the two objects from ever being vertical
 * or horizontal, which looks ugly). 
 *
 * If the attaching object actually intersects the reference object, then
 * the line may be attached to the actual center of the attaching object,
 * rather than one of the eight compass points. 
 */

public class attachPt2ToNearestCompassPt extends attachPtToNearestCompassPt {

    /** 
     * In order to calculate where the line intersects the reference
     * object, we need to know the object from which the line originates
     **/
    public attachPt2ToNearestCompassPt(Object obj, Object fromObj) {
	super(obj, fromObj);
    }

    public void formula(constrainedObject self) {
	LineShape objToSet = (LineShape)self;
	int horizontalSide 
	    = getXPoint(attachObj.getWidth() <= referenceObj.getWidth());
	int verticalSide 
	    = getYPoint(attachObj.getHeight() <= referenceObj.getHeight());
	
	int compassPt = getCompassPt(horizontalSide, verticalSide);

	Point2D.Double attachPt = attachObj.getCompassCoordinates(compassPt);
	
	objToSet.setX2(attachPt.getX());
	objToSet.setY2(attachPt.getY());
    }
    
    public String toString () {
	return "constraint attachPt2ToNearestCompassPt";
    }
}
