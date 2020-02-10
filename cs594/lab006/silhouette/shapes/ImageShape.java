package silhouette.shapes;

import java.awt.*;
import java.awt.geom.*;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * A gif, jpeg, or bitmap image that is drawn on the screen. In addition
 * to the normal properties of a Silhouette BoxShape, an ImageShape also
 * has the following property:
 * <p>
 * <ol>
 * <li>Image: The image to be displayed on the screen. The image can be created
 *    from a URL, from a filename, or from an java.awt.Image object.
 * </ol>
 */
public class ImageShape extends BoxShape {

  /**
   * The default size for the image that is returned if a valid width
   * cannot be determined.
   */
  public static final int defaultSize = -2;

  /**
   * A pointer to the image object
   */
  protected Image image;

  /**
   * Constructs an Image shape from an Image object
   */
  public ImageShape(Image img) {
    setImage(img);
    initializeImage();
  }

  /**
   * Constructs an Image shape from an Image object and assigns it
   * the indicated (x,y) location
   */
  public ImageShape(double x, double y, Image img) {
    setLeft(x);
    setTop(y);
    setImage(img);
    initializeImage();
  }

  /**
   * Constructs an Image shape from a URL
   */
  public ImageShape(URL url) {
    setImage(new ImageIcon(url).getImage());
    initializeImage();
  }

  /**
   * Constructs an Image shape from a URL and assigns it the indicated
   * (x,y) location
   */
  public ImageShape(double x, double y, URL url) {
    setLeft(x);
    setTop(y);
    setImage(new ImageIcon(url).getImage());
    initializeImage();
  }

  /**
   * Constructs an Image shape by retrieving the image from a file
   */
  public ImageShape(String filename) {
    setImage(new ImageIcon(filename).getImage());
    initializeImage();
  }

  /**
   * Constructs an Image shape by retrieving the image from a file and
   * assigns it the indicated (x,y) location
   */
  public ImageShape(double x, double y, String filename) {
    setLeft(x);
    setTop(y);
    setImage(new ImageIcon(filename).getImage());
    initializeImage();
  }

  /**
   * Initialize the width and height to defaultSize so that if
   * the user does not provide a width and height, the width and
   * height will be derived from the image's natural width and height
   */ 
  protected void initializeImage() {
    setWidth(defaultSize);
    setHeight(defaultSize);
  }

  /**
   * Draws the image on the screen. This method should never be called
   * directly by an application. To get an image to appear on the
   * screen, either add it to a Silhouette canvas or to a composite shape 
   * that has been added to a Silhouette canvas.
   */
  public void draw(Graphics2D g) {
    if ((getWidth() == defaultSize) && (getHeight() == defaultSize)) {
      g.drawImage(getImage(), (int)getLeft(), (int)getTop(), null);
    }
    else
      g.drawImage(getImage(), (int)getLeft(), (int)getTop(), (int)getWidth(),
        (int)getHeight(), null);
    validate();
  }

  /**
   * Get the image
   */
  public Image getImage () { 
    return image;
  }

  /**
   * Set the image
   */
  public SilShape setImage(Image img) {
    image = img;
    return this;
  }

  /**
   * Return the image's width
   */
  public double getWidth() {
    double wd = width.getValue();
    if (wd == defaultSize)
      return image.getWidth(null);
    else
      return wd;
  }

  /**
   * Return the image's height
   */
  public double getHeight() {
    double ht = height.getValue();
    if (ht == defaultSize)
      return image.getHeight(null);
    else
      return ht;
  }
}
