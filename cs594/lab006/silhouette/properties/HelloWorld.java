package silhouette.properties;

public class HelloWorld {

  public HelloWorld() {
  }

  public static void main(String[] args) {
    HelloWorld helloWorld = new HelloWorld();
    helloWorld.invokedStandalone = true;
    System.out.println("Hello World");
  }
  private boolean invokedStandalone = false;
}