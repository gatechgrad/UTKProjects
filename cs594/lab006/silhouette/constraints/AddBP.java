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


public class AddBP implements ActionListener {

  ImageShape stopSign;
  constraint cn0;

  public AddBP(constraint cn) {
    super();
    stopSign = cn.getStopSign();
    cn0 = cn;
  }

  public void actionPerformed(ActionEvent e) {
    DebugWindow.sPanel.add(stopSign);
    DebugWindow.sPanel.repaint(); // why must call repaint() here?
    cn0.setBreakPoint(true);
  }

}
