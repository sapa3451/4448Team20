package edu.colorado.team20;
  
public class Main {

    public static void main(String[] args) {
        // write your code here
//         Ship ship = new Ship();
//         ship.show();

        // creating a GameManagement object to start game
        GameManagement game = new GameManagement();
        Player player = new Player();

        // display the start game info
        game.BeginGameDisplay();

        // May need to implement loop to switch between player and computer turns
        player.GetDecisionShot();

        // Testing Board class
         Board board = new Board();
         board.Show();

    }
}
