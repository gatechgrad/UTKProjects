package silhouette.constraints;

/**
 * Implements a first-in, first-out queue using a doubly-linked list.
 */

public class Queue {

  /**
   * An element on the queue
   */
  protected class Element {
    Object item;
    Element next;
    Element prev;

    Element(Object value) {
      item = value;
    }
  }
 
  /**
   * The doubly linked list uses a sentinel node to facilitate insertions
   * and deletions.
   */
  protected Element sentinelNode = new Element(null);

  /**
   * The 0-argument constructor sets up an empty queue
   */
  Queue () {
    // initialize the sentinel node
    sentinelNode.next = sentinelNode;
    sentinelNode.prev = sentinelNode;
  }
  
  /**
   * Indicates whether the queue has any elements
   */
  public boolean isEmpty() {
    return (sentinelNode.next == sentinelNode);
  }

  int size() {
  	Element tmp = sentinelNode;
	int size = 0;
  	while(tmp.next != sentinelNode) {
		size++;
		tmp = tmp.next;
	}
	return size;
  }	
 
  /**
   * inserts an element at the end of the queue
   */
  void insert(Object value) {
    Element newElement = new Element(value);
    newElement.prev = sentinelNode.prev;
    newElement.next = sentinelNode;
    sentinelNode.prev.next = newElement;
    sentinelNode.prev = newElement;
  }

  /**
   * removes an element from the front of the queue
   */
  Object remove() {
    if (isEmpty())
      return null;
    Object returnValue = sentinelNode.next.item;
    sentinelNode.next = sentinelNode.next.next;
    sentinelNode.next.prev = sentinelNode;
    return returnValue;
  }
}
    
