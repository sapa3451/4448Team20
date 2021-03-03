package edu.colorado.team20;

import java.util.Scanner;

public final class UserPlayer extends Player{

    public UserPlayer(Board board) {
        super(board);
    }

    // TODO: need to call CheckSpot to make sure that the spot hasn't already been shot at
    public int Shot(Board board, char colv, int row, int turnNum) {
        // variables for input
        if (colv != 'Z' && row != -1) {
            return board.MarkBoard(colv, row);
        }
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

        // add shot to map of shot decisions
        this.addShotFromTurn(turnNum, colVal+String.valueOf(rowVal));

        //added a return method to this function, which is then used to call and update the board with a hit
        // before calling method need to check if this spot is a valid move (not already been shot at)
        return board.MarkBoard(colVal, rowVal);
    }

    public void ShipPlacement(int id, int shipSize, int check) {
        // TODO: we need to make sure that ships do not overlap
        if (check == -1) {
            board.SetShipPos(id, 1, 'A', 1, shipSize);
            return;
        }
        // do they want to place the ship vertically(1) or horizontally(0)
        char colVal = ' ';
        int rowVal = -1;
        System.out.println("Type which column (A-J) you would like to place your ship:");
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

        System.out.println("Type which row (1-10) you would like to place your ship: ");

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
        // after this i have a valid colVal and rowVal


        System.out.println("Do you want to place your ship horizontally(1) or vertically(0)? ");
        input = sc.nextLine();
        int direction = Integer.parseInt(input);
        board.SetShipPos(id, rowVal, colVal, direction, shipSize);
    }

    public void placeBattleship (int id) {
        System.out.println("Placing your Battleship!");
        ShipPlacement(id, 4, 0);
    }

    public void placeMinesweeper (int id) {
        System.out.println("Placing your Minesweeper!");
        ShipPlacement(id, 2, 0);
    }

    public void placeDestroyer (int id) {
        System.out.println("Placing your Destroyer!");
        ShipPlacement(id, 3, 0);
    }


    //TODO: connecting ships with player??? email dwight
    //TODO: captains quarters
    //TODO: sonar radar
    //TODO: ship health and board knowing which ships are which
}
