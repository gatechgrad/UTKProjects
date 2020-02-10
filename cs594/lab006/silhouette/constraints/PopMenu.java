package silhouette.constraints;

import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import java.util.*;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.inspector.*;

import javax.swing.*;
import graphplace.*;


public class PopMenu extends SilJavaInteractor implements ActionListener {

  property itsProperty;   // the property the constraint graph represents

  CanvasShape sPanel;   // canvas of the debug window

  static int scale;   // scale of distance between each element acoording to
                      //   length of their name

  /**
   * constructor
   */
  public PopMenu(property p) {
    super();
    itsProperty = p;
  }

  /**
   *routine call recursively to find all constraints related to
   * target property
   */
  void TraverseConstraints (Iterator pIter, property self) {
    while (pIter.hasNext()) {
      constraint cn = (constraint)pIter.next();
      //add an edge from constraint to property	
      constraintDebugger.edges.addLast(new EdgeElement(self, cn, false));
      // get input Properties
      Iterator inputIter = cn.getInputs();
      while(inputIter.hasNext()) {

        property ip = (property) inputIter.next();
        //add an edge from property to constraint
        constraintDebugger.edges.addLast(new EdgeElement(ip, cn, true));

        if (ip.getConstraints() != null) {
          TraverseConstraints(ip.getConstraints(), ip);
        }
      }
    }
  }

/**
 * override actionPerfromed method
 */
  public void actionPerformed(ActionEvent e) {
    // first activate constraint debugger
    constraintDebugger.startConstraintDebugger();
	      
    sPanel = constraintDebugger.getDebugWindow().sPanel;
    Iterator cnIter = itsProperty.getConstraints();
    if (cnIter != null) {
      constraintDebugger.edges.removeAll(constraintDebugger.edges);
      TraverseConstraints(cnIter, itsProperty);
    }
    //System.out.println("sizeof edges="+constraintDebugger.edges.size());
    // if Deleted hashtable is not empty, then add edges
    // in it to edges list for display
    if(!constraintDebugger.Deleted.isEmpty()) {
      Iterator deleted = 
	  constraintDebugger.Deleted.values().iterator();
      /*
      for(int i = 0; i < deleted.size(); i++) {
        constraintDebugger.edges.addLast(
          (EdgeElement)deleted.get(i));
      }	
      */
      while(deleted.hasNext()) {
	  constraintDebugger.edges.addLast((EdgeElement)deleted.next());
      }
    }	
    int nameLength = 0;
    // find the maximum name length
    for(int i = 0; i < constraintDebugger.edges.size(); i++) {
      EdgeElement tmp = (EdgeElement)constraintDebugger.edges.get(i);
      //System.out.print(tmp.getFromName() + "-----------");
      //System.out.println(tmp.getToName());
      if(tmp.getFromName().length() > nameLength) {
        nameLength = tmp.getFromName().length();
      }
      if(tmp.getToName().length() > nameLength) {
        nameLength = tmp.getToName().length();
      }

    }
    scale = nameLength *2 - 4;
    graphplaceForList placer = new graphplaceForList(constraintDebugger.edges);
    constraintDebugger.edges = (LinkedList)placer.layoutGraph();
    drawGraph();
  }

 /**
  * core method drawing the dataflow graph
  */
  void drawGraph() {
    int pointNum = 0;
    for(int i = 0; i < constraintDebugger.edges.size(); i++) {
      pointNum++;
      EdgeElement tmp = (EdgeElement)constraintDebugger.edges.get(i);

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
        // property name
        sPanel.add(new TextShape(
            (int)tmp.fromX*scale - tmp.getFromName().length()/2 +80,
            (int)tmp.fromY*scale - 30 +80, tmp.getFromName()));
        // a constraint
        RectangleShape to = new RectangleShape(
          (int)tmp.toX*scale+80-4,
          (int)tmp.toY*scale+80-4, 8, 8);
        sPanel.add(to);
        // add stopSign if has breakpoint set there
        if(tmp.cn.breakpoint == true) {
          sPanel.add(tmp.cn.getStopSign());
        }
        // if constraint is invalid, then fill it as black
        if(!tmp.cn.isValid()) {
          to.setFillColor(Color.black);
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
        // add stopSign if has breakpoint set there
        if(tmp.cn.breakpoint == true) {
          sPanel.add(tmp.cn.getStopSign());
        }
        // if constraint is invalid, then fill it as black
        if(!tmp.cn.isValid()) {
          fm.setFillColor(Color.black);
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

        //if this property is invalid, color it black
        if(!tmp.p.isValid()) {
          to.setFillColor(Color.black);
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
          constraintDebugger.Added.remove(
            tmp.getFromName() + tmp.getToName());
        } 
        // if the relationship has just been deleted, then color it red
        else if(constraintDebugger.Deleted.containsKey( 
            tmp.getFromName() + tmp.getToName())) 
        {
          line.setLineColor(Color.red);
          constraintDebugger.Deleted.remove(
            tmp.getFromName() + tmp.getToName());
        }

      }

    }
    sPanel.setPreferredSize(new Dimension(250*pointNum, 250*pointNum));
    //sPanel.setPreferredSize(new Dimension(1000, 800));
    sPanel.getSilPanel().updateUI();
  }


  public void mousePressed(MouseEvent me){
    // if there's a right mouse key click, show up a popup menu
    if((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {

      JPopupMenu popup = new JPopupMenu();
      JMenuItem  menuItem = new JMenuItem("Show Constraints Graph");
      popup.add(menuItem);
      if(itsProperty.getConstraints() == null) {
    	  menuItem.setEnabled(false);
      }
		    
      menuItem.addActionListener(this);
      popup.show(me.getComponent(),
        me.getX(), me.getY());
    }
  }

}



