package silhouette.constraints;

/**
 * The top-level abstract class for constraints. A user can create
 * a constraint by anonymously subclassing constraint and overriding
 * the formula method. The user's formula method should explicitly
 * set whatever slots the formula computes.
 */

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.net.*;
import silhouette.shapes.*;

public abstract class constraint {

  /**
   * A list of constraints that needs to be evaluated
   */
  public static Queue evalQueue = new Queue();

  /**
   * The set of constraints that are currently being evaluated.The
   * stack represents a call stack. The
   * top-most constraint on the stack is the constraint that is
   * currently executing. Every other constraint on the stack has
   * called the constraint above it on the stack (actually constraints
   * do not directly call one another, instead they request a
   * property whose value is out of date and hence must be brought
   * up to date by evaulating a constraint.
   */

  static Stack constraintStack = new Stack();

  /**
   * Indicates whether the Evaluate method is currently executing.
   */
  static boolean evalFlag = false;

  /**
   * a flag which is true when breakpoint is set
   */
  boolean breakpoint = false;


  /**
   * Evaluate all the constraints on the evalQueue.
   */
  public static void Evaluate() {
    constraint cn;
    if (!evalFlag) {
      // prevent recursive calls to Evaluate
      evalFlag = true;
      // System.out.println("size of evalQueue============="+evalQueue.size());
      while (!evalQueue.isEmpty()) {

        cn = (constraint)evalQueue.remove();
        cn.validate();  // evaluate the constraint
      }

      evalFlag = false;
    }
  }


  /**
   * The object to which this constraint belongs
   */
  constrainedObject owner = null;

  /**
   * whether or not this constraint is up to date--true means its
   * up to date.
   */
  boolean valid = false;

  /**
   * name of the constraint
   */
  String name = "constraint";

  /**
   * a counter to differenciate constraint
   */
  static int counter = 0;

  /**
   * The set of inputs that the constraint requested during its previous
   * evaluation.
   */
  List inputs = new LinkedList();

  /**
   * The set of properties the constraint set during its previous
   * evaluation
   */
  List outputs = new LinkedList();

  /**
   * The image of StopSign to show breakpoint
   */
  ImageShape stopSign = null;


  /**
   * a main list to store all the constraints which have been initiliazed
   */
  static List cnList = new LinkedList();


  /**
   * The method that the user should override to implement a constraint.
   * The self parameter is a reference to the object that contains the
   * constraint.
   */
  public abstract void formula(constrainedObject self);

  /**
   * constructor
   */

  public constraint() {
    super();
    name = name + (new Integer(counter)).toString();
    counter++;
  }

  /**
   * Initializes the constraint
   */
  void initialize(constrainedObject o) {
    owner = o;
    evalQueue.insert(this); // make sure the constraint gets evaluated
    cnList.add(this);  //add the constraint to main constraint list
  }

  /** 
   * suspend the constraint evaluation process when need
   * observe in the dataflow graph is suspended on a constraint
   */
   void callWait() {

    constraintDebugger.setOperation(constraintDebugger.NULL);
    constraintDebugger.setBlocked(this);
    try {
      if (constraintDebugger.getState() == constraintDebugger.START) {
        constraintDebugger.setState(constraintDebugger.RUNNING);
      }
      // System.out.println(this.toString()+" wait()--------");
      //constraintDebugger.doEventDispatch = true;
      // constraint graph need to be updated
      constraintDebugger.needUpdate = true;

      // prevent constraint dependencies from being created while debugger
      // is executing
      constraint.constraintStack.push(property.doNotCreateDependencies);

      // wake up the constraint Debugger
      if(constraintDebugger.cnDebugger != null) {
        synchronized(constraintDebugger.cnDebugger) {
          constraintDebugger.cnDebugger.notify();
        }
      }
      // let main thread wait 
      wait();
      // System.out.println("been wake up");
    } catch(InterruptedException e) {
      System.err.println("Interrupted");
    }			            

    //System.out.println(this.toString()+" has been notified");				    
  }

  /**
   * when constraint evalution process has been near end,
   * last constraint been evaluated is suspended
   */
   void callLastWait() {

    constraintDebugger.setOperation(constraintDebugger.NULL);
    constraintDebugger.setBlocked(this);
    try {
      if (constraintDebugger.getState() == constraintDebugger.START) {
        constraintDebugger.setState(constraintDebugger.RUNNING);
      }
      // System.out.println(this.toString()+" wait()--------");
      //constraintDebugger.doEventDispatch = true;

      // wake up the constraint Debugger
      if(constraintDebugger.cnDebugger != null) {
        synchronized(constraintDebugger.cnDebugger) {
          constraintDebugger.cnDebugger.notify();
        }
      }
      // let evaluation thread wait 
      wait();
      // System.out.println("been wake up");
    } catch(InterruptedException e) {
      System.err.println("Interrupted");
    }			            

    //System.out.println(this.toString()+" has been notified");				    
  }


  /**
   * collect edges info in the current constraint stack, then store
   * the edges into stackEdges stack
   */

  void collectStackInfo(constraint cn) {
    ListIterator inputIter = cn.getInputs();
    ListIterator outputIter = cn.getOutputs();
    while(inputIter.hasNext()) {
      property ip = (property) inputIter.next();
      //add an edge from property to constraint
      constraintDebugger.stackEdges.
        push(new EdgeElement(ip, cn, true));
    }
    while(outputIter.hasNext()) {
      property op = ((outputInfo) outputIter.next()).outputProperty;
      //add an edge from property to constraint
      constraintDebugger.stackEdges.
        push(new EdgeElement(op, cn, false));
    }

  }

  /**
   * Brings the constraint up-to-date by evaluating its formula
   */
  void validate() {
    valid = true;

    // mark the constraint's outputs as being in the process of being
    // evaluated
    ListIterator outputProperties = outputs.listIterator();
    outputInfo prop;

    while (outputProperties.hasNext()) {
      prop = (outputInfo)outputProperties.next();
      prop.outputProperty.invalidConstraint = property.inProgress;
    }

    // do the evaluation
    constraint.constraintStack.push(new constraintStackInfo(this,
        inputs.listIterator(),
        outputs.listIterator()));
    constraint.constraintStack.push(property.doNotCreateDependencies);
    if(constraintDebugger.cnDebugger != null) {
      // get the stuff in current constraintStack
      collectStackInfo(this);

      // constraint will suspend if breakpoint is met or when calling 'STEP'
      synchronized(this) {
        if(this.breakpoint == true) { 
          if(constraintDebugger.getState() == constraintDebugger.STOP){
            constraintDebugger.cnDebugger.debuggerStart();
            // System.out.println("debugger START---------------");
          }
          callWait();
        }
        else if(
          constraintDebugger.getOperation() == constraintDebugger.STEP ||
          constraintDebugger.getOperation() == constraintDebugger.NEXT) {
          callWait();
        }
        // System.out.println("evaluation thread wakes up");
      }
    }
    
   if(constraint.constraintStack.peek() == property.doNotCreateDependencies){
        constraint.constraintStack.pop();
      }
   // catch any runtime exceptions 	
   try { 
   	formula(owner);
   }catch(RuntimeException e) {
   	System.out.println(e.toString());
	e.printStackTrace();
	if(constraint.constraintStack.peek() 
		== property.doNotCreateDependencies) {
		Object tmp = constraint.constraintStack.pop();
		collectStackInfo((constraint)constraint.constraintStack.peek());
		constraint.constraintStack.push(tmp);
	}
	else {
		collectStackInfo((constraint)constraint.constraintStack.peek());
	}
	// show the stack stuff the moment exception occurs
	constraintDebugger.cnDebugger.updateConstraintGraph();
	
   }	
	
    constraint.constraintStack.push(property.doNotCreateDependencies);
    // constraint will suspend only if debugging process returns to
    // the original constraint which has been traversed when calling 'STEP'
    // OR, if debugging process returns to the original constraint 
    // when calling 'NEXT'
    if(constraintDebugger.cnDebugger != null) {
      synchronized(this) {
        if(constraintDebugger.getOperation() == constraintDebugger.STEP ||
          constraintDebugger.getOperation() == constraintDebugger.NEXT) 
        {
          callWait();
          // System.out.println("evaluation thread wakes up");
        }
      } 

      //constraintDebugger.setBlocked(null);  // set it to "null" or "this" ?
    }
    if(constraint.constraintStack.peek() == property.doNotCreateDependencies){
        constraint.constraintStack.pop();
      }

    constraintStackInfo info
      = (constraintStackInfo)constraint.constraintStack.pop();

    // remove associat edges in stackEdges, including inputs edges
    // and outputs edges
    while(!constraintDebugger.stackEdges.isEmpty() &&
      ((EdgeElement)(constraintDebugger.stackEdges.peek())).cn == this) {
      constraintDebugger.stackEdges.pop();
    }

    // remove any inputs that remain on the inputs list. For each input
    // that is removed from the inputs list, remove the constraint from
    // that input's dependents list
    property oldInput;
    while (info.inputs.hasNext()) {
      oldInput = (property)info.inputs.next();
      oldInput.removeDependent(this);
      info.inputs.remove();
    }


    // remove any outputs that remain on the outputs list.
    while (info.outputs.hasNext()) {
	outputInfo nextOutput = (outputInfo)info.outputs.next();        
	nextOutput.outputProperty.constraints.remove(info.cn);
	info.outputs.remove();
    }

    // mark the constraint's outputs as being up-to-date

    while (info.outputs.hasPrevious()) {
      prop = (outputInfo)info.outputs.previous();
      prop.outputProperty.invalidConstraint = null;
    }

    //one circle of evaluation ends
    if(constraintDebugger.cnDebugger != null
      && constraintDebugger.getState() == constraintDebugger.RUNNING
      && constraint.constraintStack.isEmpty() 
      && constraint.evalQueue.isEmpty()) {
      constraintDebugger.setState(constraintDebugger.NEARSTOP);   
      synchronized(this) {
        callLastWait();
      }
    }

    Runnable repaintWindow = new Runnable() {
      public void run() {
        // redraw those application window waiting for updated objects
        ListIterator iter = constraintDebugger.panelList.listIterator();
        while(iter.hasNext()) {
          SilPanel panel = (SilPanel)iter.next();
	  iter.remove();
          panel.repaint();
        }
      }
    };	
    SwingUtilities.invokeLater(repaintWindow);
  }

  /**
   * Mark this constraint as out-of-date and recursively mark
   * any properties that depend on this constraint as being out-of-date
   */
  void mark() {
    ListIterator outputProperties = outputs.listIterator();
    outputInfo prop;

    valid = false;

    evalQueue.insert(this);

    while (outputProperties.hasNext()) {
      prop = (outputInfo)outputProperties.next();
      prop.outputProperty.markHelper(this);
    }
  }

  /**
   * return the iterator of inputs list
   */
  public ListIterator getInputs() {
    return inputs.listIterator();
  }

  /** 
   *return the iterator of outputs list
   */ 
  public ListIterator getOutputs() {
    return outputs.listIterator();
  }

  /**
   * set name for constraint, default name is "constraint?"
   */
  public void setName(String cnName) {
    name = cnName;
  }

  /**
   * get name of constraint
   */
  public String getName() {
    return name;
  }

  /**
   * return the name of the constraint
   */
  public String toString() {
    return name;
  }

  /**
   * get the image represent a breakpoint is set
   */
  public ImageShape getStopSign() {
    if(stopSign == null) {
      stopSign = new ImageShape(constraint.class.getResource("stop.gif"));
    }
    return stopSign;
  }

  /**
   * return the validility of constraint
   */
  public boolean isValid() {
    return valid;
  }

  /**
   * set or clean the breakpoint
   */
  public void setBreakPoint(boolean flag) {
    breakpoint = flag;
  }

}




