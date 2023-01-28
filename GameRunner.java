import java.io.IOException; 
import java.util.concurrent.TimeUnit;

class GameRunner {
  public static void main(String argvs[]) throws IOException {
    System.out.println("Welcome to the Game of Nim!");
    try{
      Board.populate();
    }
    catch(IOException e){
      System.out.println("Hello");
    }
    
    Game nim = new Game();
    String p = "Hello";
    try {
  nim.play();
}
catch(IOException e) {
  System.out.println("Hello");
}
  }
}