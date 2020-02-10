package silhouette.properties;

import silhouette.shapes.*;
import silhouette.properties.*;
import java.util.Vector;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Enumeration;

public class CompositeShapeTester {
  protected static final double feedbackDepth = 0.0;
  protected static final double gateDepth = 2.0;
  protected static final double lineDepth = 3.0;
  protected static final int numShapes = 20;

  public CompositeShapeTester() {
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
      System.out.println();
    }
  }

  public static void main(String[] args) {
    CompositeShapeTester vZListTester = new CompositeShapeTester();
    vZListTester.invokedStandalone = true;

    List checkList = new LinkedList();
    CompositeShape testList = new CompositeShape();
    Vector testObjects = new Vector(numShapes);
    RectangleShape r = null;

    testObjects.setSize(numShapes);
    for (int i = 0; i < numShapes; i++) {
      r = new RectangleShape();
      r.setName("Rectangle" + i);
      testObjects.setElementAt(r, i);
    }

    // test the zList's add methods
    testList.add((SilShape)testObjects.get(0));
    testList.add((SilShape)testObjects.get(1));
    testList.add((SilShape)testObjects.get(2), "Rectangle2");
    testList.add((SilShape)testObjects.get(3), "Rectangle3");
    testList.add((SilShape)testObjects.get(4));
    testList.add((SilShape)testObjects.get(5));
    testList.add((SilShape)testObjects.get(6), "Rectangle6", 1);
    testList.add((SilShape)testObjects.get(7), 0);
    testList.add((SilShape)testObjects.get(8), "Rectangle8", 1);

    // determine if the add methods worked
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(1));
    checkList.add(testObjects.get(2));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(5));
    compare(testList.getShapes(),
            testList.getShapes(), checkList, 1);

    // determine if the bringToFront command works
    testList.bringToFront((SilShape)testObjects.get(2));
    testList.bringToFront((SilShape)testObjects.get(1));
    testList.bringToFront((SilShape)testObjects.get(3));

    checkList.clear();
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(5));
    checkList.add(testObjects.get(2));
    checkList.add(testObjects.get(1));
    checkList.add(testObjects.get(3));
    compare(testList.getShapes(),
            testList.getShapes(), checkList, 2);

    // determine if the sendToBack command works
    testList.sendToBack((SilShape)testObjects.get(3));
    testList.sendToBack((SilShape)testObjects.get(4));
    testList.sendToBack((SilShape)testObjects.get(8));

    checkList.clear();
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(5));
    checkList.add(testObjects.get(2));
    checkList.add(testObjects.get(1));
    compare(testList.getShapes(),
            testList.getShapes(), checkList, 3);

    // determine if the remove command works
    testList.remove((SilShape)testObjects.get(4));
    testList.remove((SilShape)testObjects.get(1));
    testList.remove((SilShape)testObjects.get(0));
    testList.remove((SilShape)testObjects.get(5));

    checkList.clear();
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(2));
    compare(testList.getShapes(),
            testList.getShapes(), checkList, 4);

    // now add the objects back at different places
    testList.add((SilShape)testObjects.get(4), 2);
    testList.add((SilShape)testObjects.get(1));
    testList.add((SilShape)testObjects.get(0), 0);
    testList.add((SilShape)testObjects.get(5));

    checkList.clear();
    checkList.add(testObjects.get(0));
    checkList.add(testObjects.get(8));
    checkList.add(testObjects.get(3));
    checkList.add(testObjects.get(4));
    checkList.add(testObjects.get(7));
    checkList.add(testObjects.get(6));
    checkList.add(testObjects.get(2));
    checkList.add(testObjects.get(1));
    checkList.add(testObjects.get(5));
    compare(testList.getShapes(),
            testList.getShapes(), checkList, 5);

    // test the getShape method
    compare(testList.getShape("Rectangle3"), testObjects.get(3), 6);
    compare(testList.getShape("Rectangle0"), testObjects.get(0), 6);
    compare(testList.getShape("Rectangle5"), testObjects.get(5), 6);

    // test the getShapeIndex and getShapeDepth methods
    compare(testList.getShapeIndex((SilShape)testObjects.get(3)), 2, 7);
    compare(testList.getShapeIndex((SilShape)testObjects.get(1)), 7, 7);
    compare(testList.getShapeIndex((SilShape)testObjects.get(5)), 8, 7);
  }

  private boolean invokedStandalone = false;
} 