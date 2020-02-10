import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class sokoban {
  
  /*** CONSTANTS ***/
  public static final String MENU_ACTION = "Actions";
  public static final String MI_RESET = "Reset Game";
  public static final String MI_LOAD = "Load Board";
  public static final String MI_QUIT = "Quit";

  /*** INSTANCE VARIABLES ***/
  JFrame theFrame;
  SokobanControl theControl;
  BoardData theBoardData;
  BoardView theBoardView;
  BoardReader br = null;

  public sokoban() {
    theControl = new SokobanControl(this);
    theBoardView = new BoardView(this);
    setupWindow();
  }

  private void setupWindow() {
    theFrame = new JFrame();
    WindowListener wl = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        theFrame.hide();
        theFrame.dispose();
        System.exit(0);
      }
    };
    
//    br = new BoardReader("board.txt", this);
//    resetGame();

    theFrame.setLocation(64, 64);
    theFrame.setJMenuBar(makeMenuBar());
    theFrame.addWindowListener(wl);
    theFrame.addKeyListener(theControl);

    theFrame.getContentPane().add(theBoardView);
    theFrame.pack();
    theFrame.show();
  }

  public void resetGame() {
    if (br != null) {
      theBoardData = br.getBoardData();
      repaintViews();
    }
  }
 
  public void quitGame() {
    theFrame.hide();
    theFrame.dispose();
    System.exit(0);
  }

  private JMenuBar makeMenuBar() {
    JMenuBar mb;
    JMenu menuAction;
    JMenuItem miReset, miLoad, miQuit;
    

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

    return mb;
  }


  public BoardData getBoardData() {
    return theBoardData;
  }

  public void showWin() {
    theBoardView.showWin();
  }


  public void cantMove() {
    theBoardView.cantMove();
  }

  public void repaintViews() {
    theBoardView.repaint();
  }

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

  public static void main(String args[]) {
    new sokoban();
  }

}
