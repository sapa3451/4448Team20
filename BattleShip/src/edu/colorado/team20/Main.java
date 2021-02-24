package edu.colorado.team20;

public class Main {

    public static void main(String[] args) {
        // write your code here
//         Ship ship = new Ship();
//         ship.show();

        // creating a GameManagement object to start game
        GameManagement game = new GameManagement();
        Player player = new Player();
        ComputerPlayer cp = new ComputerPlayer();
        Board playerBoard = new Board('P');
        Board compBoard = new Board('C');

        // we are going to have to make double ships and label them as computer and player ships
        Ship playerMinesweeper = new Ship("minesweeper",2);
        Ship playerDestroyer = new Ship("destroyer",3);
        Ship playerBattleship = new Ship("battleship",4);
        Ship compMinesweeper = new Ship("minesweeper",2);
        Ship compDestroyer = new Ship("destroyer",3);
        Ship compBattleship = new Ship("battleship",4);


        // display the start game info
        game.BeginGameDisplay();
        //**THIS IS THE PLAYERS BOARD BEING DISPLAYED**
        playerBoard.Show();

//        player.GetShipPlacement(playerMinesweeper);
//        player.GetShipPlacement(playerDestroyer);
//        player.GetShipPlacement(playerBattleship);
        //The parameter here is insuring the ships are being placed on the player board only
        String result = playerBoard.SetShipPos(playerMinesweeper);
        System.out.println(result);
        //TODO: SetShipPos to be moved into the get placement functions in both player and computer player
        result = playerBoard.SetShipPos(playerDestroyer);
        System.out.println(result);
        result = playerBoard.SetShipPos(playerBattleship);
        System.out.println(result);
        //**THIS IS THE PLAYERS BOARD BEING DISPLAYED**
        playerBoard.Show();

        //computer player ship placement here

        // May need to implement loop to switch between player and computer turns
        player.GetDecisionShot(compBoard); // need to send in opponent's board for player shot

        // Testing Board class
        //**THIS IS THE COMPUTERS BOARD BEING DISPLAYED**
        //ships are hidden on this board
        compBoard.Show(); // want to show player the computer board after shot

        // Testing computer player class
        String cpTurn = cp.RandomShot(playerBoard);
        System.out.println("The computer has taken their turn"); // make this into a method to notify player
        System.out.println();
        // need to make the cpTurn call MarkBoard method in the computer choice function, not in main
        // board.MarkBoard(cpTurn);

    }
}