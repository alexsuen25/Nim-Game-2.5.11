import java.util.Scanner;
import java.io.IOException;  
import java.util.concurrent.TimeUnit;
class Game{
  private String player1_name;
  private String player2_name;
  private String current_player_name;
  public int take_away;
  public boolean game_over = false;
  public String[] spinner = new String[] {"\u0008/", "\u0008-", "\u0008\\", "\u0008|" };
  public int i = 0;
  Scanner user_input = new Scanner(System.in);
  public void chooseFirstPlayer(){
    if ((Math.round(Math.random())) == 1){
      current_player_name = player1_name;
    }
    else{
      current_player_name = player2_name;
    }
  }
  public void autoPlayer(){
    int max = (Board.pile_size / 2);
    int min = 1;
    int range = max - min + 1;
    take_away = (int)(Math.random() * range) + min;
  }
  public void getUserInput(){
    try{
        take_away = Integer.parseInt(user_input.nextLine());
      }
      catch (Exception e){
        System.out.println("Please only enter whole numbers");
        getUserInput();
      }
  }
  public void changeCurrentPlayer(){
    if (current_player_name == player1_name){
        current_player_name = player2_name;
      }
      else{
        current_player_name = player1_name;
      }
  }
  public void checkEndGame(){
    if (Board.pile_size == 1){
        System.out.println(current_player_name + " is the winner!");
        game_over = true;
      }
  }
  public void playAgain(){
    System.out.println("Do you want to play again? [Y/N]");
    String response = user_input.nextLine();
    if (response.toLowerCase().equals("y")){
      
      game_over = false;
      try{
        Get.GETRequest();
        if (Board.players == 1){
          Board.populate();
        }
        else{
          Board.pile_size = 0;
          Board.players = 0;
          Post.POSTReq();
          Board.populate();
        }
      }
      catch(Exception e){
        System.out.println("Hello");
      }
      String p = "Hello";
      try {
  play();
}
catch(IOException e) {
  System.out.println("Hello");
}
    }
    else{
      System.out.println("Thank you for playing!");
    }
  }
  public void online_play() throws IOException{

    if (Board.state != 3){
      int output_counter = 0;
      while ((Board.state != 0) && (Board.state != 3)){
        if (output_counter == 0){
          System.out.print("The other player is choosing  ");
          System.out.printf("%s", spinner[i % spinner.length]);
          output_counter += 1;
          i += 1;
        }
        else{
          System.out.printf("%s", spinner[i % spinner.length]);
          i += 1;
        }
      Get.GETRequest();
      try{
        TimeUnit.MILLISECONDS.sleep(500);
      }
      catch(Exception e){
        System.out.println("Hello");
      }
      }
      System.out.println("\u0008 ");
      if (Board.state != 3){
        System.out.println("The pile size is " + Board.pile_size + ".");
        System.out.println("How much do you want to take away " + player1_name);
      getUserInput();
      while ((take_away > (Board.pile_size / 2)) || (take_away < 1)){
        System.out.println("Please choose a number that is half or less than the pile size and greater than 0");
        getUserInput();
      }
      Board.pile_size = Board.pile_size - take_away;
      if (Board.pile_size == 1){
        Board.state = 3;
        Post.POSTReq();
        System.out.println("You have won congratulations!");
        playAgain();
      }
      else{
        Board.state = 1;
        Post.POSTReq();
        online_play();
      }
      }
      else{
        System.out.println("The other player has won sorry.");
        playAgain();
      }
      
    }
    else{
      System.out.println("The other player has won sorry.");
      playAgain();
    }
    //System.out.println("exiting out of the loop");
  }
  public void play() throws IOException{
    System.out.println("Would you like to play online or local");
    String res = user_input.nextLine();
    int output_counter = 0;
    if (res.toLowerCase().equals("online")){
      Board.players += 1;
      Post.POSTReq();
      System.out.println("Please enter your name Player");
      player1_name = user_input.nextLine();
      while (Board.players < 2){
        if (output_counter == 0){
          System.out.print("Waiting for other player to join  ");
          System.out.printf("%s", spinner[i % spinner.length]);
          output_counter += 1;
          i += 1;
        }
        else{
          System.out.printf("%s", spinner[i % spinner.length]);
          i += 1;
        }
      try{
        TimeUnit.MILLISECONDS.sleep(500);
        Get.GETRequest();
      }
      catch(Exception e){
        System.out.println("Hello");
      }
      }
      System.out.println("\u0008 ");

      online_play();
    }
    else{
    System.out.println("Please enter your name Player 1");
    player1_name = user_input.nextLine();
    System.out.println("Please enter your name Player 2");
    player2_name = user_input.nextLine();
    if (player2_name.equals("")){
      player2_name = "Robot";
    }
    System.out.println(player1_name + player2_name);
    System.out.print(res.toLowerCase());
    System.out.println(Board.pile_size);
    System.out.println(Board.state);
    chooseFirstPlayer();
    while (game_over != true){
      System.out.println("The pile size is " + Board.pile_size);
      if (current_player_name == "Robot"){
        System.out.println(current_player_name + " is choosing");
        autoPlayer();
      }
      else{
        System.out.println(current_player_name + " how much do you want to take from the pile");
      getUserInput();
      while ((take_away > (Board.pile_size / 2)) || (take_away < 1)){
        System.out.println("Please choose a number that is half or less than the pile size and greater than 0");
        getUserInput();
        }
      }
      //System.out.println(take_away);
      Board.pile_size = Board.pile_size - take_away;
      //System.out.println(Board.pile_size);
      checkEndGame();
      changeCurrentPlayer();
    }
    playAgain();
    }
    
  }
  
}