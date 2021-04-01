package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.Scanner;

//This is the start of the interface for the laser
//TODO: before any further implementation, underwater board must be implemented

public class LaserInputShot implements ShotBehavior {
    public void shot(Board[] board, char colv, int row, int turnNum) {
        if (colv != 'Z' && row != -1) {
            //a laser will mark all available boards, as below
            for (int i = 0; i < board.length; i++){
                if (board[i].getId() > -5) {
                    board[i].performMarkBoard(colv, row);
                }
            }
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
        //a laser will mark all available boards, as below
        for (int i = 0; i < board.length; i++){
            if (board[i].getId() > -5) {
                board[i].performMarkBoard(colVal, rowVal);
            }
        }
    }
}
