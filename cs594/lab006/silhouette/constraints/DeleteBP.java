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


public class DeleteBP implements ActionListener {

   ImageShape stopSign;
   constraint cn0;
   public DeleteBP(constraint cn) {
     super();
     stopSign = cn.getStopSign();
     cn0 = cn;
   }

   public void actionPerformed(ActionEvent e) {
     System.out.println("remove BP");
     DebugWindow.sPanel.remove(stopSign);
     DebugWindow.sPanel.repaint();
     cn0.setBreakPoint(false);
   }
}
