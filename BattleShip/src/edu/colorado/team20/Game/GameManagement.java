package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.HiddenBoardShow;
import edu.colorado.team20.Board.Interfaces.Behaviors.SonarBoardShow;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import edu.colorado.team20.Ship.Battleship;
import edu.colorado.team20.Ship.Destroyer;
import edu.colorado.team20.Ship.Minesweeper;
import edu.colorado.team20.Ship.Ship;

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

        // testing
//        computer.getBoard().setShowBehavior(new SonarBoardShow('E',3));
//        computer.getBoard().performShow();
//        computer.getBoard().setShowBehavior(new HiddenBoardShow());

        // give ships ids and place them
        for (Ship ship : playerFleet) {
            ship.setId(idNum);
            idNum++;
            player.performPlacement(ship.getId(), ship.getSize());
            player.getBoard().registerShip(ship);
        }

        // set up of game is now done. Begin taking turns. Implementing sonar pulse
        boolean firstSunk = false;
        int sonarUses = 2;
        while(!EndGame(playerFleet,compFleet)){
                player.performShot(computer.getBoard(), 'Z', -1, this.turnNum);
                ChangeTurn();
//                player.getBoard().performShow();
                computer.performShot(player.getBoard(), 'Z', -1, this.turnNum);
                ChangeTurn();
            // round over updating turnNum
            turnNum++;

            if(!firstSunk){ // loop through comp's fleet to find at least one sunk ship
                for(int i = 0; i < compFleet.length; i++) {
                    if (compFleet[i].checkSunk()) {
                        firstSunk = true;
                        break;
                    }
                }
            }

            // checking to see if the player has sunk at least one ship from comp
            // this is to ask if they want to use a sonar
            if (firstSunk && sonarUses!=0){ // ask about sonar use since first ship is sunk
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
                    computer.getBoard().setShowBehavior(new SonarBoardShow(colVal,rowVal));
                    computer.getBoard().performShow();
                    computer.getBoard().setShowBehavior(new HiddenBoardShow());
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
