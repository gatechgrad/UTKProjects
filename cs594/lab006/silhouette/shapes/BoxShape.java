package silhouette.shapes;

import java.awt.*;
import java.awt.geom.*;
import silhouette.constraints.*;
import java.util.*;

/**
 * The base class for rectangular shapes, like roundtangles, ellipses,
 * rectangles, composite objects and text objects. It adds the following
 * properties to a Silhouette Shape:
 * <ol>
 * <li> left: The left side of the object.
 * <li> top: The top side of the object.
 * <li> width: The width of the object.
 * <li> height: The height of the object.
 * </ol>
 *
 */
public abstract class BoxShape extends AbstractSilShape {

  /**
   * working storage for checking whether a point lies within the object
   */
  protected static Rectangle2D bounds = new Rectangle2D.Double();

  /**
   * Left side of the rectangular shape
   */
  public doubleProperty left = new doubleProperty("left", 0.0, this);

  /**
   * Top side of the rectangular shape
   */
  public doubleProperty top = new doubleProperty("top", 0.0, this);

  /**
   * Width of the rectangular shape
   */
  public doubleProperty width = new doubleProperty("width", 20.0, this);

  /**
   * Height of the rectangular shape
   */
  public doubleProperty height = new doubleProperty("height", 20.0, this);

  /* Weizhong: access to property */
/*  public SilProperty getLeftProperty() {
    return left;
  }
  public SilProperty getTopProperty() {
    return top;
  }
  public SilProperty getWidthProperty() {
    return width;
  }
  public SilProperty getHeightProperty() {
    return height;
  }
*/

  /**
   * Returns the location of the left side of the rectangular shape
   */
  public double getLeft() {
    return left.getValue ();
  }

  /**
   * Returns the location of the top of the rectangular shape
   */
  public double getTop() {
    return top.getValue();
  }

  /**
   * Returns the width of the rectangular shape
   */
  public double getWidth() {
    return width.getValue();
  }

  /**
   * Returns the height of the rectangular shape
   */
  public double getHeight() {
    return height.getValue();
  }

  /**
   * Sets the left side of the rectangular shape
   */
  public SilShape setLeft(double newLeft) {
    left.setValue(this, newLeft);
    return this;
  }

  /**
   * Sets the top of the rectangular shape
   */
  public SilShape setTop(double newTop) {
    top.setValue(this, newTop);
    return this;
  }

    /**
     * Sets the object's x-center
     */
    public SilShape setCenterX(double newCenterX) {
	setLeft(newCenterX - getWidth() / 2);
	return this;
    }

    /**
     * Sets the object's y-center
     */
    public SilShape setCenterY(double newCenterY) {
	setTop(newCenterY - getHeight() / 2);
	return this;
    }

    /**
     * Sets the object's right
     */
    public SilShape setRight(double newRight) {
	setLeft(newRight - getWidth());
	return this;
    }

    /**
     * Sets the object's bottom
     */
    public SilShape setBottom(double newBottom) {
	setTop(newBottom - getHeight());
	return this;
    }

  /**
   * Sets the width of the rectangular shape
   */
  public SilShape setWidth(double newWidth) {
    width.setValue(this, newWidth);
    return this;
  }

  /**
   * Sets the height of the rectangular shape
   */
  public SilShape setHeight(double newHeight) {
    height.setValue(this, newHeight);
    return this;
  }


  /**
   * Returns the bounding box that encloses this rectangular shape
   */
  public Rectangle getBounds () {
    return new Rectangle((int)getLeft(), (int)getTop(),
			 (int)getWidth() + 1, (int)getHeight() + 1);
  }

  /**
   * Checks whether or not this rectangular shape contains the point
   * at the specified (x, y) location
   */
  public boolean contains(double x, double y) {
    bounds.setFrame(getLeft(), getTop(),
				     getWidth()+1, getHeight()+1);
    return( bounds.contains(x, y) );
  }

  /* a cheating method for debugging */
  public LinkedList getProperties() {
  	LinkedList propertyList = new LinkedList();
	propertyList.add(this.left);
	propertyList.add(this.top);
	propertyList.add(this.width);
	propertyList.add(this.height);
	propertyList.add(this.visible);
	propertyList.add(this.lineColor);
	propertyList.add(this.fillColor);
	propertyList.add(this.Filled);

	return propertyList;
 }

    /**
     * Pretend that a line is drawn between the centers
     * of this object and the from object. Return the intersection point 
     * between this object and the line.
     **/
  public Point2D.Double getIntersectPoint(SilShape fromObj) {
	
    final int LeftSide   = 101; 
    final int TopSide    = 102;
    final int RightSide  = 103;
    final int BottomSide = 104;

    double x1, y1, wd, ht;
    double ptX, ptY;

    double fromX = fromObj.getCenterX();
    double fromY = fromObj.getCenterY();

    Point2D.Double desiredPt = new Point2D.Double(0,0);   // the point we seek

    x1 = this.getLeft();
    y1 = this.getTop();
    wd = this.getWidth();
    ht = this.getHeight();

    double CenterX = this.getCenterX();
    double CenterY = this.getCenterY();

    // decides the position of the point
    int ptPos;
    double slope = (fromY-CenterY)/(fromX-CenterX);

    if (fromY < y1) {
      // Upper
      if (fromX < x1) {
	  double ts = (y1-CenterY)/(x1-CenterX);
	if (slope >= ts)       ptPos = TopSide;  
	else                   ptPos = LeftSide;
      } 
      else if (fromX < (x1+wd))  ptPos = TopSide;
      else {
	double ts = (y1-CenterY)/(x1+wd-CenterX);
	if (slope >= ts)       ptPos = RightSide;
	else                   ptPos = TopSide;
      }
    } else if (fromY < (y1+ht)) {
      if (fromX < x1)            ptPos = LeftSide;
      else                     ptPos = RightSide;
    } else if (fromX < x1) {
      double ts = (y1+ht-CenterY)/(x1-CenterX);
      if (slope >= ts)         ptPos = LeftSide;
      else 	               ptPos = BottomSide;
    } else if (fromX < (x1+wd))  ptPos = BottomSide;
    else {
      double ts = (y1+ht-CenterY)/(x1+wd-CenterX);
      if (slope <= ts)         ptPos = RightSide;
      else                     ptPos = BottomSide;
    }

    switch (ptPos) {
      case LeftSide:
	desiredPt = 
	  new Point2D.Double(x1, CenterY-slope*0.5*wd);
	break;
      case TopSide:
	desiredPt = 
	  new Point2D.Double(CenterX-ht/(2*slope), y1);
	break;
      case RightSide:
	desiredPt = 
	  new Point2D.Double(x1+wd, CenterY+wd*(0.5*slope) );
	break;
      case BottomSide:
	desiredPt = 
	  new Point2D.Double(CenterX+ht/(2*slope), y1+ht);
	break;
    }
    return desiredPt;
  }  // end of getIntersectPoint()

}
