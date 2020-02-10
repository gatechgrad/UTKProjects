package silhouette.constraints;

import silhouette.shapes.*;

/**
 * Make two objects have the same width
 **/

public class sameWidth extends constraint {
  protected Object referenceObj;
  protected int displacement;

  /**
   * Make the widths exactly similar
   **/
  public sameWidth(Object obj) {
    referenceObj = obj;
    displacement = 0;
  }

  /**
   * Make the widths differ by the amount of the offset
   **/
  public sameWidth(Object obj, int offset) {
    referenceObj = obj;
    displacement = offset;
  }

  public void formula(constrainedObject self) {
    BoxShape objToSet = (BoxShape)self;
    objToSet.setWidth(((SilShape)referenceObj).getWidth() 
      + displacement);
  }
}

