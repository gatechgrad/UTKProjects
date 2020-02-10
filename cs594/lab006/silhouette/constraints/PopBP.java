package silhouette.constraints;

import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import java.util.*;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.constraints.*;

import javax.swing.*;
import graphplace.*;


public class PopBP extends SilJavaInteractor {

  constraint cnStop;   // the constraint where breakpoint sets
  double   PosX;
  double   PosY;

  public PopBP(constraint cn, double x, double y) {
    super();
    cnStop = cn;
    cn.getStopSign().setLeft(x);
    cn.getStopSign().setTop(y);
    PosX = x;
    PosY = y;
    //System.out.println("meeeeeeeeeeeeeeeee");
  }

  public void mousePressed(MouseEvent me){
    // if there's a right mouse key click, show up a popup menu
    if((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
      JPopupMenu popup = new JPopupMenu();
      JMenuItem  menuItem1 = new JMenuItem("Add breakpoint");
      popup.add(menuItem1);
      menuItem1.addActionListener(new AddBP(cnStop));
      JMenuItem  menuItem2 = new JMenuItem("Delete breakpoint");
      popup.add(menuItem2);
      menuItem2.addActionListener(new DeleteBP(cnStop));

      popup.show(me.getComponent(),
        me.getX(), me.getY());
      if(!cnStop.isValid()) {
        menuItem1.setEnabled(false);
        menuItem2.setEnabled(false);
      }
    }
  }

}



