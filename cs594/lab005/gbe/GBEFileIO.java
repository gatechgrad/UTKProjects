import java.io.*;
import java.util.Vector;

public class GBEFileIO extends BoardReader {

  /*** CONSTANTS ***/
  
  /*** INSTANCE VARIABLES ***/
//  private Vector vectTxtBoard;

  public GBEFileIO(String str) {
    super(str, null);
  }

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

/*
  public void writeFile(String strFileName) {

  }

  public void readFile(String strFile) {
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
*/
}
