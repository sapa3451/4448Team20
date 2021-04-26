package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.*;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import edu.colorado.team20.GamePiece.*;

import java.util.Scanner;

/**
 * Description: Game Management is where the buck of the game is managed. Here, we initialize the Factories, the players, the shot types, and all
 * other information we need for the game to properly run. It consists of multiple functions that handle different responsibilities such as initialization
 * where the ships are placed and the extra piece is chosen, beginning the game where the shots, special abilities, and the main game happens, and checking
 * when the game has ended. This takes care of a lot of the main User I/O for the game to properly run.
 */
public class GameManagement {
    private int turnNum;
    private int idNum = 1;
    private GamePiece[] playerFleet;
    private GamePiece[] compFleet;
    //Change Users to attributes
    private final Player player;
    private final Player computer;
    private ShotBehavior specialType;

    public GameManagement() {
        turnNum = 1; // initialize first round

        //Initialize Boards & Set Behaviors
        BoardSetFactory boardSetFactory = new BoardSetFactory();
        int[] standardBoardSet={1,0,-1};
        Board[] playerBoards = boardSetFactory.createBoardSet(standardBoardSet);
        Board[] computerBoards = boardSetFactory.createBoardSet(standardBoardSet);

        this.player = new UserPlayer(playerBoards);
        this.computer = new ComputerPlayer(computerBoards);

        for (Board board : this.computer.getBoards()) {
            board.setShowBehavior(new HiddenShow());
        }


    }

    /**
     * Description: This function finds the special piece that the user has chosen and gives them a special ability based on which extra piece they chose.
     * Params: extra piece chosen by player
     * Returns: none
     */
//Sets global string variable to keep track of which special the player can use
    public void setSpecialType(String extraShip){
        if(extraShip.equalsIgnoreCase("bomber")){
            this.specialType= new BombRun();
        }
        else if(extraShip.equalsIgnoreCase("battleship")){
            this.specialType= new CannonBarrage();
        }
        else if(extraShip.equalsIgnoreCase("submarine")){
            this.specialType= new TorpedoShot();
        }
        else{//Dev error somehow
            this.specialType= new CannonBarrage();
        }
    }

    /**
     * Description: This is where the bulk of game initialization happens. Here, the user and the computer both will place there ships. The board is updated,
     *              the proper special shots and pieces are given, and the game is ready to begin once this function ends.
     * Params: none
     * Returns: none
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
        setSpecialType(input);//Sets special ability based on chosen extra ship

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
     * Description: When a player uses their sonar ability, this function is called to grab the input, check to make sure they have enough uses available,
     *              and shows the enemies board with the given input on their sonar. if the user is out of sonar uses, they must select another option
     * Params: amount of sonar uses left
     * Returns: if the sonar performed successfully
     */
    public boolean Sonar(int sonarUses) {
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input;
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
            return true;
        }
        else {
            System.out.println("You are all out of Sonar Shots! Try something else!");
            return false;
        }
    }

    /**
     * Description: When a player uses their special ability, this function is called to grab the input, check to make sure they have enough uses available,
     *              and performs the special shot. if they do not have enough special abilities, they must choose another option.
     * Params: amount of special shot uses left
     * Returns: if the special shot performed successfully
     */
    public boolean SpecialShot(int specialUses){
        //Checks to see if they have remain special shots left

        if (specialUses!=0) {
            ShotBehavior prevShotBev = this.player.getShotBehavior();
            this.player.setShotBehavior(this.specialType);
            this.player.performSpecialShot(this.computer.getBoards(), 'Z', -1);
            this.player.setShotBehavior(prevShotBev);
            return true;
        }
        else{
            System.out.println("You have no remaining special shots!");
            return false;

        }
    }

    /**
     * Description: This is where the bulk of the game happens. We have a loops set up that runs until the game ends. Until it does, the game will
     *              go back and forth between the user and computer for them to take their turns. the shots, the players abilities, the menu for the player to
     *              choose from, and other main game functions are all taken care of in this function
     * Params: none
     * Returns: none
     */
    public void BeginGame() {
        // set up of game is now done. Begin taking turns. Implementing sonar pulse
        Scanner sc = new Scanner(System.in);
        boolean justShowed;
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
            if (firstSunkComputer) {
                System.out.println("5. Use Sonar Shot Against Enemy");
            }
            String input = sc.nextLine();
            int intInput;
            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")) {
                intInput = Integer.parseInt(input);
            }
            else {
                intInput = 0;
            }
            if (intInput == 1){
                player.performTurn(this.computer.getBoards(), 'Z', -1, this.turnNum);
                justShowed = false;
            }
            else if (intInput == 2){
                //Calls specialShot helper to see if player wants to use it and lets them if able
                //If they do use it subtracts 1 from their remaining special shots
                if(SpecialShot(specialUses)){
                    specialUses--;
                    justShowed = false;
                }
                else {
                    justShowed = true;
                }
            }
            else if (intInput == 3){
                for (Board boards : computer.getBoards()){
                    boards.performShow();
                }
                justShowed = true;
            }
            else if (intInput == 4){
                for (Board boards : player.getBoards()){
                    boards.performShow();
                }
                justShowed = true;
            }
            else if (intInput == 5 && firstSunkComputer){
                if (Sonar(sonarUses)){
                    justShowed = false;
                    sonarUses--;
                }
                else {
                    justShowed = true;
                }
            }
            else{
                System.out.println("Please Select a Valid Option!!!");
                justShowed = true;
            }
            if (!justShowed) {
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
     * Description: This function is called once every loop in BeginGame. This checks to see if all of a players pieces are sunk. If it finds that
     *              one player does have all of their pieces sunk, we will end the game and display a message.
     * Params: none
     * Returns: true for end of game, false otherwise
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
        if (compCount == this.playerFleet.length){
            System.out.println("You have won! Your skill and strategy has allowed you to defeat your enemy and sink their entire fleet. Future enemies will fear you!");
        }
        else if (playerCount == this.compFleet.length) {
            System.out.println("You have lost! You did not successfully defend against your enemies attacks. You may have lost this battle, but the war is far from over.");
        }
        return playerCount == this.playerFleet.length || compCount == this.compFleet.length;
    }

    public int getIdNum() { return this.idNum; }
    public void setIdNum() { this.idNum++; }

}
