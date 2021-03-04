package edu.colorado.team20;

import edu.colorado.team20.Board;

public class GameManagement {
    // provides turn information
    // P --> user player turn
    // C --> computer turn
    private int turnNum;
    private char turnInfo;
    int idNum = 1;

    public GameManagement() {
        turnInfo = 'P'; // set to player first always
        turnNum = 1; // initialize first round
    }

    public void BeginGame() {
        Ship Pbattleship = new Battleship(4, "battleship");
        Ship Pdestroyer = new Destroyer(3, "destroyer");
        Ship Pminesweeper = new Minesweeper(2, "minesweeper");
        Ship Cbattleship = new Battleship(4, "battleship");
        Ship Cdestroyer = new Destroyer(3, "destroyer");
        Ship Cminesweeper = new Minesweeper(2, "minesweeper");

        Ship[] playerFleet = {Pbattleship, Pdestroyer, Pminesweeper};
        Ship[] compFleet = {Cbattleship, Cdestroyer, Cminesweeper};

        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player player = new UserPlayer(playerBoard);
        Player computer = new ComputerPlayer(computerBoard);

        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        System.out.println();

        System.out.println("You will place your ships, and then you will fire the first shot to being your battle. Good luck!");
        System.out.println();
        System.out.println();

        // give ships ids and place them
        for (Ship ship : compFleet) {
            ship.setId(idNum);
            idNum++;
            computer.performPlacement(ship.getId(), ship.getSize());
            computer.getBoard().registerShip(ship);
        }

        computer.getBoard().setShowBehavior(new SonarBoardShow('E',3));
        computer.getBoard().performShow();
        computer.getBoard().setShowBehavior(new HiddenBoardShow());

        // give ships ids and place them
        for (Ship ship : playerFleet) {
            ship.setId(idNum);
            idNum++;
            player.performPlacement(ship.getId(), ship.getSize());
            player.getBoard().registerShip(ship);
        }

        player.performShot(computer.getBoard(), 'Z', -1, this.turnNum);

        System.out.println("The computer is now taking their shot!");
        System.out.println();

        computer.performShot(player.getBoard(), 'Z', -1, this.turnNum);

        // TODO: make sure when round ends add one to the turnNum!

    }

    // change the turn
    public void ChangeTurn() {
        // change the turn to either player or computer
        turnInfo = (turnInfo == 'P') ? 'C' : 'P';
    }

    // method to return turn info
    public char GetTurn() {
        return turnInfo;
    }


    //TODO: Add end game check, more functions
    public boolean EndGame(Ship[] playerFleet, Ship[] compFleet) {
        int playerCount = 0;
        int compCount = 0;
        for (int i = 0; i < playerFleet.length; i++) {
            if (playerFleet[i].checkSunk(playerFleet[i].getSize())) {
                playerCount++;
            }
            if (compFleet[i].checkSunk(compFleet[i].getSize())) {
                compCount++;
            }
        }
        return playerCount == playerFleet.length || compCount == compFleet.length;
    }

    public int getTurnNum() { return turnNum; }
    public int getIdNum() { return this.idNum; }
    public void setIdNum() { this.idNum++; }

}
