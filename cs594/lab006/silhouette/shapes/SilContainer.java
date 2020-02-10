package silhouette.shapes;

import java.util.Enumeration;
import java.awt.Rectangle;

/**
 * An interface for Silhouette objects that contain other Silhouette objects
 */
public interface SilContainer {
  /**
   * Returns an enumeration of the object's shapes
   */
  public Enumeration getShapes();

  /**
   * Returns the bounds of the container
   */
  public Rectangle getBounds();

    /**
     * Validates the container so that it is shown as being redrawn
     */
  public void validate();
}
