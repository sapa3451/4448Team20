package edu.colorado.team20;

import java.util.Scanner;

public final class UserPlayer implements IPlayer {
    private final IBoard board;
    public UserPlayer() {
        this.board = new PlayerBoard();
    }

    public IBoard getBoard () {
        return board;
    }

    public void Shot(IBoard board, char colv, int row) {
        // variables for input
        if (colv != 'Z' && row != -1) {
            board.MarkBoard(colv, row);
            return;
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
        //added a return method to this function, which is then used to call and update the board with a hit
        // before calling method need to check if this spot is a valid move (not already been shot at)
        board.MarkBoard(colVal, rowVal);
    }

    public void ShipPlacement(int shipSize, int check) {
        if (check == -1) {
            this.board.SetShipPos(1, 'A', 1, shipSize);
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


        System.out.println("Do you want to place your ship horizontally or vertically? ");
        input = sc.nextLine();
        int direction = Integer.parseInt(input);
        this.board.SetShipPos(rowVal, colVal, direction, shipSize);
    }

    public void placeBattleship () {
        System.out.println("Placing your Battleship!");
        ShipPlacement(4, 0);
    }

    public void placeMinesweeper () {
        System.out.println("Placing your Minesweeper!");
        ShipPlacement(2, 0);
    }

    public void placeDestroyer () {
        System.out.println("Placing your Destroyer!");
        ShipPlacement(3, 0);
    }

}
