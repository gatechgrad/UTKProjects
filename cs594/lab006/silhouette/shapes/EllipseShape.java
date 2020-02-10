package silhouette.shapes;

import java.awt.*;
import silhouette.properties.*;
import java.awt.geom.*;

import java.util.Vector;

/**
 * A Ellipse that is drawn on the screen.
 */
public class EllipseShape extends BoxShape {

  /**
   * Constructs a new EllipseShape with the specified parameters.
   * @param x_in 	 the x coordinate
   * @param y_in 	 the y coordinate
   * @param width_in	 the width
   * @param height_in	 the height
   */
  public EllipseShape(double x_in, double y_in, double width_in, double height_in) 
  {
    setLeft(x_in);
    setTop(y_in);
    setWidth(width_in);
    setHeight(height_in);
  }



  /**
   * Constructs a default RectShape
   */
  public EllipseShape() {}

  /**
   * Paints a Ellipse to the screen. This method should never be called
   * directly by an application. To get a Ellipse to appear on the
   * screen, either add it to a Silhouette canvas or to a composite shape
   * that has been added to a Silhouette canvas.
   */
  public void draw(Graphics2D g) {
    if(getVisible()) {
      if(getFilled()) {
        g.setColor(getFillColor());
        g.fill(new Ellipse2D.Double(getLeft(), getTop(),
            getWidth(), getHeight()));
      }

      g.setColor(getLineColor());
      g.draw(new Ellipse2D.Double(getLeft(), getTop(),
          getWidth(), getHeight()));
      validate();
    }
  }
}
