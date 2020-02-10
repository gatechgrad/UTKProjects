
//Title:        Silhouette
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Brad Vander Zanden
//Company:      UTK
//Description:

package silhouette.exceptions;

/**
 * An exception class that is thrown when the user tries to access a
 * shape at a z-depth that does not exist.
 */

public class NoSuchDepthException extends RuntimeException {
  /**
   * The z-depth that was accessed
   */
  public double depth;

  /**
   * The constructor takes as an argument the z-depth that was improperly
   * accessed.
   */
  public NoSuchDepthException(double zValue) {
    super("No depth numbered " + zValue + " has been created");
    depth = zValue;
  }
}
