package silhouette.constraints;

import java.util.*;
import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import java.util.*;
import java.lang.reflect.*;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.inspector.*;

import javax.swing.*;
import graphplace.*;


public class constraintDebugger implements Runnable {

  // a singleton DebugWindow
  private static DebugWindow debugW = null;

  // a singleton constraintDebugger
  public static constraintDebugger cnDebugger;

  // a list which stores all the frames waiting to be repainted.
  public static LinkedList panelList = new LinkedList();

  // a list storing the edges in constraint stack
  static Stack stackEdges = new Stack();

  // a list storing property pairs those have constraint relationship
  static LinkedList edges = new LinkedList();

  // a hashtable storing the edges just been added
  static Hashtable Added = new Hashtable(50);

  // a hashtable storing the edge just been deleted
  static Hashtable Deleted = new Hashtable(50);

  /**
   * a constant that denotes the debugger's start state
   */
  public static final int START = -1; // start state

  /**
   * a constant that denotes the debugger's running state
   */
  public static final int RUNNING = -2;  // running state

  /**
   * a constant that denotes the debugger's nearly stop state
   */
  public static final int NEARSTOP = -3;  // near stop state

  /**
   * a constant that denotes the debugger's stop state
   */
  public static final int STOP = -4; // stop state
  
  /**
   * The debugger's current state
   */
  static int state = STOP;

  public static final int STEP = -5; // STEP operation

  public static final int NEXT = -6; // NEXT operation

  public static final int CONTINUE = -7; // CONTINUE operation

  public static final int NULL = 0;  // NULL operation

  static int operation = NULL;

  //Thread cnEventDispatch = null;


  // constraint or property which is currently been blocked in the system
  static Object blocked;

  protected static void setBlocked(Object obj) {
   
    blocked = obj;
  }

  protected static Object getBlocked() {
    return blocked; 
  } 

  /**
   * Set the operation debugger requires
   */
  protected static void setOperation (int oper) {
    operation = oper;
  }

  /**
   * get the current operation debugger should do
   */
  protected static int getOperation () {
    return operation;
  }  

  /**
   * The constraint which is currently operating on
   */
  constraint  selectedConstraint = null;

  Thread selfThread = null;

  boolean firstTime = true;
  
  //debug thread need take over event dispatching or not
  //static boolean doEventDispatch = false;

  static boolean needUpdate = false;

  /**
   * constructor
   */
  protected  constraintDebugger(){
    selfThread = new Thread(constraintDebugger.this);
    selfThread.start();
  } 

  /**
   * make a new constraintDebugger if there isn't one,
   * or return the existed one.
   */
  public static constraintDebugger  startConstraintDebugger() {
    if(cnDebugger == null) {
      //System.out.println("first new constraint debugger\n");
      cnDebugger = new constraintDebugger();
    }
    return cnDebugger;
  }

  /**
   * Get the interactor's state
   */
  public static int getState () {
    return state;
  }

  /**
   * Set the interactor's state
   */
  public static void setState (int newState) {
    state = newState;

  }

  /*void processEvent() {
    try {
      EventQueue q = Toolkit.getDefaultToolkit().getSystemEventQueue()
        ;
      AWTEvent e = q.getNextEvent();

      //System.out.println(e.toString()+"-------------");
      //System.out.println(q.peekEvent().toString()+"..........");
      if (e instanceof ActiveEvent) {
        ((ActiveEvent)e).dispatch();
      }
      else if(e.getSource() instanceof Component) {
        ((Component)e.getSource()).dispatchEvent(e);
      }
      else if (e.getSource() instanceof MenuComponent) {
        ((MenuComponent)e.getSource()).dispatchEvent(e);
      }
      else {
        ;
      }
    }
    catch(java.lang.InterruptedException exception) {}
  }*/

 /**
  * core method for drawing dataflow graph during constraint 
  * evalution process
  */
  void drawGraph(AbstractList edges, Object highlight) {
    int scale = 1;	
    CanvasShape sPanel = constraintDebugger.getDebugWindow().sPanel;
    int nameLength = 0;
    // find the maximum name length
    for(int i = 0; i < edges.size(); i++) {
      EdgeElement tmp =
        (EdgeElement)edges.get(i);
      if(tmp.getFromName().length() > nameLength) {
        nameLength = tmp.getFromName().length();
      }
      if(tmp.getToName().length() > nameLength) {
        nameLength = tmp.getToName().length();
      }

    }
    scale = nameLength *2 - 4;
    
    int pointNum = 0; 
    for(int i = 0; i < edges.size(); i++) {
      pointNum++;
      EdgeElement tmp = (EdgeElement)edges.get(i);

      if(tmp.fromIsProperty == true) {
        // a property
        CircleShape fm = new CircleShape(
          (int)tmp.fromX*scale+80-4,
          (int)tmp.fromY*scale+80-4, 4);
        sPanel.add(fm);
        //add interactor for inspect current property value of the object
        SilShape owner = tmp.p.getOwner();
        fm.setObjToPresent(owner);
        fm.addInteractor(sPanel.getInspector().getPropertyBehavior());

        //if this property is invalid, color it black
        if(!tmp.p.isValid()) {
          fm.setFillColor(Color.black);
        }
        // if evalution process suspends here, color it green	
        if(highlight == tmp.p) {
          fm.setFillColor(Color.green);
        }
        // property name
        sPanel.add(new TextShape(
            (int)tmp.fromX*scale - tmp.getFromName().length()/2 +80,
            (int)tmp.fromY*scale - 30 +80, tmp.getFromName()));
        // a constraint
        RectangleShape to = new RectangleShape(
          (int)tmp.toX*scale+80-4,
          (int)tmp.toY*scale+80-4, 8, 8);
        sPanel.add(to);
        // if constraint is invalid, then fill it as black
        if(!tmp.cn.isValid()) {
          to.setFillColor(Color.black); 
        }
        // if evalution process suspends here, color it green  
        if(highlight == tmp.cn) {
          to.setFillColor(Color.green);
        }
        // add stopSign if has breakpoint set there
        if(tmp.cn.breakpoint == true) {
          sPanel.add(tmp.cn.getStopSign());
        }	

        // to pop up a menu for adding breakpoint
        to.addInteractor(new PopBP(tmp.cn, tmp.toX*scale+80-4, 
            tmp.toY*scale+80+10));
        // constraint name
        sPanel.add(new TextShape(
            (int)tmp.toX*scale - tmp.getToName().length()/2+80,
            (int)tmp.toY*scale - 30 +80, tmp.getToName()));
        // link two points
        SilArrowLine line = new SilArrowLine(
          (int)tmp.fromX*scale+80+4, 
          (int)tmp.fromY*scale+80, 
          (int)tmp.toX*scale+80-4, 
          (int)tmp.toY*scale+80);

        // if the relationship is new added, then color it blue
        if(constraintDebugger.Added.containsKey(
            tmp.getFromName() + tmp.getToName())){
          line.setLineColor(Color.blue);
          constraintDebugger.Added.remove(
            tmp.getFromName() + tmp.getToName());
        } 
        // if the relationship has just been deleted, then color it red
        else if(constraintDebugger.Deleted.containsKey(
            tmp.getFromName() + tmp.getToName())) {
          line.setLineColor(Color.red);
          constraintDebugger.Deleted.remove(
            tmp.getFromName() + tmp.getToName());

        }         

        sPanel.add(line);				
      }
      else {
        // a constraint
        RectangleShape fm = new RectangleShape(
          (int)tmp.fromX*scale+80-4, 
          (int)tmp.fromY*scale+80-4, 8, 8);

        sPanel.add(fm);
        // if constraint is invalid, then fill it as black
        if(!tmp.cn.isValid()) {
          fm.setFillColor(Color.black); 
        }
        // if evalution process suspends here, color it green  
        if(highlight == tmp.cn) {
          fm.setFillColor(Color.green);
        }
        // add stopSign if has breakpoint set there
        if(tmp.cn.breakpoint == true) {
          sPanel.add(tmp.cn.getStopSign());
        }
        // constraint name
        sPanel.add(new TextShape(
            (int)tmp.fromX*scale - tmp.getFromName().length()/2 +80,
            (int)tmp.fromY*scale - 30 +80, tmp.getFromName()));
        // a property
        CircleShape to = new CircleShape(
          (int)tmp.toX*scale+80-4,
          (int)tmp.toY*scale+80-4, 4);
        sPanel.add(to);
        // add property inpector to it
        SilShape owner = tmp.p.getOwner();
        to.setObjToPresent(owner);
        to.addInteractor(sPanel.getInspector().getPropertyBehavior());

        //if this property is invaddlid, color it black
        if(!tmp.p.isValid()) {
          to.setFillColor(Color.black);
        }
        // if evalution process suspends here, color it green   
        if(highlight == tmp.p) {
          to.setFillColor(Color.green);
        }
        // property name
        sPanel.add(new TextShape(
            (int)tmp.toX*scale - tmp.getToName().length()/2+80,
            (int)tmp.toY*scale - 30 +80, tmp.getToName()));
        SilArrowLine line = new SilArrowLine(
          (int)tmp.fromX*scale+80+4, 
          (int)tmp.fromY*scale+80, 
          (int)tmp.toX*scale+80-4, 
          (int)tmp.toY*scale+80);
        sPanel.add(line);
        // if the relationship is new added, then color it blue
        if(constraintDebugger.Added.containsKey(
            tmp.getFromName() + tmp.getToName())) 
        {
          line.setLineColor(Color.blue);            
          constraintDebugger.Added.remove(tmp.getFromName() + tmp.getToName());
        } 
        // if the relationship has just been deleted, then color it red
        else if(constraintDebugger.Deleted.containsKey(
            tmp.getFromName() + tmp.getToName())) 
        {
          line.setLineColor(Color.red);
          constraintDebugger.Deleted.remove(tmp.getFromName()+tmp.getToName());
        }
      }
    }
    sPanel.setPreferredSize(new Dimension(250*pointNum, 250*pointNum));
    sPanel.getSilPanel().updateUI();
	
  }


  /**
   * do the drawing stuff then put it into the event queue
   */
  void updateConstraintGraph(){
    Runnable drawConstraintGraph = new Runnable() {
      public void run() {
        int scale = 1;
        Object highlight = getBlocked();
        graphplaceForList placer =
          new graphplaceForList(constraintDebugger.stackEdges);
        constraintDebugger.stackEdges = (Stack)placer.layoutGraph();

        drawGraph(constraintDebugger.stackEdges, highlight);

      }
    };

    SwingUtilities.invokeLater(drawConstraintGraph);
    //System.out.println("end of update constriantGraph");
  }

 /**
  * stop the constriant debugger
  */
  public void debuggerStop() {
    // set constraint debugger to STOP if it's existed
    if(constraintDebugger.cnDebugger != null &&
      constraintDebugger.getState() == constraintDebugger.NEARSTOP) {
      constraintDebugger.setState(constraintDebugger.STOP);
      // System.out.println("SSSSSSSSSSSSSTOPPPPPPPPPPPPPPPPPPPPPP");
      DebugWindow.stepButton.setEnabled(false);
      DebugWindow.nextButton.setEnabled(false);
      DebugWindow.continueButton.setEnabled(false);
      Runnable drawFinalGraph = new Runnable() {
        public void run() {
          constraintDebugger.cnDebugger.drawGraph(
            constraintDebugger.edges, null);
        }
      };
      SwingUtilities.invokeLater(drawFinalGraph);		     

    }
  }

 /**
  * suspend constraint debugger
  */ 
 public void debuggerWait() {
    if(constraintDebugger.cnDebugger != null) { 
      synchronized(constraintDebugger.cnDebugger) {
        try{
          //System.out.println("debugger thread wait()---------------");
          constraintDebugger.cnDebugger.wait();
        } catch(InterruptedException e) {
          System.err.println("Interrupted");
        }      
        //System.out.println("debugger thread been waken up");
      }    
    }
  }

/**
 * start constraint debugger
 */
  public void debuggerStart() {
    if(constraintDebugger.cnDebugger != null) {
      constraintDebugger.setState(constraintDebugger.START);
      // System.out.println("constraintDebugger.........START");
      Runnable start = new Runnable() {
        public void run() {
          DebugWindow.stepButton.setEnabled(true);
          DebugWindow.nextButton.setEnabled(true);
          DebugWindow.continueButton.setEnabled(true);
        }
      };
      SwingUtilities.invokeLater(start);

    }
  }	     

  /**
   * thread's run() method
   */
 public void run() {
    while(true) {
      debuggerWait();	  
      // System.out.println("debugging thread is running************");
      // System.out.println("constraintDebugger.state ====== "+ state);

      if(needUpdate == true) { 
        //state == constraintDebugger.RUNNING && needUpdate == true) {
        // System.out.println("constraintGraph updating");
        updateConstraintGraph();
        needUpdate = false;
      }
      /*else if(constraintDebugger.cnDebugger != null
        && constraintDebugger.getState() == constraintDebugger.RUNNING
        && constraint.constraintStack.isEmpty() 
        && constraint.evalQueue.isEmpty()) {*/
      else if(constraintDebugger.getState() == constraintDebugger.NEARSTOP){       
        finalWakeup();
        debuggerStop();
        //doEventDispatch = false;	
      }

     }
  }

  /**
   * wake up the constraint suspended by callLastWait()
   */
  void finalWakeup() {
    Object obj = constraintDebugger.getBlocked();
    if(obj != null) {
      synchronized(obj) {
        obj.notify();

      }
    }
  }

  /**
   * return DebugWindow if existed or, create a new one
   */
 static public DebugWindow  getDebugWindow() {
    //System.out.println("get DebugWindow");
    if(debugW == null) {
      debugW = new DebugWindow();
    }
    debugW.ShowNewFrame();

    return debugW;
  }
}     
