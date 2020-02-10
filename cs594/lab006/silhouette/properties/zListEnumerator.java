package silhouette.properties;

import java.util.Enumeration;
import java.util.ListIterator;
import silhouette.globals;

/**
 * An enumerator that returns each shape held by a zList
 */

public class zListEnumerator implements Enumeration {

  /**
   * A reference to the zList whose elements we are enumerating
   */
  protected zList sourceList = null;

  /**
   * An iterator for the zList's levels
   */
  protected ListIterator zLevelIter = null;

  /**
   * An enumerator for the list of shapes in a z-level
   */
  protected Enumeration shapeIter = null;

  public zListEnumerator(zList source) {
    sourceList = source;
    zLevelIter = sourceList.getZLevels();
    // The following code is yucky. We want to traverse the sourceList in
    // the reverse direction but unfortunately, Java's iterators start at
    // the front of the list. Hence we first iterate to the end of the list
    // so that the iterator can then traverse the list from back to front.
    while(zLevelIter.hasNext())
      zLevelIter.next();
      
    // initialize shapeIter
    if (zLevelIter.hasPrevious ()) {
      zElement z = (zElement)zLevelIter.previous ();
      shapeIter = z.getShapes();
    }
    else // the zList has no elements so give shapeIter a reference
         // to a list with no elements
      shapeIter = globals.emptyVector.elements();
  }

  /**
   * A zlist has more elements if either:
   * <ol>
   * <li> there is still an element in the current z-level, or
   * <li> there are more z-levels to process and at least one of these
   *       z-levels is non-empty.
   * </ol>
   * A side-effect of the hasMoreElements method is to set the
   * cursor to the next element that should be returned
   */
  public boolean hasMoreElements() {
    while (true) {
      if (shapeIter.hasMoreElements())
        return true;
      else if (zLevelIter.hasPrevious()) {
        zElement z = (zElement)zLevelIter.previous ();
        shapeIter = z.getShapes();
      }
      else
        return false;
    }
  }

  /**
   * Return the next element in the z-list
   */
  public Object nextElement() {
    if (!this.hasMoreElements())
      throw new java.util.NoSuchElementException();
    return shapeIter.nextElement ();
  }
}