package silhouette.shapes;

import java.awt.*;
import java.awt.geom.*;
import silhouette.constraints.*;

import java.util.Vector;

/**
 * A line that is drawn on the screen. In addition to the properties of
 * a normal Silhouette shape, a line also has the following properties:
 * <p>
 * <ol>
 * <li> x1, y1: The first endpoint
 * <li> x2, y2: The second endpoint
 * </ol>
 */
public class LineShape extends AbstractSilShape {

  /**
   * default threshold amount for how close a point must come to a line
   * before it is considered to lie on that line
   */
  static final int hitThreshold = 3;

  /**
   * working storage for checking whether a point lies on the line
   */
  protected static Line2D bounds = new Line2D.Double();

  /**
   * The first point.
   */
  protected doubleProperty x1 = new doubleProperty("x1", 0.0, this);
  protected doubleProperty y1 = new doubleProperty("y1", 0.0, this);

  /**
   * The second point.
   */
  protected doubleProperty x2 = new doubleProperty("x2", 20.0, this);
  protected doubleProperty y2 = new doubleProperty("y2", 20.0, this);

  /* Weizhong */
  /*  public SilProperty getX1Property() {
      return x1;
      }

      public SilProperty getY1Property() {
      return y1;
      }

      public SilProperty getX2Property() {
      return x2;
      }

      public SilProperty getY2Property() {
      return y2;
      }*/

  /**
   * Creates a default line shape
   */
  public LineShape() {
  }

  /**
   * Constructs a new LineShape with the specified parameters.
   * @param x1_in 	the x1 value
   * @param y1_in	the y1 value
   * @param x2_in 	the x2 value
   * @param y2_in	the y2 value
   */
  public LineShape(double x1_in, double y1_in, double x2_in, double y2_in) {
    setX1(x1_in);
    setY1(y1_in);
    setX2(x2_in);
    setY2(y2_in);
  }

  /**
   * Draws a line shape to the screen. This method should never be called
   * directly by an application. To get a line shape to appear on the
   * screen, either add it to a Silhouette canvas or to a composite shape
   * that has been added to a Silhouette canvas.
   */
  public void draw(Graphics2D g) {
    if(getVisible()) {
      g.setColor(getLineColor());
      g.drawLine((int)getX1(), (int)getY1(), (int)getX2(), (int)getY2());
      validate();
    }
  }

  /**
   * Checks whether or not the point lies within a threshold distance
   * from the line
   */
  public boolean contains(double x, double y) {
    bounds.setLine(getX1(), getY1(), getX2(), getY2());
    return (bounds.ptSegDist(x, y) <= hitThreshold);
  }

  /**
   *  Returns the length of the line.
   */
  /*
     public int getLength() {
     return( Geometry.calculateDistance(x1, y1, x2, y2) );
     }
   */
  /**
   * Returns the x-coordinate of the first endpoint of the line
   */
  public double getX1() {
    return x1.getValue();
  }

  /**
   * Returns the y-coordinate of the first endpoint of the line
   */
  public double getY1() {
    return y1.getValue();
  }

  /**
   * Returns the x-coordinate of the second endpoint of the line
   */
  public double getX2() {
    return x2.getValue();
  }

  /**
   * Returns the y-coordinate of the second endpoint of the line
   */
  public double getY2() {
    return y2.getValue ();
  }

  /**
   * Returns the first endpoint of the line
   */
  public Point2D getP1() {
    return new Point2D.Double(getX1(), getY1());
  }

  /**
   * Returns the second endpoint of the line
   */
  public Point2D getP2() {
    return new Point2D.Double(getX2(), getY2());
  }

  /**
   * Sets the x-coordinate of the first endpoint of the line
   */
  public LineShape setX1(double x) {
    x1.setValue(this, x);
    return this;
  }

  /**
   * Sets the y-coordinate of the first endpoint of the line
   */
  public LineShape setY1(double y) {
    y1.setValue(this, y);
    return this;
  }

  /**
   * Sets the x-coordinate of the second endpoint of the line
   */
  public LineShape setX2(double x) {
    x2.setValue(this, x);
    return this;
  }

  /**
   * Sets the y-coordinate of the second endpoint of the line
   */
  public LineShape setY2(double y) {
    y2.setValue(this, y);
    return this;
  }

  /**
   * Sets the first endpoint of the line
   */
  public LineShape setP1(Point2D pt) {
    x1.setValue(this, pt.getX());
    y1.setValue(this, pt.getY());
    return this;
  }

  /**
   * Sets the second endpoint of the line
   */
  public LineShape setP2(Point2D pt) {
    x2.setValue(this, pt.getX());
    y2.setValue(this, pt.getY());
    return this;
  }

  /**
   * Returns the midpoint of the line.
   */
  public Point2D getCenter() {
    double centerx = (getX1() + getX2())/2;
    double centery = (getY1() + getY2())/2;
    return( new Point2D.Double(centerx, centery) );
  }

  /*
     public void reshape(int x_in, int y_in, int width_in, int height_in) {
     if(bounds.y == y1) {

     x1 = bounds.x = x_in;
     y1 = bounds.y = y_in;

     bounds.width  = width_in;
     bounds.height = height_in;

     x2 = x1 + width_in;
     y2 = y1 + height_in;

     } else {

     x1 = bounds.x = x_in;
     y2 = bounds.y = y_in;

     bounds.width  = width_in;
     bounds.height = height_in;

     x2 = x1 + width_in;
     y1 = y2 + height_in;
     }

     getCenter();
     invalidate();
     }
   */

  /**
   * Returns the smallest bounding box that encloses the line
   */
  public Rectangle getBounds() {
    int left = (int)Math.min(getX1(), getX2());
    int top = (int)Math.min(getY1(), getY2());
    int width = (int)Math.abs(getX1() - getX2());
    int height = (int)Math.abs(getY1() - getY2());
    return new Rectangle(left, top, width, height);
  }

  /**
   * Returns the line's left
   */
  public double getLeft() {
    return (int)Math.min(getX1(), getX2());
  }

  /**
   * Returns the line's top
   */
  public double getTop() {
    return (int)Math.min(getY1(), getY2());
  }

  /**
   * Returns the line's width 
   */
  public double getWidth() {
    return (int)Math.abs(getX1() - getX2());
  }

  /**
   * Returns the line's height
   */
  public double getHeight() {
    return (int)Math.abs(getY1() - getY2());
  }

  /**
   * A line drawn from the center of the fromObj to the center of this
   * line will intersect this line at its center. Hence return the
   * center point of this line as the intersection point.
   **/
  public Point2D.Double getIntersectPoint(SilShape fromObj) {
    return new Point2D.Double(this.getCenterX(), this.getCenterY());
  }

  /* Add Fields for reflect usage */
  public static Vector Fields           = new Vector(10);

  static {
    Fields.addElement("Top");
    Fields.addElement("Left");
    Fields.addElement("Height");
    Fields.addElement("Width");
    Fields.addElement("Visible");
    Fields.addElement("LineColor");
    Fields.addElement("FillColor");
    Fields.addElement("Filled");
    Fields.addElement("Font");
    Fields.addElement("Text");
  }

}
