package silhouette.constraints;

import java.util.*;

/**
 * A record class for constraints that go on the constraint stack
 */
class constraintStackInfo {

  /**
   * The constraint that goes on the stack
   */
  constraint cn;

  /**
   * A list of the constraint's previous set of inputs
   */
  ListIterator inputs;

  /**
   * A list of the constraint's previous set of outputs
   */
  ListIterator outputs;

  /**
   * The constructor should take a constraint, a list iterator for the
   * constraint's previous set of inputs, and a list iterator for the
   * constraint's previous set of outputs.
   */
  constraintStackInfo(constraint c, ListIterator i, ListIterator o) {
    cn = c;
    inputs = i;
    outputs = o;
  }
}
