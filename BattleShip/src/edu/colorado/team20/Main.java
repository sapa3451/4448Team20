package edu.colorado.team20;
  
public class Main {

    public static void main(String[] args) {
        // write your code here
//         Ship ship = new Ship();
//         ship.show();

        // creating a GameManagement object to start game
        GameManagement game = new GameManagement();
        Player player = new Player();
        Board playerBoard = new Board('P');
        Board compBoard = new Board('C');

        // we are going to have to make double ships and label them as computer and player ships
        Ship minesweeper = new Ship("minesweeper",2);
        Ship destroyer = new Ship("destroyer",3);
        Ship battleship = new Ship("battleship",4);


        // display the start game info
        game.BeginGameDisplay();

        // May need to implement loop to switch between player and computer turns
        player.GetDecisionShot(compBoard); // need to send in opponent's board for player shot

        // Testing Board class
        playerBoard.Show();

        // Testing computer player class
         ComputerPlayer cp = new ComputerPlayer();
         String cpTurn = cp.RandomShot();
         System.out.println("The computer has taken their turn"); // make this into a method to notify player
         System.out.println();
         // need to make the cpTurn call MarkBoard method in the computer choice function, not in main
        // board.MarkBoard(cpTurn);

    }
}
