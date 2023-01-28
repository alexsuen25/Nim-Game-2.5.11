import java.io.IOException;  
class Board{
  public static int pile_size;
  public static int state;
  public static int players = 0;
  public static void populate() throws IOException{
    Get.GETRequest();
    //System.out.println(pile_size);
    //System.out.println(state);
    //System.out.println(players);
    if (players == 0){
      //System.out.println("Only player currently");
      int min = (int) Math.ceil(10);
    int max = (int) Math.floor(50);
    pile_size = (int) Math.floor(Math.random() * (max - min) + min);
    //System.out.println(pile_size);
    chooseFirstPlayer();
    Post.POSTReq();
    }
    else{
      //System.out.println("Another Player already in");
      Post.POSTReq();
    }
  }
  public static void chooseFirstPlayer(){
    if ((Math.round(Math.random())) == 1){
      state = 1;
    }
    else{
      state = 0;
    }
    }
}