package silhouette.shapes;

import java.awt.*;
import java.awt.geom.*;
import silhouette.properties.*;
import silhouette.interactors.*;
import java.util.Stack;

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

public interface SilShape {

  /**
   * Returns whether this Shape is visible.
   */ 
  public boolean getVisible();

  /**
   * Set the Shape's visibility
   */
  public SilShape setVisible(boolean value);

  /**
   *  Returns the line color of the Shape.
   */
  public Color getLineColor();

  /**
   *  Returns the fill color of the Shape.
   */
  public Color getFillColor();

  /**
   * Sets the line color of the Shape.
   */
  public SilShape setLineColor(Color color_in);

  /**
   * Sets the fill color of the Shape.
   */
  public SilShape setFillColor(Color color_in);

  /**
   * Returns the Filled variable.
   */
  public boolean getFilled();

  /**
   * Sets the Filled variable.
   */
  public SilShape setFilled(boolean fill);

  /**
   * Contains the algorithm for painting the Shape on the screen.
   */
  public abstract void    draw(Graphics2D g);

  /**
   * Checks if the given point is within the Shape.
   */
  public abstract boolean contains(double x, double y);

  /**
   * Returns the string name of the Shape.
   */
  public String getName();

  /**
   * Sets the string name of the Shape.
   */
  public SilShape setName(String name_in);

  /**
   * Returns the canvas to which this object is attached 
   */
  public CanvasShape getCanvas();

  /**
   * Gets a property value
   */
  public Object getValue(String nameIn);

  /**
   * Sets a property value. setValue allows the application to
   * dynamically add new properties to a Silhouette shape.
   */
  public SilShape setValue(String nameIn, Object valueIn);

  /**
   * Deletes a property name from the property list
   */
  public SilShape removeProperty(String nameIn);

  /**
   * Returns a copy of the Shape's bounding box.
   */
  public Rectangle getBounds();

  /**
   * Makes the indicated interactor operate on this object
   */
  public SilShape addInteractor(SilInteractor inter);

  /**
   * The indicate interactor will no longer operate on this object
   */
  public SilShape removeInteractor(SilInteractor inter);

  /**
   * find all the shapes in the composite object hierarchy that contain
   * the given (x,y); point. This default version of findShapes simply adds
   * the shape to the stack of shapes containing the point. An object's
   * findShapes method will be called only if it has already been ascertained
   * that the object contains the point.
   */
  public void findShapes(int x, int y, Stack foundShapes);

  /**
   * Returns a reference to the Shape's parent.
   */
  public SilShape getParent();

  /**
   * Sets the Shape's parent
   */
  public SilShape setParent(SilShape parent);

  /**
   * Marks the object as needing to be re-displayed and notifies the
   * object's canvas that the canvas needs to be repainted
   **/ 
  public void invalidate();

  /**
   * Returns the location of the objects' left side 
   */
  public double getLeft();

  /**
   * Returns the location of the object's top side
   */
  public double getTop();

  /**
   * Returns the object's width
   */
  public double getWidth();

  /**
   * Returns the object's height
   */
  public double getHeight();

  /**
   * Returns the object's x-center
   */
  public double getCenterX();

  /**
   * Returns the object's y-center
   */
  public double getCenterY();

  /**
   * Returns the object's right
   */
  public double getRight();

  /**
   * Returns the object's bottom
   */
  public double getBottom();

  /**
   * Pretend that a line is drawn between the centers
   * of this object and the from object. Return the intersection point 
   * between this object and the line.
   **/
  public Point2D.Double getIntersectPoint(SilShape fromObj);

  /**
   * Returns the coordinates associated with the given compass point
   **/
  public Point2D.Double getCompassCoordinates(int compassPoint);

  /**
   * Locates the leaf object in the composite object hierarchy that
   * is at the given x, y position.
   * @return A Shape if found, null otherwise.
   */
  public SilShape findLeaf(double x, double y);

  public void setObjToPresent(SilShape obj);

  public SilShape getObjToPresent();
}



