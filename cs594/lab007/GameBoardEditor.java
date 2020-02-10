/**
 * GameBoardEditor.java - editor for sokoban game files 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 28, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Vector;

public class GameBoardEditor implements ActionListener {

  public static final String BUT_PLAYER = "Player";
  public static final String BUT_BALL = "Ball";
  public static final String BUT_GOAL = "Goal";
  public static final String BUT_WALL = "Wall";
  public static final String BUT_BLANK = "Blank";

  public static final String BUT_NEW_LABEL = "New";
  public static final String BUT_SAVE_LABEL = "Save";
  public static final String BUT_SAVEAS_LABEL = "Save As";
  public static final String BUT_LOAD_LABEL = "Load";
  public static final String BUT_QUIT_LABEL = "Quit";

  public static final String BUT_DELROW_LABEL = "Delete Row";
  public static final String BUT_DELCOL_LABEL = "Delete Column";


  GBEDrawingArea theDrawingArea;
  private BoardData theModel = null;
  JFrame theFrame;
  JButton butDelRow, butDelCol;
  private String strFileName;
  private sokoban theSokoban;

  JToggleButton butPlayer, butBall, butWall, butGoal, butBlank;

  /**
   * GameBoardEditor - constructor
   */
  public GameBoardEditor(sokoban s) {
    strFileName = "";

    theDrawingArea = new GBEDrawingArea(theModel, this);

    theSokoban = s;
    if (theSokoban != null) {
      theSokoban.addEditor(this); 
    }

    setupWindow();

    theFrame.setLocation(0, 32);
    theFrame.show();
  }


  /**
   * setupWindow - sets up the game board editor window
   */
  private void setupWindow() {
    JPanel pnlCommandArea;
    JPanel pnlFile, pnlAdd, pnlDelete;

    JToolBar tb;
    Icon icoPlayer, icoBall, icoWall, icoGoal, icoBlank;

    theFrame = new JFrame();

    tb = new JToolBar();
    

    icoPlayer = new ImageIcon("character.gif");
    butPlayer = new JToggleButton(icoPlayer); 
    butPlayer.setActionCommand(BUT_PLAYER);    
    butPlayer.addActionListener(this);
    tb.add(butPlayer);

    icoBall = new ImageIcon("ball.gif");
    butBall = new JToggleButton(icoBall); 
    butBall.setActionCommand(BUT_BALL);
    butBall.addActionListener(this);
    tb.add(butBall);

    icoWall = new ImageIcon("ico_wall.gif");
    butWall = new JToggleButton(icoWall); 
    butWall.setActionCommand(BUT_WALL);    
    butWall.addActionListener(this);
    tb.add(butWall);

    icoGoal = new ImageIcon("ico_goal.gif");
    butGoal = new JToggleButton(icoGoal); 
    butGoal.setActionCommand(BUT_GOAL);    
    butGoal.addActionListener(this);
    tb.add(butGoal);

    icoBlank = new ImageIcon("ico_blank.gif");
    butBlank = new JToggleButton(icoBlank); 
    butBlank.setActionCommand(BUT_BLANK);    
    butBlank.addActionListener(this);
    tb.add(butBlank);

    pnlCommandArea = new JPanel();
    pnlCommandArea.setLayout(new BoxLayout(pnlCommandArea, BoxLayout.X_AXIS));
    
    pnlCommandArea.add(Box.createHorizontalGlue());

    pnlFile = new JPanel();
    setupFilePanel(pnlFile);
    pnlCommandArea.add(pnlFile);

    pnlCommandArea.add(Box.createHorizontalStrut(16));

    pnlAdd = new JPanel();
    setupAddRowsAndCols(pnlAdd);
    pnlCommandArea.add(pnlAdd);

    pnlCommandArea.add(Box.createHorizontalStrut(16));

    pnlDelete = new JPanel();
    setupDeleteRowsAndCols(pnlDelete);
    pnlCommandArea.add(pnlDelete);

    pnlCommandArea.add(Box.createHorizontalGlue());

    theFrame.getContentPane().setLayout(new BorderLayout());
    theFrame.getContentPane().add(tb, BorderLayout.NORTH);
    theFrame.getContentPane().add(theDrawingArea.getSilPanel(), BorderLayout.CENTER);
    theFrame.getContentPane().add(pnlCommandArea, BorderLayout.SOUTH);
    theFrame.pack();

  }

  /**
   * setupFilePanel - lays out all of the components in the file
   *                  panel
   */
  private void setupFilePanel(JPanel pnl) {
    JPanel pnl001, pnl002;
    JButton butNew, butSave, butSaveAs, butLoad, butQuit;

    pnl001 = new JPanel();
    pnl001.setLayout(new BoxLayout(pnl001, BoxLayout.Y_AXIS));

    pnl001.add(Box.createVerticalStrut(8));

    pnl001.add(new JLabel("File"));

    pnl001.add(Box.createVerticalStrut(8));

    butNew = new JButton(BUT_NEW_LABEL);
    butNew.addActionListener(this);
    pnl001.add(butNew);

    pnl001.add(Box.createVerticalStrut(8));

    butSave = new JButton(BUT_SAVE_LABEL);
    butSave.addActionListener(this);
    pnl001.add(butSave);

    pnl001.add(Box.createVerticalStrut(8));

    butSaveAs = new JButton(BUT_SAVEAS_LABEL);
    butSaveAs.addActionListener(this);
    pnl001.add(butSaveAs);

    pnl001.add(Box.createVerticalGlue());

    pnl002 = new JPanel();
    pnl002.setLayout(new BoxLayout(pnl002, BoxLayout.Y_AXIS));

    pnl002.add(Box.createVerticalStrut(8));

    butLoad = new JButton(BUT_LOAD_LABEL);
    butLoad.addActionListener(this);
    pnl002.add(butLoad);

    pnl002.add(Box.createVerticalStrut(8));

    butQuit = new JButton(BUT_QUIT_LABEL);
    butQuit.addActionListener(this);
    pnl002.add(butQuit);

    pnl002.add(Box.createVerticalStrut(8));
    pnl002.add(Box.createVerticalGlue());
    
    pnl.add(pnl001);
    pnl.add(pnl002);
    pnl.setBorder(new TitledBorder(""));

  }

  /**
   * setupAddRowsAndCols - sets up the panel that holds the
   *                       "Add Rows and Columns" widget
   */
  private void setupAddRowsAndCols(JPanel pnl) {
    AddWidget aw;

    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
    pnl.add(Box.createVerticalStrut(8));

    pnl.add(new JLabel("Add New Row/Column"));

    pnl.add(Box.createVerticalStrut(8));

    aw = new AddWidget(this);
    pnl.add(aw);

    pnl.add(Box.createVerticalGlue());

    pnl.setBorder(new TitledBorder(""));
  }

  /**
   * setupDeleteRowsAndCols - sets up the panel that holds the
   *                          delete row and delete col buttons
   */
  private void setupDeleteRowsAndCols(JPanel pnl) {
    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));

    pnl.add(Box.createVerticalStrut(8));

    pnl.add(new JLabel("Delete Row/Column"));

    pnl.add(Box.createVerticalStrut(8));

    butDelRow = new JButton(BUT_DELROW_LABEL);
    butDelRow.addActionListener(this);
    pnl.add(butDelRow);

    pnl.add(Box.createVerticalStrut(8));

    butDelCol = new JButton(BUT_DELCOL_LABEL);
    butDelCol.addActionListener(this);
    pnl.add(butDelCol);

    pnl.add(Box.createVerticalStrut(8));
    pnl.add(Box.createVerticalGlue());


    pnl.setBorder(new TitledBorder(""));
  }

  /**
   * createNew - create a new game board editor
   */
  public void createNew(int iRows, int iCols) {
    GBEFileIO fio;
    fio = new GBEFileIO(null); 

    fio.createBlankBoard(iRows, iCols);
    theModel = fio.getBoardData();
    theDrawingArea.updateModel(theModel); 
  }
 
  /**
   * quit - public method that tells the game board editor to
   *        quit
   */
  public void quit() {
    theFrame.hide();
  }

  /**
   * loadBoard - loads a file into the game board editor
   */
  private void loadBoard() {
    GBEFileIO fio;
    FileDialog fd;

    fd = new FileDialog(theFrame);
    fd.show();

    if ((fd.getDirectory() != null) && (fd.getFile() != null)) {
      strFileName = fd.getDirectory() + "/" + fd.getFile();

      fio = new GBEFileIO(null); 
      fio.readBoard(strFileName);
      theModel = fio.getBoardData();
      theDrawingArea.updateModel(theModel); 

    }

      
  }

  /**
   * saveBoard - saves the current board to a file
   */
  private void saveBoard() {
    GBEFileIO fio;
    FileDialog fd;

    if (strFileName.equals("")) {
      fd = new FileDialog(theFrame);
      fd.show();

      if ((fd.getDirectory() != null) && (fd.getFile() != null)) {
        strFileName = fd.getDirectory() + "/" + fd.getFile();
      }
    }

    if (!strFileName.equals("")) {

      fio = new GBEFileIO(null); 
      fio.convertModelToText(theModel);
      fio.writeFile(strFileName);
      
    }
  }


  /**
   * saveBoardAs - saves the current board to a file (requires
   *               the user to enter a file name
   */
  private void saveBoardAs() {
    GBEFileIO fio;
    FileDialog fd;

    fd = new FileDialog(theFrame);
    fd.show();

    if ((fd.getDirectory() != null) && (fd.getFile() != null)) {
      strFileName = fd.getDirectory() + "/" + fd.getFile();

      fio = new GBEFileIO(null); 
      fio.convertModelToText(theModel);
      fio.writeFile(strFileName);
    }
  }

  /**
   * newEditor - creates a new game board editor
   */
  private void newEditor() {
    GameBoardEditor gbe;

    GBESizeDialog gsd = new GBESizeDialog(theFrame, this);
    gsd.show();    
    gsd.setLocation(0, 32);
    
    if ((gsd.getRows() > 0) &&
        (gsd.getCols() > 0)
       ) {
      gbe = new GameBoardEditor(theSokoban); 
      gbe.createNew(gsd.getRows(), gsd.getCols());
    }
    
  }
   
  /**
   * getModel - returns the game board editor data model
   */
  public BoardData getModel() {
    return theModel;
  }

  /** 
   * getDrawingArea - returns the game board edtior drawing
   *                  area (View)
   */
  public GBEDrawingArea getDrawingArea() {
    return theDrawingArea;
  }


  /**
   * resetButtons - resets all of the toolbar toggle buttons
   */
  public void resetButtons() {
    butPlayer.setSelected(false);
    butBall.setSelected(false);
    butWall.setSelected(false);
    butGoal.setSelected(false);
    butBlank.setSelected(false);
  }


  /**
   * checkButtons - checked to see if any of the toolbar toggle
   *                buttons are pressed, and react accordingly
   */
  public void checkButtons() {

    if (butPlayer.isSelected()) {
      if ((theDrawingArea.getSelectedCell().x > -1) &&
          (theDrawingArea.getSelectedCell().y > -1)
         ) {
        theModel.deleteGamePiece(theDrawingArea.getSelectedCell().x,
                         theDrawingArea.getSelectedCell().y);

        theModel.getPlayer().setPlayerLocation(
                           theDrawingArea.getSelectedCell().x,
                           theDrawingArea.getSelectedCell().y);

        theDrawingArea.clearSelectedCell();
        theDrawingArea.repaint();
      }
    } else if (butWall.isSelected()) {
      if ((theDrawingArea.getSelectedCell().x > -1) &&
          (theDrawingArea.getSelectedCell().y > -1)
         ) {
        theModel.deleteGamePiece(theDrawingArea.getSelectedCell().x,
                         theDrawingArea.getSelectedCell().y);
        theModel.getTile(theDrawingArea.getSelectedCell().x,
                         theDrawingArea.getSelectedCell().y).setPassable(false);
        theDrawingArea.clearSelectedCell();
        theDrawingArea.repaint();
      }
    } else if (butBlank.isSelected()) {
      if ((theDrawingArea.getSelectedCell().x > -1) &&
          (theDrawingArea.getSelectedCell().y > -1)) {
        theModel.deleteGamePiece(theDrawingArea.getSelectedCell().x,
                         theDrawingArea.getSelectedCell().y);
        theDrawingArea.clearSelectedCell();
        theDrawingArea.repaint();
      }
    } else if (butGoal.isSelected()) {
      if ((theDrawingArea.getSelectedCell().x > -1) &&
          (theDrawingArea.getSelectedCell().y > -1)
         ) {
        theModel.deleteGamePiece(theDrawingArea.getSelectedCell().x,
                         theDrawingArea.getSelectedCell().y);
        BallHolder tempBallHolder;
        tempBallHolder = new BallHolder(theSokoban);
        tempBallHolder.setLocation(theDrawingArea.getSelectedCell().x, 
                           theDrawingArea.getSelectedCell().y);
        theModel.getBallHolders().addElement(tempBallHolder);
 

        theDrawingArea.clearSelectedCell();
        theDrawingArea.repaint();
      }
    } else if (butBall.isSelected()) {
      if ((theDrawingArea.getSelectedCell().x > -1) &&
          (theDrawingArea.getSelectedCell().y > -1)
           ) {
        theModel.deleteGamePiece(theDrawingArea.getSelectedCell().x,
                         theDrawingArea.getSelectedCell().y);
        Ball tempBall;
        tempBall = new Ball(theSokoban);
        tempBall.setLocation(theDrawingArea.getSelectedCell().x, 
                             theDrawingArea.getSelectedCell().y);
        theModel.getBalls().addElement(tempBall);

        theDrawingArea.clearSelectedCell();
        theDrawingArea.repaint();
      }
    }
  }

  /**
   * actionPerformed - handles actions on buttons 
   */
  public void actionPerformed(ActionEvent e) {
    String strCommand;

    strCommand = e.getActionCommand();

    if (strCommand.equals(BUT_NEW_LABEL)) {
      newEditor();
    } else if (strCommand.equals(BUT_SAVE_LABEL)) {
      saveBoard(); 
    } else if (strCommand.equals(BUT_SAVEAS_LABEL)) {
      saveBoardAs();
    } else if (strCommand.equals(BUT_LOAD_LABEL)) {
      loadBoard();
      theDrawingArea.repaint(); 
    } else if (strCommand.equals(BUT_QUIT_LABEL)) {
      if (theSokoban != null) {
        theSokoban.quitAllEditors();
      } else {
        quit();
      }
    } else if (strCommand.equals(BUT_DELROW_LABEL)) {
      if ((theDrawingArea.getSelectedCell().x > -1) &&
          (theDrawingArea.getSelectedCell().y > -1) &&
          theDrawingArea.getHighlighted()) {
        theModel.deleteRow(theDrawingArea.getSelectedCell().y);
        if ((theDrawingArea.getSelectedCell().y) >= theModel.getMaxY()) {
          theDrawingArea.clearSelectedCell();
        }
        theDrawingArea.repaint();
      }
    } else if (strCommand.equals(BUT_DELCOL_LABEL)) {
      if ((theDrawingArea.getSelectedCell().x > -1) &&
          (theDrawingArea.getSelectedCell().y > -1)  &&
          theDrawingArea.getHighlighted()) { 
        theModel.deleteCol(theDrawingArea.getSelectedCell().x);
        if ((theDrawingArea.getSelectedCell().x) >= theModel.getMaxX()) {
          theDrawingArea.clearSelectedCell();
        }
        theDrawingArea.repaint();
      }

    } else if (strCommand.equals(BUT_PLAYER)) {
      butBall.setSelected(false);
      butWall.setSelected(false);
      butGoal.setSelected(false);
      butBlank.setSelected(false);
    } else if (strCommand.equals(BUT_BALL)) {
      butPlayer.setSelected(false);
      butWall.setSelected(false);
      butGoal.setSelected(false);
      butBlank.setSelected(false);
    } else if (strCommand.equals(BUT_WALL)) {
      butPlayer.setSelected(false);
      butBall.setSelected(false);
      butGoal.setSelected(false);
      butBlank.setSelected(false);
    } else if (strCommand.equals(BUT_GOAL)) {
      butPlayer.setSelected(false);
      butBall.setSelected(false);
      butWall.setSelected(false);
      butBlank.setSelected(false);
    } else if (strCommand.equals(BUT_BLANK)) {
      butPlayer.setSelected(false);
      butBall.setSelected(false);
      butWall.setSelected(false);
      butGoal.setSelected(false);
    }
  }

  /**
   * main - called if the game board is run from the command
   *        line (stand alone mode
   */
  public static void main(String args[]) {
    System.out.println("*** Running Game Board Editor in stand-alone mode");
    new GameBoardEditor(null);
  }
}
