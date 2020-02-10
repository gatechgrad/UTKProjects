/**
 * GBEFileIO.java - handles the reading and writing of game 
 *                board files 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 28, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.io.*;
import java.util.Vector;

public class GBEFileIO extends BoardReader {


  /** 
   * GBEFileIO - constructor
   */
  public GBEFileIO(String str) {
    super(str, null);
  }

  /**
   * writeFile - writes the collection strings representing the
   *             game board to a file 
   */
  public void writeFile(String strFile) {
    PrintWriter pw;
    File f;
    int i;
 
    f = new File(strFile);
    try {
      pw = new PrintWriter(new FileWriter(f));
   
      for (i = 0; i < vectTxtBoard.size(); i++) {
        pw.println((String) vectTxtBoard.elementAt(i));  
      }
      pw.close();
   } catch (IOException e) { }
  }


  /**
   * createBlankBoard - creates a collection of blank strings
   *                    representing a new gameboard
   */ 
  public void createBlankBoard(int iRows, int iCols) {
    int i;
    String strLine;

    vectTxtBoard = new Vector();
    
    strLine = "";
    for (i = 0; i < iCols; i++) {
      strLine += " ";
    }    

    for (i = 0; i < iRows; i++) {
      vectTxtBoard.addElement(strLine);
    }

  }


  /**
   * convertModelToText - replaces all of the objects in the
   *                      model with character representations
   *                      and loads them into the collection of
   *                      Strings
   */
  public void convertModelToText(BoardData theModel) {
    int i, j;
    String strLine;
    vectTxtBoard = new Vector();

    for (i = 0; i < theModel.getMaxY(); i++) {
      strLine = "";  
      for (j = 0; j < theModel.getMaxX(); j++) {
        if (theModel.getTile(j, i).getPassable() == false) {
          strLine += "#";
        } else if (theModel.getBallAt(j, i) != null) {
          strLine += "$";
        } else if (theModel.getBallHolderAt(j, i) != null) {
          strLine += ".";
        } else if ((theModel.getPlayer().getPlayerLocX() == j) &&
                   (theModel.getPlayer().getPlayerLocY() == i)
                  ) {
          strLine += "@";
        } else {
          strLine += " ";
        }
      }
      vectTxtBoard.addElement(strLine);
      System.out.println(strLine);
    } 
  }

}
