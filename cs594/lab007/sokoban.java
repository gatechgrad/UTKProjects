/**
 * sokoban.java - contains entry point for the sokoban game, 
 *                holds game structures, and makes the game 
 *                window 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 28, 2003
 * @course CS594 Graphical User Interfaces
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

public class sokoban {
  
  /*** CONSTANTS ***/
  public static final String MENU_ACTION = "Actions";
  public static final String MI_RESET = "Reset Game";
  public static final String MI_LOAD = "Load Board";
  public static final String MI_QUIT = "Quit";
  public static final String MENU_COLORS = "Colors";
  public static final String MI_COLOR_WALL = "Change Wall Color";
  public static final String MI_COLOR_GOAL = "Change Shaded Area (Goal) Color";
  public static final String MI_COLOR_BLANK = "Change Blank Space  Color";
  public static final String MENU_EDITOR = "Editor";
  public static final String MI_EDITOR = "Game Board Editor";

  /*** INSTANCE VARIABLES ***/
  private JFrame theFrame;
  private SokobanControl theControl;
  private BoardData theBoardData;
  private BoardView theBoardView;
  private TextBoardView theTextBoardView;
  private BoardReader br = null;
  private OptionPanel theOptionPanel;
  private Vector vectEditors;

  /**
   * sokoban - constructor
   */
  public sokoban() {
    theControl = new SokobanControl(this);
    theBoardView = new BoardView(theBoardData, this);
    theTextBoardView = new TextBoardView(theBoardData);
    theOptionPanel = new OptionPanel(this);
    vectEditors = new Vector();
    
    setupWindow();
  }

  /**
   * setupWindow - creates the game window
   */
  private void setupWindow() {
    theFrame = new JFrame();

    WindowListener wl = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        theFrame.hide();
        theFrame.dispose();
        System.exit(0);
      }
    };
    

    theFrame.setJMenuBar(makeMenuBar());
    theFrame.addWindowListener(wl);
    theFrame.addKeyListener(theControl);

    theFrame.getContentPane().setLayout(new BorderLayout());
    theFrame.getContentPane().add(theBoardView.getSilPanel(), BorderLayout.CENTER);
    theFrame.getContentPane().add(theTextBoardView.getSilPanel(), BorderLayout.EAST);
    theFrame.getContentPane().add(theOptionPanel, BorderLayout.SOUTH);

    theFrame.pack();
    theFrame.show();
    theFrame.setLocation(0, 32);
  }

  /**
   * resetGame - called when the game needs to be reset
   */
  public void resetGame() {
    if (br != null) {
      theBoardData = br.getBoardData();
      updateModels();
      updateMoves();
    }
  }
 
  /**
   * quitGame - called when the game needs to quit
   */
  public void quitGame() {
    theFrame.hide();
    theFrame.dispose();
    System.exit(0);
  }

  /**
   * makeMenuBar - makes a returns a menubar for the window
   */
  private JMenuBar makeMenuBar() {
    JMenuBar mb;
    JMenu menuAction, menuColor, menuEditor;
    JMenuItem miReset, miLoad, miQuit;
    JMenuItem miColorWall, miColorGoal, miColorBlank;
    JMenuItem miEditor;
    

    mb = new JMenuBar();

    menuAction = new JMenu(MENU_ACTION);

    miReset = new JMenuItem(MI_RESET, null);
    miReset.addActionListener(theControl);    
    menuAction.add(miReset);

    miLoad = new JMenuItem(MI_LOAD, null);
    miLoad.addActionListener(theControl);    
    menuAction.add(miLoad);

    miQuit = new JMenuItem(MI_QUIT, null);
    miQuit.addActionListener(theControl);    
    menuAction.add(miQuit);

    mb.add(menuAction);

    menuColor = new JMenu(MENU_COLORS);

    miColorWall = new JMenuItem(MI_COLOR_WALL, null);
    miColorWall.addActionListener(theControl);    
    menuColor.add(miColorWall);

    miColorGoal = new JMenuItem(MI_COLOR_GOAL, null);
    miColorGoal.addActionListener(theControl);    
    menuColor.add(miColorGoal);

    miColorBlank = new JMenuItem(MI_COLOR_BLANK, null);
    miColorBlank.addActionListener(theControl);    
    menuColor.add(miColorBlank);

    mb.add(menuColor);

    menuEditor = new JMenu(MENU_EDITOR);

    miEditor = new JMenuItem(MI_EDITOR, null);
    miEditor.addActionListener(theControl);    
    menuEditor.add(miEditor);

    mb.add(menuEditor);


    return mb;
  }


  /**
   * getBoardData - returns the board data (model)
   */
  public BoardData getBoardData() {
    return theBoardData;
  }

  /**
   * showWin - tells all of the views to show a win
   */
  public void showWin() {
    theBoardView.showWin();
  }


  /**
   * cantMove - tells all of the view to show their representation
   *            of an invalid move (beep)
   */
  public void cantMove() {
    theBoardView.cantMove();
  }

  /**
   * updateMoves - tells all of the views to repaint themselves
   */
  public void updateMoves() {
    theBoardView.updateMoves();
    //theBoardView.repaint();
    theTextBoardView.repaint();
  }

  public void updateModels() {
    theBoardView.updateModel(theBoardData);
    theTextBoardView.updateModel(theBoardData);
  }

  public void setViewsWallColor() {
    JColorChooser jcc;
    Color c;
                                                                                
    jcc = new JColorChooser();
    c = jcc.showDialog(theBoardView.getSilPanel(), "Choose Wall Color", Color.white);

    if (c != null) {
      theBoardView.setWallColor(c); 
      updateMoves();
    }
  }


  public void setViewsGoalColor() {
    JColorChooser jcc;
    Color c;
                                                                                
    jcc = new JColorChooser();
    c = jcc.showDialog(theBoardView.getSilPanel(), "Choose Goal (Shaded) Color", Color.white);

    if (c != null) {
      theBoardView.setGoalColor(c); 
      updateMoves();
    }

  }

  public void setViewsBlankColor() {
    JColorChooser jcc;
    Color c;
                                                                                
    jcc = new JColorChooser();
    c = jcc.showDialog(theBoardView.getSilPanel(), "Choose Blank Color", Color.white);

    if (c != null) {
      theBoardView.setBlankColor(c); 
      updateMoves();
    }

  }

  public void setViewsSmallSize() {
    theBoardView.setViewSmallSize();
    updateMoves();
  }

  public void setViewsMediumSize() {
    theBoardView.setViewMediumSize();
    updateMoves();
  }

  public void setViewsLargeSize() {
    theBoardView.setViewLargeSize();
    updateMoves();

  }

  public SokobanControl getControl() {
    return(theControl);
  }

  public void setGameFocus() {
    theFrame.requestFocus();
  }

  /**
   * loadBoard - shows a file selection dialog to load a board
   */
  public void loadBoard() {
    FileDialog fd;

    fd = new FileDialog(theFrame);
    System.out.println("Loading...");
    fd.show();

    if ((fd.getDirectory() != null) && (fd.getFile() != null)) {

      br = new BoardReader(fd.getDirectory() + "/" + fd.getFile(), this);
      resetGame();
      theFrame.requestFocus();

    }

  }

  public void addEditor(GameBoardEditor gbe) {
    vectEditors.addElement(gbe);
  }

  public void quitAllEditors() {
    int i;
    for (i = 0; i < vectEditors.size(); i++) {
      ((GameBoardEditor) vectEditors.elementAt(i)).quit();
    }
  }

  /**
   * main - entry point for the program
   */
  public static void main(String args[]) {
    System.out.println("CS594--Lab5\nSokoban\n\nSubmitted by Levi D. Smith\nlsmith55@utk.edu\nlsmith@cs.utk.edu");

    System.out.println("\nSokoban Objective:\n" +
                       "\tMove all balls into goal areas");
    System.out.println("\nControls:\n" +
                       "\tUp Arrow, k      Move Up\n" +
                       "\tDown Arrow, j    Move Down\n" +
                       "\tLeft Arrow, h    Move Left\n" +
                       "\tRight Arrow, l   Move Right\n" +
                       "\tLeft Click on adjacent ball to move it one space\n" +
                       "\tLeft Click and drag player to move spaces \n" +
                       "\t\tand move balls in the process\n" +
                       "\tLeft Click on any reachable free area to \n" +
                       "\t\tmove player to that location, not \n" +
                       "\t\tnecessarily taking the optimal path\n" +
                       "\tRight Click      Undo previous move\n");
    new sokoban();
  }

}
