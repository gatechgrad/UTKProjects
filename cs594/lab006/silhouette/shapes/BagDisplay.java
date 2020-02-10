package silhouette.shapes;

import silhouette.shapes.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Enumeration;

/**
 * This is the default DisplayManager for all Silhouette containers.
 * BagDisplay has no extra functionality, it just draws all the Shapes
 * contained in a container.
 */
public class BagDisplay implements DisplayManager {

  /**
   * The reference back to the SilContainer this DisplayManager serves.
   */
  protected SilContainer client;

  /**
   * Indicates whether the affected area should be erased before it is redrawn
   */
  protected boolean shouldErase = false;

  /**
   * Creates a new BagDisplay object with the specified client.
   */
  public BagDisplay(SilContainer clientIn) {
    client = clientIn;
  }

  /**
   * Creates a new BagDisplay object with the specified client and an
   * indication of whether the BagDisplay should erase the affected area
   * before redrawing it
   */
  public BagDisplay(SilContainer clientIn, boolean eraseArea) {
    client = clientIn;
    shouldErase = eraseArea;
  }

  /**
   * Changes the client.
   */
  public DisplayManager setClient(SilContainer client_in) {
    client = client_in;
    return(this);
  }

  /**
   * Returns a reference to the client.
   */
  public SilContainer getClient() {
    return(client);
  }

  /**
   * Returns a reference to the erase property
   */
  public boolean getErase() {
    return shouldErase;
  }

  /**
   * Sets the erase property
   */
  public DisplayManager setErase(boolean eraseArea) {
    shouldErase = eraseArea;
    return this;
  }

  /**
   * BagDisplay clears the graphic space and paints all Shapes in the
   * order held by the SilContainer.
   */
  public void display(Graphics2D g) {

    Rectangle bounds = client.getBounds();
    if (shouldErase)
      g.clearRect(0, 0, bounds.width, bounds.height);

    SilShape curshape;
    Enumeration e = client.getShapes();

    while(e.hasMoreElements()) {
      curshape = (SilShape)e.nextElement();
      curshape.draw(g);
    }
    client.validate();

  }

  /**
   * BagDisplay, being the default DisplayManager, has no special redisplay
   * method.  A call to redisplay makes a call to the display method.
   */
  public void redisplay(Graphics2D g) {
    this.display(g);
  }
}
