package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonBarrage;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserRandomShot;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import edu.colorado.team20.GamePiece.*;

import java.util.Scanner;

/**
 * Description:
 */
public class GameManagement {
    private int turnNum;
    private int idNum = 1;
    private GamePiece[] playerFleet;
    private GamePiece[] compFleet;
    //Change Users to attributes
    private final Player player;
    private final Player computer;

    public GameManagement() {
        turnNum = 1; // initialize first round

        //Initialize Boards & Set Behaviors
        //Change Boards to attributes
        BoardSetFactory boardSetFactory = new BoardSetFactory();
        String[] standardBoardSet={"air","surface","underwater"};
        Board[] playerBoards = boardSetFactory.createBoardSet(standardBoardSet);
        Board[] computerBoards = boardSetFactory.createBoardSet(standardBoardSet);

        this.player = new UserPlayer(playerBoards);
        this.computer = new ComputerPlayer(computerBoards);

        for (Board board : this.computer.getBoards()) {
            board.setShowBehavior(new HiddenShow());
        }


    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void InitializeGame() {
        //Still do user input for ship placement here
        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        System.out.println();

        System.out.println("You will place your ships, and then you will fire the first shot to begin your battle. Good luck!");
        System.out.println();
        System.out.println();

        System.out.println("To start your fleet consists of 1 Minesweeper,1 Destroyer, 1 Battleship, 1 Submarine, and 1 Bomber");
        System.out.println("For your 6th unit you may choose additional:");
        System.out.println("(Bomber)");
        System.out.println("(Submarine)");
        System.out.println("(Battleship)");
        System.out.println();


        //Initialize Fleets

        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string
        input = input.toUpperCase(); // set to uppercase

        //Checks for bad input
        while (!input.equalsIgnoreCase("bomber") && !input.equalsIgnoreCase("submarine") && !input.equalsIgnoreCase("battleship")) {
            System.out.println("Invalid optional ship type");
            System.out.println("For your 6th unit you may choose additional:");
            System.out.println("(Bomber)");
            System.out.println("(Submarine)");
            System.out.println("(Battleship)");
            System.out.println();
            input = sc.nextLine();    //reads string
        }

        FleetFactory fleetFactory = new FleetFactory();
        String[] inputFleet = {"minesweeper", "destroyer", "battleship", "submarine", "bomber", input};//Set standard list of pieces w/ user input
        this.playerFleet = fleetFactory.createFleet(inputFleet);

//!!!!!!!!!!!!!!! Add random extra ship !!!!!!!!!!!!!!!!!!!!!
        this.compFleet = fleetFactory.createFleet(inputFleet);

        // give ships ids and place them
        for (GamePiece gamePiece : compFleet) {
            if (gamePiece.canbeUnderwater()) {
                gamePiece.setId(idNum);
                idNum++;
                for (Board board : this.computer.getBoards()) {
                    if (board.getzValue() < 0) {
                        board.setCreateCoordinatesBehavior(new SubmarineCoordinates());
                    }
                }
                this.computer.performUnderwaterPlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                for (Board board : this.computer.getBoards()) {
                    if (board.getzValue() < 0) {
                        board.registerShip(gamePiece);
                    }
                }
            } else if (gamePiece.canbeInAir()) {
                gamePiece.setId(idNum);
                idNum++;
                for (Board board : this.computer.getBoards()) {
                    if (board.getzValue() > 0) {
                        board.setCreateCoordinatesBehavior(new PlaneCoordinates());
                    }
                }
                this.computer.performAirPlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                for (Board board : this.computer.getBoards()) {
                    if (board.getzValue() > 0) {
                        board.registerShip(gamePiece);
                    }
                }
            } else {
                gamePiece.setId(idNum);
                idNum++;
                for (Board board : this.computer.getBoards()) {
                    if (board.getzValue() == 0) {
                        board.setCreateCoordinatesBehavior(new LinearCoordinates());
                    }
                }
                this.computer.performSurfacePlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                for (Board board : this.computer.getBoards()) {
                    if (board.getzValue() == 0) {
                        board.registerShip(gamePiece);
                    }
                }
            }
        }

        idNum = 1;
        for (GamePiece gamePiece : playerFleet) {
            if (gamePiece.canbeUnderwater()) {//For if piece is submarine since they initialize to be underwater

                // ask if player wants to place their sub on surface or underwater
                System.out.println("Do you want to place your " + gamePiece.getName() + " underwater? (Yes)/(No)");
                input = sc.nextLine();    //reads string
                input = input.toUpperCase(); // set to uppercase

                while (!input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("yes")) {
                    System.out.println("Invalid input! Please enter (Yes) or (No): ");
                    input = sc.nextLine();
                    input = input.toUpperCase();
                }

                if (input.equalsIgnoreCase("no")) {
                    gamePiece.setId(idNum);
                    idNum++;
                    for (Board board : this.player.getBoards()) {
                        if (board.getzValue() == 0) {
                            board.setCreateCoordinatesBehavior(new SubmarineCoordinates());
                        }
                    }
                        System.out.println("Placing " + gamePiece.getName() + "!");
                        this.player.performSurfacePlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                        for (Board board : this.player.getBoards()) {
                            if (board.getzValue() == 0) {
                                board.registerShip(gamePiece);
                            }
                        }
                    } else{
                        gamePiece.setId(idNum);
                        idNum++;
                        for (Board board : this.player.getBoards()) {
                            if (board.getzValue() < 0) {
                                board.setCreateCoordinatesBehavior(new SubmarineCoordinates());
                            }
                        }
                        System.out.println("Placing " + gamePiece.getName() + "!");
                        this.player.performUnderwaterPlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                        for (Board board : this.player.getBoards()) {
                            if (board.getzValue() < 0) {
                                board.registerShip(gamePiece);
                            }
                        }
                    }
                } else if (gamePiece.canbeInAir()) {
                    gamePiece.setId(idNum);
                    idNum++;
                    for (Board board : this.player.getBoards()) {
                        if (board.getzValue() > 0) {
                            board.setCreateCoordinatesBehavior(new PlaneCoordinates());
                        }
                    }
                    System.out.println("Placing " + gamePiece.getName() + "!");
                    this.player.performAirPlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                    for (Board board : this.player.getBoards()) {
                        if (board.getzValue() > 0) {
                            board.registerShip(gamePiece);
                        }
                    }
                } else {
                    gamePiece.setId(idNum);
                    idNum++;
                    for (Board board : this.player.getBoards()) {
                        if (board.getzValue() == 0) {
                            board.setCreateCoordinatesBehavior(new LinearCoordinates());
                        }
                    }
                    System.out.println("Placing " + gamePiece.getName() + "!");
                    this.player.performSurfacePlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                    for (Board board : this.player.getBoards()) {
                        if (board.getzValue() == 0) {
                            board.registerShip(gamePiece);
                        }
                    }
                }
            }
        }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void Sonar(int sonarUses) {
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = " ";
        if (sonarUses != 0) { // ask about sonar use since first ship is sunk
            player.setShotBehavior(new LaserInputShot());
            System.out.println("Using Sonar Shot!");
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
                } catch (NumberFormatException e) { // throw error and get input again
                    System.out.println("Invalid row! Please enter a valid row (1-10):");
                    input = sc.nextLine(); // Read user input
                }

                if (rowVal > 0 && rowVal <= 10) { // check if row value is within board
                    correct = true;
                } else {
                    System.out.println("Invalid row! Please enter a valid row (1-10):");
                    input = sc.nextLine(); // Read user input
                }
            }
            // got a valid row and col
            for (Board board : this.computer.getBoards()) {
                board.setShowBehavior(new SonarBoardShow(colVal, rowVal));
                board.performShow();
                board.setShowBehavior(new HiddenShow());
            }
            sonarUses--;
        }
        else {
            System.out.println("You are all out of Sonar Shots! Try something else!");
        }
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void SpecialShot(int specialUses){
        if (specialUses!=0){
            System.out.println("Do you want to use your Bomb Run Ability? (Yes)/(No)");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine(); // Read user input

            while(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"))
            {
                System.out.println("Invalid input! Please enter (Yes) or (No): ");
                input = sc.nextLine();
            }

            if(input.equalsIgnoreCase("yes")){
                //Checks to see if player wants to use their special shot
                //if so: changes shot behavior, performs BombRun/Shot, changes behavior back,
                // reduces their remaining available special shot count
                ShotBehavior prevShotBev = this.player.getShotBehavior();
                this.player.setShotBehavior(new CannonBarrage());
                this.player.performSpecialShot(this.computer.getBoards(),'Z',-1);
                this.player.setShotBehavior(prevShotBev);
                specialUses--;
            }
            else{

            }
        }
        else{
            System.out.println("You have no remaining special shots");
        }
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void BeginGame() {
        // set up of game is now done. Begin taking turns. Implementing sonar pulse
        Scanner sc = new Scanner(System.in);
        boolean justShowed = false;
        boolean firstSunkComputer = false;
        boolean firstSunkPlayer = false;
        int sonarUses = 2;
        int specialUses = 2;
        while (!EndGame()) {
            if (!firstSunkComputer) { // loop through comp's fleet to find at least one sunk ship
                for (GamePiece compGamePiece : compFleet) {
                    if (compGamePiece.checkSunk()) {
                        firstSunkComputer = true;
                        System.out.println("You have sunk your enemy's first ship! Your weapon has been upgrading to the Space Laser, allowing you to fire onto all 3 of the enemy's boards!");
                        System.out.println();
                        player.setShotBehavior(new LaserInputShot());
                        break;
                    }
                }
            }
            System.out.println("**************MENU***************");
            System.out.println("Choose from below what you would like to do! (Enter Number)");
            System.out.println("1. Fire Shot Against Enemy");
            System.out.println("2. Use Special Ability Against Enemy");
            System.out.println("3. Show Enemy Boards");
            System.out.println("4. Show Your Boards");
            if (firstSunkComputer == true) {
                System.out.println("5. Use Sonar Shot Against Enemy");
            }
            int input = Integer.parseInt(sc.nextLine());
            if (input == 1){
                player.performTurn(this.computer.getBoards(), 'Z', -1, this.turnNum);
                justShowed = false;
            }
            else if (input == 2){
                SpecialShot(specialUses);
                justShowed = false;
            }
            else if (input == 3){
                for (Board boards : computer.getBoards()){
                    boards.performShow();
                }
                justShowed = true;
            }
            else if (input == 4){
                for (Board boards : player.getBoards()){
                    boards.performShow();
                }
                justShowed = true;
            }
            else if (input == 5 && firstSunkComputer == true){
                Sonar(sonarUses);
                justShowed = false;
            }
            else{
                System.out.println("Please Select a Valid Option!!!");
                justShowed = true;
            }
            if (justShowed == false) {
                this.computer.performTurn(player.getBoards(), 'Z', -1, this.turnNum);
                // round over updating turnNum
                turnNum++;
                if (!firstSunkPlayer) { // loop through players fleet to find at least one sunk ship
                    for (GamePiece playerGamePiece : playerFleet) {
                        if (playerGamePiece.checkSunk()) {
                            firstSunkPlayer = true;
                            break;
                        }
                    }
                }
                if (firstSunkPlayer) {
                    //if the computer sunk a player's ship, the computer now has the laser
                    this.computer.setShotBehavior(new LaserRandomShot());
                }
            }
        }
    }


    /**
     * Description:
     * Params:
     * Returns:
     */
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
