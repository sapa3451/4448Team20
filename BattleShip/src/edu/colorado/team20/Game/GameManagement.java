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
    // C --> this.computer turn
    private int turnNum;
    private char turnInfo;
    int idNum = 1;
    //FleetAttributes;
    private FleetFactory fleetFactory;
    private Ship[] playerFleet;
    private Ship[] compFleet;
    //Change Boards to attributes
    private BoardSetFactory boardSetFactory;
    private Board[] playerBoards;
    private Board[] computerBoards;
    //Change Users to attributes
    private Player player;
    private Player computer;

    public GameManagement() {
        turnInfo = 'P'; // set to player first always
        turnNum = 1; // initialize first round

    //Initialize Fleets
        this.fleetFactory = new FleetFactory();
        String[] standardFleet={"minesweeper","destroyer","battleship","submarine"};//Set standard list of pieces
        this.playerFleet = this.fleetFactory.createFleet(standardFleet);
        this.compFleet = this.fleetFactory.createFleet(standardFleet);

    //Initialize Boards & Set Behaviors
        this.boardSetFactory = new BoardSetFactory();
        String[] standardBoardSet={"surface","underwater"};
        this.playerBoards = this.boardSetFactory.createBoardSet(standardBoardSet);
        this.computerBoards = this.boardSetFactory.createBoardSet(standardBoardSet);

        this.computerBoards[0].setShowBehavior(new SurfaceHiddenBoardShow());
        this.computerBoards[1].setShowBehavior(new UnderwaterHiddenBoardShow());

        this.player = new UserPlayer(this.playerBoards);
        this.computer = new ComputerPlayer(this.computerBoards);

    }

    public void BeginGame() {



        //Still do user input for ship placement here
        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        System.out.println();

        System.out.println("You will place your ships, and then you will fire the first shot to being your battle. Good luck!");
        System.out.println();
        System.out.println();

        // give ships ids and place them
        for (Ship ship : compFleet) {
            if (!ship.getUnderwater()) {
                ship.setId(idNum);
                idNum++;
                this.computer.getBoards()[1].setCreateShipCoordinatesBehavior(new RegularShipCoordinates());
                this.computer.performSurfacePlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                this.computer.getBoards()[0].registerShip(ship);
            }
            else {
                ship.setId(idNum);
                idNum++;
                this.computer.getBoards()[1].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
                this.computer.performUnderwaterPlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                this.computer.getBoards()[1].registerShip(ship);
            }
        }

        boolean subUnderwater = true;
        // ask if player wants to place their sub on surface or underwater
        System.out.println("Do you want to place your submarine underwater? (Yes)/(No)");
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string
        input = input.toUpperCase(); // set to uppercase
        if (input.equals("NO")) { // need to check for bad input
            subUnderwater = false;
        }
        // give ships ids and place them
        for (Ship ship : playerFleet) {
            if (!ship.getUnderwater()) { // these are for ships that aren't sub so always place on surface board
                ship.setId(idNum);
                idNum++;
                this.player.getBoards()[0].setCreateShipCoordinatesBehavior(new RegularShipCoordinates());
                this.player.performSurfacePlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                this.player.getBoards()[0].registerShip(ship);
            }
            else if(!subUnderwater && ship.getUnderwater()){ // this is if the player decides to place their sub on the surface
                ship.setId(idNum);
                idNum++;
                this.player.getBoards()[0].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
                this.player.performSurfacePlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                this.player.getBoards()[0].registerShip(ship);
            }
            else { // this is if the player decides to place their sub underwater
                ship.setId(idNum);
                idNum++;
                this.player.getBoards()[1].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
                this.player.performUnderwaterPlacement(ship.getId(), ship.getSize(), ship.getQuartersSpotInt());
                this.player.getBoards()[1].registerShip(ship);
            }
        }

        // set up of game is now done. Begin taking turns. Implementing sonar pulse
        boolean firstSunkComputer = false;
        boolean firstSunkPlayer = false;
        int sonarUses = 2;
        while(!EndGame()){
            player.performShot(this.computer.getBoards(), 'Z', -1, this.turnNum);
            ChangeTurn();
            this.computer.performShot(player.getBoards(), 'Z', -1, this.turnNum);
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
                        break;
                    }
                }
            }

            if(firstSunkPlayer){
                //if the computer sunk a player's ship, the computer now has the laser
                this.computer.setShotBehavior(new LaserRandomShot());
            }

            // checking to see if the player has sunk at least one ship from comp
            // this is to ask if they want to use a sonar
            if (firstSunkComputer && sonarUses!=0){ // ask about sonar use since first ship is sunk
                player.setShotBehavior(new LaserInputShot());
                System.out.println("Because you sunk your first opponent's ship, would you like to use a sonar pulse?");
                System.out.println();
                input = sc.nextLine(); // Read user input
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
                    this.computer.getBoards()[0].setShowBehavior(new SonarBoardShow(colVal,rowVal));
                    this.computer.getBoards()[0].performShow();
                    this.computer.getBoards()[0].setShowBehavior(new SurfaceHiddenBoardShow());
                    // remove one sonar use
                    sonarUses--;
                }
            }
        }
    }

    // change the turn
    public void ChangeTurn() {
        // change the turn to either player or this.computer
        turnInfo = (turnInfo == 'P') ? 'C' : 'P';
    }

    // method to return turn info
    public char GetTurn() {
        return turnInfo;
    }


    //TODO: Add end game check, more functions
    public boolean EndGame() {
        int playerCount = 0;
        int compCount = 0;
        for (int i = 0; i < this.playerFleet.length; i++) {
            if (this.playerFleet[i].checkSunk()) {
                playerCount++;
            }
            if (this.compFleet[i].checkSunk()) {
                compCount++;
            }
        }
        return playerCount == this.playerFleet.length || compCount == this.compFleet.length;
    }

    public int getIdNum() { return this.idNum; }
    public void setIdNum() { this.idNum++; }

}
