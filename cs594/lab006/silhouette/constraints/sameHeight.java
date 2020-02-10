package silhouette.constraints;

import silhouette.shapes.*;

/**
 * Make two objects have the same height
 **/

public class sameHeight extends constraint {
  protected Object referenceObj;
  protected int displacement;

  /**
   * Make the heights exactly similar
   **/
  public sameHeight(Object obj) {
    referenceObj = obj;
    displacement = 0;
  }

  /**
   * Make the heights differ by the amount of the offset
   **/
  public sameHeight(Object obj, int offset) {
    referenceObj = obj;
    displacement = offset;
  }

  public void formula(constrainedObject self) {
    BoxShape objToSet = (BoxShape)self;
    objToSet.setHeight(((SilShape)referenceObj).getHeight() 
      + displacement);
  }
}

