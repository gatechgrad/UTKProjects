package silhouette.constraints;


import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import silhouette.shapes.*;
import silhouette.inspector.*;



public class DebugWindow extends JFrame {


  /* frame and canvas */
  static JFrame    	    frame;
  static CanvasShape 	sPanel;
  static boolean   	    FrameShow;    // status of the Frame

  static Color		    defColor = Color.black;
  static Font		    defFont  = new Font (
                                   "Lucida Sans Typewriter", Font.PLAIN, 12);
  static JButton        stepButton;
  static JButton        nextButton;
  static JButton        continueButton;


  static int scale;   // scale of distance between each element acoording to
                      // length of their name

  /**
   *Initialization routine,  build up the heavy part
   */
   static public void INITWindow() {
    frame  = new JFrame();
    sPanel = new CanvasShape();
    sPanel.getSilPanel().setWindowType(SilPanel.DebugWindow);
    sPanel.getSilPanel().setPreferredSize(new Dimension(800, 600));


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize  = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height   = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width    = screenSize.width;
    }
    frame.setBounds( 0, 0, 600, 400 );
    // frame.setSize(600, 400);
    // decides the position of the frame
    frame.setLocation(
      (screenSize.width  - frameSize.width )/2 - 500,
      (screenSize.height - frameSize.height)/2 - 300);
    frame.setTitle("Constraints Debug Window");


    JScrollPane sp = new JScrollPane(sPanel.getSilPanel());
    sp.setPreferredSize(new Dimension(600, 400));

    // add "step" button,  used to hide the frame.
    stepButton = new JButton("Step");
    stepButton.setEnabled(false);
    stepButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        constraintDebugger.setOperation(constraintDebugger.STEP);	
        wakeup();
      }
    });
    JPanel buttonPane = new JPanel();
    buttonPane.add(stepButton);

    // press 's' also has the same function as pressing stepButton
    stepButton.setMnemonic(KeyEvent.VK_S);

    // add "next" button,  used to hide the frame.
    nextButton = new JButton("Next");
    nextButton.setEnabled(false);
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        constraintDebugger.setOperation(constraintDebugger.NEXT);
        wakeup();
      }
    });
    buttonPane.add(nextButton);

    // press 'n' also has the same function as pressing stepButton
    nextButton.setMnemonic(KeyEvent.VK_N);


    // add "continue" button,  used to hide the frame.
    continueButton = new JButton("Continue");
    continueButton.setEnabled(false);
    continueButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        constraintDebugger.setOperation(constraintDebugger.CONTINUE);	
        wakeup();
      }
    });
    buttonPane.add(continueButton);

    continueButton.setMnemonic(KeyEvent.VK_C);

    // add "close" button,  used to hide the frame.
    JButton closeButton = new JButton("Exit");
    closeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sPanel.removeAll();       // remove each component from the Canvas
        frame.setVisible(false);  // hide it.
        frame.hide();
        // let constraint debugger stop
        constraintDebugger.setState(constraintDebugger.STOP);
        // delete all the breakpoints set in constraints
        /*for(int i = 0; i < constraint.cnList.size(); i++) {
          constraint cn = 
          (constraint)constraint.cnList.get(i);
          if(cn.breakpoint == true) {
            cn.setBreakPoint(false);
          }
        }
	*/
        if(constraintDebugger.getBlocked() != null) {
          constraintDebugger.setBlocked(null);
        }	
        wakeup();	                  

      } 
    });
    buttonPane.add(closeButton);
    closeButton.setMnemonic(KeyEvent.VK_E);

    // add "help" button,  used to bring up the helper frame.
    JButton helpButton = new JButton("Help");
    helpButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	Helper.showHelper();
      } 
    });
    buttonPane.add(helpButton);
    helpButton.setMnemonic(KeyEvent.VK_H);



    // layout the canvas and buttons on the frame
    frame.getContentPane().add(sp, BorderLayout.CENTER);
    frame.getContentPane().add(buttonPane, BorderLayout.SOUTH);

    // override the "close window" event
    frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        sPanel.removeAll();  // remove each component from the Canvas
        frame.setVisible(false);  // hide it.
        frame.hide();
        // let constraint debugger stop
        constraintDebugger.setState(constraintDebugger.STOP);
        // delete all the breakpoints set in constraints
        /*for(int i = 0; i < constraint.cnList.size(); i++) {
          constraint cn =
          (constraint)constraint.cnList.get(i);
          if(cn.breakpoint == true) {
            cn.setBreakPoint(false);
          }
        }*/
        if(constraintDebugger.getBlocked() != null) {
          constraintDebugger.setBlocked(null);
        }
        wakeup();
      }
    });

  }

  // class initialization
  static {
    INITWindow();
  }

  /**
   * wake up the blocked constraint solver thread
   */
  static void wakeup() {
    Object obj = constraintDebugger.getBlocked();
    if(obj != null) {
      synchronized(obj) {

        if(!constraint.constraintStack.isEmpty() 
          &&constraint.constraintStack.peek() ==
          property.doNotCreateDependencies) 
        {
          constraint.constraintStack.pop();
        }
        obj.notify();

      }
    }		 
  }

  /**
   *bring it up the frame:
   */
  public void ShowNewFrame () {
    sPanel.removeAll();
    sPanel.repaint();
    frame.pack();
    frame.setVisible(true);
    frame.setState(frame.NORMAL);

  }

  /**
   * constructor
   */
  public DebugWindow () {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
  }
}
