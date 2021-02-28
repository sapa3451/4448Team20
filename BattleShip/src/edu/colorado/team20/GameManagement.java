package edu.colorado.team20;

public class GameManagement {
    // provides turn information
    // P --> user player turn
    // C --> computer turn
    private char turnInfo;

    public GameManagement() {
        turnInfo = 'P'; // set to player first always
    }

    public void BeginGame() {
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");

        Ship[] fleet = {battleship, destroyer, minesweeper};

        PlayerBoard playerBoard = new PlayerBoard(fleet);
        ComputerBoard computerBoard = new ComputerBoard(fleet);

        IPlayer player = new UserPlayer(playerBoard);
        IPlayer computer = new ComputerPlayer(computerBoard);

        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        System.out.println();

        System.out.println("You will place your ships, and then you will fire the first shot to being your battle. Good luck!");
        System.out.println();
        System.out.println();

        computer.placeBattleship();
        computer.placeDestroyer();
        computer.placeMinesweeper();

        player.placeBattleship();
        player.placeDestroyer();
        player.placeMinesweeper();

        player.Shot(computer.getBoard(),'Z', -1);

        System.out.println("The computer is now taking their shot!");
        System.out.println();

        computer.Shot(player.getBoard(), 'Z', -1);

    }

    // change the turn
    public void ChangeTurn() {
        // change the turn to either player or computer
        turnInfo  = (turnInfo == 'P') ? 'C' : 'P';
    }

    // method to return turn info
    public char GetTurn(){
        return turnInfo;
    }


    //TODO: Add end game check, more functions
}
