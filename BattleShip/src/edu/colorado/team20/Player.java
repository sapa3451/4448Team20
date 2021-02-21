package edu.colorado.team20;
import java.util.Scanner;

public class Player {

    private Ship minesweeper;
    private Ship destroyer;
    private Ship battleship;

    public void UpdateAndSetPlayerShips(Ship ship){
        if (ship.getShipName() == "minesweeper"){
            this.minesweeper = ship;
        }
        else if (ship.getShipName() == "destroyer"){
            this.destroyer = ship;
        }
        else if (ship.getShipName() == "battleship"){
            this.battleship = ship;
        }

    }



    // ask player for shot decision
    // need to send in Board object in order to call the check spot validity (V)
    public void GetDecisionShot(Board board) {
        // variables for input
        char colVal = ' ';
        int rowVal = -1;

        System.out.println("It is your turn!");
        System.out.println("Decision: ");
        System.out.println("Type which column (A-J) you would like to target: ");

        // take in user input
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string
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

        System.out.println("Type which row (1-10) you would like to target: ");

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
        //added a return method to this function, which is then used to call and update the board with a hit
        // before calling method need to check if this spot is a valid move (not already been shot at)
        board.MarkBoard(colVal, rowVal);
    }


    public void GetShipPlacement(Ship myShip,String [] input) {
        String orientation = ""; // do they want to place the ship vertically(1) or horizontally(0)
        char colVal = ' ';
        int rowVal = -1;
        System.out.println("Type which column (A-J) you would like to place your " + myShip.getShipName() + " ship:");
        // take in user input
//        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
//        String input = sc.nextLine();    //reads string
//        input = input.toUpperCase(); // set to uppercase

        // we want to check input is okay for column
        boolean correct = false;
        while (!correct) {
            // check if valid input
            if (input[0].length() == 1) { // check if single letter
                char[] col = input[0].toCharArray(); // set to char array
                if (col[0] >= 'A' && col[0] <= 'J') {  // check if valid column input
                    correct = true;
                    // set column value
                    colVal = col[0];
                }
            }
            // invalid input
            if (!correct) {
                System.out.println("Invalid column! Please enter a valid column (A-J): ");
//                input = sc.nextLine(); // Read user input
//                input = input.toUpperCase(); // set to uppercase
            }
        }

        System.out.println("Type which row (1-10) you would like to place your " + myShip.getShipName() + " ship: ");

        // take in user input
//        input = sc.nextLine();

        // we want to check input is okay for column
        correct = false;
        while (!correct) {
            // check if valid input
            try {
                // checking valid integer using parseInt() method
                rowVal = Integer.parseInt(input[1]); // set value
            }
            catch (NumberFormatException e) { // throw error and get input again
                System.out.println("Invalid row! Please enter a valid row (1-10):");
//                input = sc.nextLine(); // Read user input
            }

            if (rowVal > 0 && rowVal <= 10) { // check if row value is within board
                correct = true;
            }
            else {
                System.out.println("Invalid row! Please enter a valid row (1-10):");
//                input = sc.nextLine(); // Read user input
            }
        }
        // after this i have a valid colVal and rowVal


        System.out.println("Do you want to place your " + myShip.getShipName() + " ship horizontally or vertically? ");
//        input = sc.nextLine();
        orientation = input[2];
        int direction;
        if (orientation.toUpperCase() == "HORIZONTALLY") {
            direction = 1;
        }
        else {
            direction = 0;
        }
        myShip.setColumnAndRow(colVal,rowVal,direction);
    }

    public Ship getMinesweeper() {
        return minesweeper;
    }

    public Ship getDestroyer() {
        return destroyer;
    }

    public Ship getBattleship() {
        return battleship;
    }
}