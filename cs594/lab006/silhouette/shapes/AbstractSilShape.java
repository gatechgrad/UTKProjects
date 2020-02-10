package silhouette.shapes;

import java.awt.*;
import java.awt.geom.*;
import silhouette.properties.*;
import silhouette.interactors.*;
import silhouette.constraints.*;
import silhouette.globals;
import java.util.Stack;
import silhouette.inspector.*;

/**
 * The abstract ancestor for all Silhouette shapes. All silhouette
 * shapes support the following properties:
 * <p>
 * <ol>
 * <li> visible: whether or not the shape is visible.
 * <li> parent: the composite object to which the shape belongs
 * <li> lineColor: the color used to draw the object's boundary
 * <li> fillColor: the color used to fill the object
 * <li> Filled: whether the fillColor is used to fill the object or whether
 *      the object is drawn as a transparent object
 * </ol>
 * <p>
 * The user can add additional properties using the setValue method and
 * retrieve these properties using the getValue method. Additional
 * properties are named using strings and their values are Java Objects.
 *
 */

public abstract class AbstractSilShape extends constrainedObject
implements SilShape {

  /**
   * Whether or not the SilShape will be drawn on the screen.
   */
  public  booleanProperty    visible = new booleanProperty("visible", true, this);

  /**
   * The SilShape containing the current SilShape.
   */
  protected SilShape       parent = null;

  /**
   * The line color.
   */
  public  colorProperty lineColor = new colorProperty("lineColor", Color.black, this);

  /**
   * The fill color.
   */
  protected colorProperty fillColor = new colorProperty("fillColor", Color.white, this);

  /**
   * Whether or not this SilShape will be filled.
   */
  protected booleanProperty Filled = new booleanProperty("Filled", true, this);

  /**
   * Whether or not the SilShape has been altered since it was last drawn.
   */
  protected boolean     valid = true;

  /**
   * A set of properties that a user can dynamically add to the object
   */
  protected PropertySet properties = new PropertySet();

  /**
   * A name that the user can assign to the object
   */
  private String      name = "";

  SilShape    objToPresent;

//  Inspector	myInspector;
  /**
   * Default constructor that does nothing
   */
  AbstractSilShape() {
	   
  }

  public void setObjToPresent(SilShape obj) {
    objToPresent = obj;
  }

  public SilShape getObjToPresent() {
    return objToPresent;
  }	

  /**
   * Returns whether this Shape is visible.
   */
  public boolean getVisible() {
    return  visible.getValue();
  }

  /**
   * Set the Shape's visibility
   */
  public SilShape setVisible(boolean value) {
    visible.setValue(this, value);
    return this;
  }

  /**
   *  Returns the line color of the Shape.
   */
  public Color getLineColor() {
    return (Color)lineColor.getValue();
  }

  /**
   *  Returns the fill color of the Shape.
   */
  public Color getFillColor() {
    return (Color)fillColor.getValue();
  }

  /**
   * Sets the line color of the Shape.
   */
  public SilShape setLineColor(Color color_in) {
    lineColor.setValue(this, color_in);
    return(this);
  }

  /**
   * Sets the fill color of the Shape.
   */
  public SilShape setFillColor(Color color_in) {
    fillColor.setValue(this, color_in);
    return(this);
  }

  /**
   * Returns a reference to the Shape's parent.
   */
  public SilShape getParent() {
    return(parent);
  }

  /**
   * Sets the Shape's parent
   */
  public SilShape setParent(SilShape newParent) {
    parent = newParent;
    return this;
  }
  /**
   * Returns the Filled variable.
   */
  public boolean getFilled() {
    return 	Filled.getValue();
  }

  /**
   * Sets the Filled variable.
   */
  public SilShape setFilled(boolean fill) {
    Filled.setValue(this, fill);
    return(this);
  }


  /* Weizhong */
  /*public SilProperty getVisibleProperty() {
    return visible;
    }

    public SilProperty getLineColorProperty() {
    return lineColor;
    }

    public SilProperty getFillColorProperty() {
    return fillColor;
    }

    public SilProperty getFilledProperty() {
    return Filled;
    }*/

  /**
   * return the canvas to which this object is attached 
   */
  public CanvasShape getCanvas () {
    SilShape parent = getParent();
    SilShape prevParent = null;

    while (parent != null) {
      prevParent = parent;
      parent = parent.getParent();
    }
    if (prevParent == null) 
      return null;
    else if (prevParent instanceof CanvasShape)
      return (CanvasShape)prevParent;
    else 
      return null;
  }

  /**
   * Contains the algorithm for painting the Shape on the screen.
   */
  public abstract void    draw(Graphics2D g);

  /**
   * Checks if the given point is within the Shape.
   */
  public abstract boolean contains(double x, double y);

  /**
   * Marks the Shape as having been altered since the last time it was drawn.
   * and notifies the shape's canvas to redraw itself
   */
  public void invalidate() {
    if (valid == true) {
      valid = false;

      // find the shape's canvas by chasing the parent chain to the composite
      // shape that belongs to the shape's canvas. We know when we've reached
      // the top of the chain since the top-level composite shape has a null
      // parent. Once we have the top-level composite shape, we can get the
      // canvas from the composite shape's display manager.
      SilShape prevParent = this;
      SilShape myParent = getParent();
      if (myParent != null)
	  myParent.invalidate();
    }
  }

  /**
   * Used to reset the Shape after it has been drawn.
   */
  public void validate() {
    valid = true;
  }

  /**
   * Returns the string name of the Shape.
   */
  public String getName() {
    if (name.equals(""))
   	return toString();
    else
        return(name);
  }

  /**
   * Sets the string name of the Shape.
   */
  public SilShape setName(String name_in) {
    name = name_in;
    return(this);
  }

  /**
   * Gets a property value
   */
  public Object getValue(String nameIn) {
    return properties.getValue(nameIn);
  }

  /**
   * Sets a property value. setValue allows the application to
   * dynamically add new properties to a Silhouette shape.
   */
  public SilShape setValue(String nameIn, Object valueIn) {
    properties.setValue(nameIn, valueIn);
    return this;
  }

  /**
   * Deletes a property name from the property list
   */
  public SilShape removeProperty(String nameIn) {
    properties.removeProperty(nameIn);
    return this;
  }

  /**
   * Returns a copy of the Shape's bounding box.
   */
  public abstract Rectangle getBounds();

  /**
   * Makes the indicated interactor operate on this object
   */
  public SilShape addInteractor(SilInteractor inter) {
    SilEventHandler.addInteractor(this, inter);
    return this;
  }

  /**
   * The indicate interactor will no longer operate on this object
   */
  public SilShape removeInteractor(SilInteractor inter) {
    SilEventHandler.removeInteractor(this, inter);
    return this;
  }


  /**
   * Locates the Shape at the given x, y position.
   * @return A Shape if found, null otherwise. 
   * For leaf objects, we already know that the object
   * contains the given (x,y) point so just return the object.
   * This is the default case.
   */
  public SilShape find(double x, double y) {
    return this;
  }

  /**
   * find all the shapes in the composite object hierarchy that contain
   * the given (x,y) point. This default version of findShapes simply adds
   * the shape to the stack of shapes containing the point. An object's
   * findShapes method will be called only if it has already been ascertained
   * that the object contains the point.
   */
  public void findShapes(int x, int y, Stack foundShapes) {
    foundShapes.push(this);
  }

  /**
   * Returns the object's x-center
   */
  public double getCenterX() {
    return getLeft() + getWidth() / 2;
  }

  /**
   * Returns the object's y-center
   */
  public double getCenterY() {
    return getTop() + getHeight() / 2;
  }

  /**
   * Returns the object's right
   */
  public double getRight() {
    return getLeft() + getWidth();
  }

  /**
   * Returns the object's bottom
   */
  public double getBottom() {
    return getTop() + getHeight();
  }

  /**
   * Pretend that a line is drawn between the centers
   * of this object and the from object. Return the intersection point 
   * between this object and the line.
   **/
  abstract public Point2D.Double getIntersectPoint(SilShape fromObj);

  /**
   * Returns the coordinates associated with the given compass point
   **/
  public Point2D.Double getCompassCoordinates(int compassPoint) {
    switch (compassPoint) {
      case globals.NorthWest: 
        return new Point2D.Double(getLeft(), getTop());
      case globals.North:
        return new Point2D.Double(getCenterX(), getTop());
      case globals.NorthEast: 
        return new Point2D.Double(getRight(), getTop());
      case globals.West: 
        return new Point2D.Double(getLeft(), getCenterY());
      case globals.Center: 
        return new Point2D.Double(getCenterX(), getCenterY());
      case globals.East: 
        return new Point2D.Double(getRight(), getCenterY());
      case globals.SouthWest: 
        return new Point2D.Double(getLeft(), getBottom());
      case globals.South: 
        return new Point2D.Double(getCenterX(), getBottom());
      case globals.SouthEast: 
        return new Point2D.Double(getRight(), getBottom());
      default:
        return new Point2D.Double(0,0);
    }
  }

  public SilShape findLeaf(double x, double y) {
    return this;
  }
}
