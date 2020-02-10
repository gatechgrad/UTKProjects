package silhouette.samples.DebuggerTest;

import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.inspector.*;

import java.awt.event.KeyEvent;

import java.lang.reflect.*;
import java.lang.SecurityManager.*;

import silhouette.constraints.*;
import java.util.Enumeration;

/* a test class */

public class ReflectFrame extends JFrame {
  JPanel contentPane;
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuFileExit = new JMenuItem();
  JMenu menuHelp = new JMenu();
  JMenuItem menuHelpAbout = new JMenuItem();
  JToolBar toolBar = new JToolBar();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  CanvasShape gPanel1 = new CanvasShape(0, 0, 400, 400);
  SilPanel gPanel2 =  gPanel1.getSilPanel();

  public ReflectFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception  {
    image1 = new ImageIcon(ReflectFrame.class.getResource("openFile.gif"));
    image2 = new ImageIcon(ReflectFrame.class.getResource("closeFile.gif"));
    image3 = new ImageIcon(ReflectFrame.class.getResource("help.gif"));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    //this.setPreferredSize(new Dimension(400, 300));
    this.setTitle("Reflect Test");
    statusBar.setText(" ");
    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(
      new ReflectFrame_menuFileExit_ActionAdapter(this));
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(
      new ReflectFrame_menuHelpAbout_ActionAdapter(this));
    jButton1.setIcon(image1);
    jButton1.setToolTipText("Open File");
    jButton2.setIcon(image2);
    jButton2.setToolTipText("Close File");
    jButton3.setIcon(image3);
    jButton3.setToolTipText("Help");
    toolBar.add(jButton1);
    toolBar.add(jButton2);
    toolBar.add(jButton3);
    menuFile.add(menuFileExit);
    menuHelp.add(menuHelpAbout);
    menuBar1.add(menuFile);
    menuBar1.add(menuHelp);
    this.setJMenuBar(menuBar1);
    contentPane.add(toolBar, BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);

    // add SilPanel
    contentPane.add(gPanel2, BorderLayout.CENTER);

    // add CompositeShape
    CompositeShape cs0 = new CompositeShape(0, 0, 390, 390);

    CompositeShape cs1 = new CompositeShape(0, 0, 380, 380);
    cs0.add(cs1);
    gPanel1.add(cs0);

    // show some my defined object
    for (int i=0; i<3; i++) {
      Silrects[i] = new RectangleShape(60*i+20, 50*i+20, 50, 40);
      // better to set a name for each shape you created, which
      // will be shown on the top of inspector frame. This will 
      // let you know which object this inspector window is inspecting
      // especially when there are multiple inspector windows on the screen.
      Silrects[i].setName("Rectangle" + new Integer(i).toString() );
      cs1.add(Silrects[i]);  // add to compositeShape
    }
//cs1.add(Silrects[2]);
//cs1.add(Silrects[1]);
//cs1.add(Silrects[0]);

    // add constraints stuff
    constraint cn1 = new alignTop(Silrects[0], 80);
    Silrects[1].addConstraint(cn1);
    Silrects[1].addConstraint(new constraint () {
      public void formula(constrainedObject owner) {
        RectangleShape self = (RectangleShape) owner;
	self.setWidth(self.getTop() + self.getHeight());
      }
    });
    // constraints to rect2
    constraint cn2 = new alignTop(Silrects[1], 100);
    Silrects[2].addConstraint(cn2);

    TextShape ts0 = new TextShape(10, 100, "This is a TextShape Instance");
    ts0.setName("Text String");
    gPanel1.add(ts0);

    EllipseShape ellipse = new CircleShape(240, 140, 50);
    gPanel1.add(ellipse);
    ellipse.setName("my ellipse");

    LineShape line = new LineShape(20, 300, 200, 300);
    gPanel1.add(line);
    line.setLineColor(Color.green);

    pack();

  }



  // Classwide Variables
  final int MaxCount = 100;
  RectangleShape [] Silrects = new RectangleShape [MaxCount];

  //File | Exit action performed
  public void fileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  //Help | About action performed
  public void helpAbout_actionPerformed(ActionEvent e) {
    ReflectFrame_AboutBox dlg = new ReflectFrame_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation(
      (frmSize.width - dlgSize.width) / 2 + loc.x,
      (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      fileExit_actionPerformed(null);
    }
  }
}

class ReflectFrame_menuFileExit_ActionAdapter implements ActionListener {
  ReflectFrame adaptee;

  ReflectFrame_menuFileExit_ActionAdapter(ReflectFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.fileExit_actionPerformed(e);
  }
}

class ReflectFrame_menuHelpAbout_ActionAdapter implements ActionListener {
  ReflectFrame adaptee;

  ReflectFrame_menuHelpAbout_ActionAdapter(ReflectFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.helpAbout_actionPerformed(e);
  }
}

