package silhouette.shapes;

import java.awt.*;
import silhouette.properties.*;
import java.awt.geom.*;

import java.util.Vector;

/**
 * A rectangle that is drawn on the screen.
 */
public class RectangleShape extends BoxShape {

  /**
   * Constructs a new RectShape with the specified parameters.
   * @param x_in 	 the x coordinate 
   * @param y_in 	 the y coordinate
   * @param width_in	 the width 
   * @param height_in	 the height
   */
  public RectangleShape(double x_in, double y_in, double width_in, double height_in) {
    setLeft(x_in);
    setTop(y_in);
    setWidth(width_in);
    setHeight(height_in);
  }

  public SilShape setHeight(double newHeight) {
    height.setValue(this, newHeight);
    return this;
  }

  /**
   * Constructs a default RectShape
   */
  public RectangleShape() {}

  /**
   * Paints a rectangle to the screen. This method should never be called
   * directly by an application. To get a rectangle to appear on the
   * screen, either add it to a Silhouette canvas or to a composite shape 
   * that has been added to a Silhouette canvas.
   */
  public void draw(Graphics2D g) {
    if(getVisible()) {
      if(getFilled()) {
        g.setColor(getFillColor());
        g.fillRect((int)getLeft(), (int)getTop(), 
          (int)getWidth(), (int)getHeight());
      }

      g.setColor(getLineColor());
      g.drawRect((int)getLeft(), (int)getTop(), 
        (int)getWidth(), (int)getHeight());
      validate();
    }
  }


}
