package silhouette.shapes;

import java.awt.*;
import silhouette.properties.*;
import java.awt.geom.*;

import java.util.Vector;

/**
 * A Ellipse that is drawn on the screen.
 */
public class CircleShape extends EllipseShape {

  /**
   * Constructs a new CircleShape with the specified parameters.
   * @param x_in 	 the x coordinate
   * @param y_in 	 the y coordinate
   * @param radius_in	 the radius of the circle
   */
  public CircleShape(double x_in, double y_in, double radius_in) {
    setLeft(x_in);
    setTop(y_in);
    setWidth(2*radius_in);
    setHeight(2*radius_in);
  }



  /**
   * Constructs a default RectShape
   */
  public CircleShape() {}

  /**
   * return the radius of the circle
   */
  public double getRadius() {
    return getWidth()/2;
  }

}
