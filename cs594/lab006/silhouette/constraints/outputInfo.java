package silhouette.constraints;

/**
 * One instance of this class is created for each property that a
 * constraint sets. The class contains a reference to the property
 * and a reference to the property's owner. The reference to the
 * property's owner is required because some methods need this information
 * and because a property does not store a reference to its owner.
 */
class outputInfo {
  constrainedObject propertyOwner;
  property outputProperty;

  outputInfo(constrainedObject owner, property output) {
    propertyOwner = owner;
    outputProperty = output;
  }
}
