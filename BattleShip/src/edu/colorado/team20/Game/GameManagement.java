package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserRandomShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import edu.colorado.team20.Ship.*;

import java.util.Scanner;

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
        Ship Psub = new Submarine(5, "submarine");
        Ship Cbattleship = new Battleship(4, "battleship");
        Ship Cdestroyer = new Destroyer(3, "destroyer");
        Ship Cminesweeper = new Minesweeper(2, "minesweeper");
        Ship Csub = new Submarine(5, "submarine");

        Ship[] playerFleet = {Pbattleship, Pdestroyer, Pminesweeper, Psub};
        Ship[] compFleet = {Cbattleship, Cdestroyer, Cminesweeper, Csub};

        Board playerSurfaceBoard = new SurfaceBoard();
        Board computerSurfaceBoard = new SurfaceBoard();
        Board computerUnderWaterBoard = new UnderwaterBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();

        computerSurfaceBoard.setShowBehavior(new SurfaceHiddenBoardShow());
        computerUnderWaterBoard.setShowBehavior(new UnderwaterHiddenBoardShow());

        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        Board[] computerBoards = new Board[]{computerSurfaceBoard, computerUnderWaterBoard};


        Player player = new UserPlayer(playerBoards);
        Player computer = new ComputerPlayer(computerBoards);

        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        System.out.println();

        System.out.println("You will place your ships, and then you will fire the first shot to being your battle. Good luck!");
        System.out.println();
        System.out.println();

        // give ships ids and place them
        for (Ship ship : compFleet) {
            if (ship.getUnderwater() == false) {
                ship.setId(idNum);
                idNum++;
                computer.getBoards()[1].setCreateShipCoordinatesBehavior(new RegularShipCoordinates());
                computer.performSurfacePlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                computer.getBoards()[0].registerShip(ship);
            }
            else {
                ship.setId(idNum);
                idNum++;
                computer.getBoards()[1].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
                computer.performUnderwaterPlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                computer.getBoards()[1].registerShip(ship);
            }
        }


        // give ships ids and place them
        for (Ship ship : playerFleet) {
            if (ship.getUnderwater() == false) {
                ship.setId(idNum);
                idNum++;
                player.getBoards()[0].setCreateShipCoordinatesBehavior(new RegularShipCoordinates());
                player.performSurfacePlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                player.getBoards()[0].registerShip(ship);
            }
            else {
                ship.setId(idNum);
                idNum++;
                player.getBoards()[1].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
                player.performUnderwaterPlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                player.getBoards()[1].registerShip(ship);
            }
        }

        // set up of game is now done. Begin taking turns. Implementing sonar pulse
        boolean firstSunkComputer = false;
        boolean firstSunkPlayer = false;
        int sonarUses = 2;
        while(!EndGame(playerFleet,compFleet)){
                player.performShot(computer.getBoards(), 'Z', -1, this.turnNum);
                ChangeTurn();
//                player.getBoard().performShow();
                computer.performShot(player.getBoards(), 'Z', -1, this.turnNum);
                ChangeTurn();
            // round over updating turnNum
            turnNum++;

            if(!firstSunkComputer){ // loop through comp's fleet to find at least one sunk ship
                for (Ship ship : compFleet) {
                    if (ship.checkSunk()) {
                        firstSunkComputer = true;
                        break;
                    }
                }
            }

            if(!firstSunkPlayer){ // loop through players fleet to find at least one sunk ship
                for (Ship ship : playerFleet) {
                    if (ship.checkSunk()) {
                        firstSunkPlayer = true;
                        computer.setShotBehavior(new LaserRandomShot());
                        break;
                    }
                }
            }

            // checking to see if the player has sunk at least one ship from comp
            // this is to ask if they want to use a sonar
            if (firstSunkComputer && sonarUses!=0){ // ask about sonar use since first ship is sunk
                player.setShotBehavior(new LaserInputShot());
                System.out.println("Because you sunk your first opponent's ship, would you like to use a sonar pulse?");
                System.out.println();
                // take in user input
                Scanner sc = new Scanner(System.in); //System.in is a standard input stream
                String input = sc.nextLine();    //reads string
                input = input.toUpperCase(); // set to uppercase
                if (input.equals("YES")) { // need to check for bad input
                    // use sonarBoardShow
                    char colVal = ' ';
                    int rowVal = -1;
                    System.out.println("Type which column (A-J) you would like your sonar pulse center to be: ");
                    // take in user input
                    input = sc.nextLine();    //reads string
                    input = input.toUpperCase(); // set to uppercase
                    // we want to check input is okay for column
                    boolean correct = false;
                    while (!correct) {
                        // check if valid input
                        if (input.length() == 1) { // check if single letter
                            char[] col = input.toCharArray(); // set to char array
                            if (col[0] >= 'A' && col[0] <= 'J') {  // check if valid column input
                                correct = true;
                                // set column value
                                colVal = col[0];
                            }
                        }
                        // invalid input
                        if (!correct) {
                            System.out.println("Invalid column! Please enter a valid column (A-J): ");
                            input = sc.nextLine(); // Read user input
                            input = input.toUpperCase(); // set to uppercase
                        }
                    }

                    System.out.println("Type which row (1-10) you would like your sonar pulse center to be: ");

                    // take in user input
                    input = sc.nextLine();

                    // we want to check input is okay for column
                    correct = false;
                    while (!correct) {
                        // check if valid input
                        try {
                            // checking valid integer using parseInt() method
                            rowVal = Integer.parseInt(input); // set value
                        }
                        catch (NumberFormatException e) { // throw error and get input again
                            System.out.println("Invalid row! Please enter a valid row (1-10):");
                            input = sc.nextLine(); // Read user input
                        }

                        if (rowVal > 0 && rowVal <= 10) { // check if row value is within board
                            correct = true;
                        }
                        else {
                            System.out.println("Invalid row! Please enter a valid row (1-10):");
                            input = sc.nextLine(); // Read user input
                        }
                    }
                    // got a valid row and col
                    computer.getBoards()[0].setShowBehavior(new SonarBoardShow(colVal,rowVal));
                    computer.getBoards()[0].performShow();
                    computer.getBoards()[0].setShowBehavior(new SurfaceHiddenBoardShow());
                    // remove one sonar use
                    sonarUses--;
                }
            }
        }

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
            if (playerFleet[i].checkSunk()) {
                playerCount++;
            }
            if (compFleet[i].checkSunk()) {
                compCount++;
            }
        }
        return playerCount == playerFleet.length || compCount == compFleet.length;
    }

    public int getIdNum() { return this.idNum; }
    public void setIdNum() { this.idNum++; }

}
