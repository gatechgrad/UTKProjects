import java.io.*;
import java.util.Vector;

public class BoardReader {

  /*** CONSTANTS ***/
  public static final char PLAYER_CHAR = '@';
  public static final char WALL_CHAR = '#';
  public static final char BALLHOLDER_CHAR = '.';
  public static final char BALL_CHAR = '$';
  

  /*** INSTANCE VARIABLES ***/
  sokoban theSB;

  private Vector vectTxtBoard;

  public BoardReader(String str, sokoban sb) {
    theSB = sb;

    if (str != null) {
      readBoard(str);
    }
  }


  private void readBoard(String strFile) {
    String strLine;

    vectTxtBoard = new Vector();

    try {
      BufferedReader br = new BufferedReader(new FileReader(strFile));


      while ((strLine = br.readLine()) != null) {
        System.out.println(">>" + strLine);
        vectTxtBoard.addElement(strLine);
      }
      br.close();

    } catch (IOException e) {
      System.out.println("*** Error: Could not read file " + strFile + "\n");
    }
  }

  /**
   * convert the Vector of Strings to a Board object containing game
   * objects
   */
  public BoardData getBoardData() {
    BoardData b;
    Ball tempBall;
    BallHolder tempBallHolder;
    int i, j;
    String strLine;
    int iMaxLineSize;

    b = new BoardData(theSB);

    iMaxLineSize = 0;
    for (i = 0; i < vectTxtBoard.size(); i++) {
      if (   ((String) vectTxtBoard.elementAt(i)).length() > iMaxLineSize) {
        iMaxLineSize = ((String) vectTxtBoard.elementAt(i)).length(); 
      }
    }
    b.setBoardSize(iMaxLineSize, vectTxtBoard.size());
  
    for (i = 0; i < vectTxtBoard.size(); i++) {
      strLine = (String) vectTxtBoard.elementAt(i);
      for (j = 0; j < strLine.length(); j++) {
        //If the character is '#' then make a wall
        if (strLine.charAt(j) == WALL_CHAR) {
          b.getTile(j, i).setPassable(false);
        //If the character is '@' the set the player 
        } else if (strLine.charAt(j) == PLAYER_CHAR) {
          b.getPlayer().setPlayerLocation(j, i); 
        } else if (strLine.charAt(j) == BALL_CHAR) {
          tempBall = new Ball();
          tempBall.setLocation(j, i);
          b.getBalls().addElement(tempBall);
        } else if (strLine.charAt(j) == BALLHOLDER_CHAR) {
          tempBallHolder = new BallHolder();
          tempBallHolder.setLocation(j, i);
          b.getBallHolders().addElement(tempBallHolder);
        }
      }
    } 
   

    return b;
  }

}
