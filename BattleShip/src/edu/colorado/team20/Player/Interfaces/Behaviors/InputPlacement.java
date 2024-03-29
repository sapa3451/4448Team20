package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;

import java.util.Scanner;

/**
 * Description: Places a ship on a passed board based on user input.
 * User says where they want the first position to be then decides to have the rest of the ship extend from the point
 * either vertically(down) or horizontally(right)
 * Checks if desired position would go off board or collide with another ship on board, if so makes them enter new info
 */
public class InputPlacement implements PlacementBehavior {
    /**
     * Description: Takes in user input to place a game peice on a passed board, and saves info for later use
     * Params: an ID number to identify the ship later, the board to place the ship on, the size of the ship, the position on the ship of the Captain Quaters
     * Returns: nothing
     */
    public void place (int id, Board board, int size, int quartersPos) {
        if (size == 99) {
            return;
        }
        boolean validPlacement = false;

        // we want to check input is okay for column
        while(!validPlacement) {
            char colVal = ' ';
            int rowVal = -1;
            System.out.println("Type which column (A-J) you would like to place your piece:");
            // take in user input
            Scanner sc = new Scanner(System.in); //System.in is a standard input stream
            String input = sc.nextLine();    //reads string
            input = input.toUpperCase(); // set to uppercase

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

            System.out.println("Type which row (1-10) you would like to place your piece: ");

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
            System.out.println("Do you want to place your piece horizontally(1) or vertically(0)? ");
            input = sc.nextLine();
            while(!input.equalsIgnoreCase("0") && !input.equalsIgnoreCase("1")){
                    System.out.println("Input is not an int value. Please enter 1 for horizontal or 0 for vertical.");
                    input = sc.nextLine();
            }
            int direction = Integer.parseInt(input);

            validPlacement = board.SetGamePiecePos(id, rowVal, colVal, direction, size, quartersPos);
            board.performShow();
            if (!validPlacement) {
                System.out.println("Ship won't fit on board with chosen row and column! Type in a valid position.");
            }
        }
    }
}