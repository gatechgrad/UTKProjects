package silhouette.shapes;

import silhouette.shapes.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * The interface for all DisplayManager objects.
 * A display manager is an object tied to a SilContainer and handles
 * the drawing and redrawing of the Shapes within the SilContainer.
 */
public interface DisplayManager {

  /**
   * This method returns the SilContainer handled by the display manager
   */
  public SilContainer getClient();
  
  /**
   * This method sets the SilContainer handled by the display manager
   */
  public DisplayManager setClient(SilContainer container);

  /**
   * This method draws all the Shapes within a SilContainer.
   */
  public abstract void display(Graphics2D g);

  /**
   * This method draws all damaged Shapes within a SilContainer.
   */
  public abstract void redisplay(Graphics2D g);
}
