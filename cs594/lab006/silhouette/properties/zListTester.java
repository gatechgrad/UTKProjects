package silhouette.properties;

import silhouette.shapes.*;
import java.util.Vector;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Enumeration;

public class zListTester {
  protected static final double feedbackDepth = 0.0;
  protected static final double gateDepth = 2.0;
  protected static final double lineDepth = 3.0;
  protected static final int numShapes = 20;

  public zListTester() {
  }

  public static void compare(int testValue, int answerValue, int test) {
    if (testValue != answerValue) {
      System.out.println("test " + test + " failed");
      System.out.println("    correct value = " + answerValue +
                          "    wrong value = " + testValue);
    }
  }

  public static void compare(double testValue, double answerValue, int test) {
    if (testValue != answerValue) {
      System.out.println("test " + test + " failed");
      System.out.println("    correct value = " + answerValue +
                          "    wrong value = " + testValue);
    }
  }
  public static void compare(Object testObject, Object answerObject, int test) {
    if (testObject != answerObject) {
      System.out.println("test " + test + " failed");
      System.out.println("    correct object = " +
                          ((SilShape)answerObject).getName() +
                          "    wrong object = " +
                          ((testObject == null) ? "null" :
                               ((SilShape)testObject).getName()));
    }
  }

  public static void compare(Enumeration testList, Enumeration backupTestList,
                        List answerList, int test) {
    Iterator answerIter = answerList.iterator();
    boolean listsEqual = true;
    while (answerIter.hasNext() && testList.hasMoreElements ()) {
      if (answerIter.next() != testList.nextElement ()) {
        listsEqual = false;
        break;
      }
    }
    if (answerIter.hasNext() || testList.hasMoreElements ())
      listsEqual = false;
    if (!listsEqual) {
      System.out.println("test " + test + " failed");
      System.out.print("    The list should have had: ");
      answerIter = answerList.iterator();
      while (answerIter.hasNext()) {
        SilShape shape = (SilShape)(answerIter.next());
        System.out.print(shape.getName() + "  ");
      }
      System.out.println();
      System.out.print("    The list actually had: ");
      while (backupTestList.hasMoreElements ()) {
        SilShape shape = (SilShape)(backupTestList.nextElement ());
        System.out.print(shape.getName() + "  ");
      }
    }
  }

  public static void main(String[] args) {
    zListTester vZListTester = new zListTester();
    vZListTester.invokedStandalone = true;

    List checkList = new LinkedList();
    zList testList = new zList();
    Vector testObjects = new Vector(numShapes+1);
    RectangleShape r = null;

    testObjects.setSize(numShapes);
    for (int i = 0; i < numShapes; i++) {
      r = new RectangleShape();
      r.setName("Rectangle" + i);
      testObjects.setElementAt(r, i);
    }

    // test the zList's add methods
    testList.add((SilShape)testObjects.get(0), CanvasShape.DefaultDepth);
    testList.add((SilShape)testObjects.get(1), gateDepth);
    testList.add((SilShape)testObjects.get(2), feedbackDepth);
    testList.add((SilShape)testObjects.get(3), CanvasShape.DefaultDepth);
    testList.add((SilShape)testObjects.get(4), feedbackDepth);
    testList.add((SilShape)testObjects.get(5), feedbackDepth);
    testList.add((SilShape)testObjects.get(6), CanvasShape.DefaultDepth, 1);
    testList.add((SilShape)testObjects.get(7), feedbackDepth, 0);
    testList.add((SilShape)testObjects.get(8), gateDepth, 1);

    // determine if the add methods worked
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(3));
    compare(testList.getShapes(CanvasShape.DefaultDepth),
            testList.getShapes(CanvasShape.DefaultDepth), checkList, 1);

    checkList.clear();
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(2));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(5));
    compare(testList.getShapes(feedbackDepth),
            testList.getShapes(feedbackDepth), checkList, 1);

    checkList.clear();
    checkList.add(testObjects.get(1));
    checkList.add(testObjects.get(8));
    compare(testList.getShapes(gateDepth),
            testList.getShapes(gateDepth), checkList, 1);

    // determine if the zlist iterator works
    checkList.clear();
    checkList.add(testObjects.get(1));
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(2));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(5));

    compare(testList.getShapes(),
            testList.getShapes(), checkList, 2);

    // determine if the bringToFront command works
    testList.bringToFront((SilShape)testObjects.get(2));
    testList.bringToFront((SilShape)testObjects.get(1));
    testList.bringToFront((SilShape)testObjects.get(3));

    checkList.clear();
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(1));
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(5));
    checkList.add(testObjects.get(2));

    compare(testList.getShapes(),
            testList.getShapes(), checkList, 3);

    // determine if the sendToBack command works
    testList.sendToBack((SilShape)testObjects.get(3));
    testList.sendToBack((SilShape)testObjects.get(4));
    testList.sendToBack((SilShape)testObjects.get(8));

    checkList.clear();

    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(1));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(5));
    checkList.add(testObjects.get(2));
    compare(testList.getShapes(),
            testList.getShapes(), checkList, 4);

    // determine if the remove command works
    testList.remove((SilShape)testObjects.get(4));
    testList.remove((SilShape)testObjects.get(1));
    testList.remove((SilShape)testObjects.get(0));
    testList.remove((SilShape)testObjects.get(5));

    checkList.clear();
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(2));

    compare(testList.getShapes(),
            testList.getShapes(), checkList, 5);

    // now add the objects back at different levels
    testList.add((SilShape)testObjects.get(4), lineDepth);
    testList.add((SilShape)testObjects.get(1), feedbackDepth);
    testList.add((SilShape)testObjects.get(0), feedbackDepth, 0);
    testList.add((SilShape)testObjects.get(5), lineDepth);

    checkList.clear();

    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(5));
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(2));
    checkList.add(testObjects.get(1));

    compare(testList.getShapes(),
            testList.getShapes(), checkList, 6);

    // test the getShape method
    compare(testList.getShape("Rectangle3"), testObjects.get(3), 7);
    compare(testList.getShape("Rectangle0"), testObjects.get(0), 7);
    compare(testList.getShape("Rectangle5"), testObjects.get(5), 7);

    // test the getShapeIndex and getShapeDepth methods
    compare(testList.getShapeIndex((SilShape)testObjects.get(3)), 0, 8);
    compare(testList.getShapeDepth((SilShape)testObjects.get(3)), CanvasShape.DefaultDepth, 8);
    compare(testList.getShapeIndex((SilShape)testObjects.get(1)), 3, 8);
    compare(testList.getShapeDepth((SilShape)testObjects.get(1)), feedbackDepth, 8);
    compare(testList.getShapeIndex((SilShape)testObjects.get(5)), 1, 8);
    compare(testList.getShapeDepth((SilShape)testObjects.get(5)), lineDepth, 8);
  }

  private boolean invokedStandalone = false;
}