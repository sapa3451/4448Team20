package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.Scanner;

/**
 * Description: A shot behavior that gets used when place decides to use a special shot
 * Ability for if player chooses an extra bomber. Makes shot for every other coord in a line(vertical or horizontal)
 * based on user input. Hits surface and underwater board
 */
public class BombRun implements ShotBehavior{
    /**
     * Description: Takes in user input for horizontal or vertical, and then col or row to do, then marks board accordingly
     * Params: a set of boards at different z elevations, and the column and row coords for dev test
     * Returns: true and assumes shot sucessful
     */
    public boolean shot(Board[] board, char colv,  int row) {

        if (colv != 'Z' && row != -1) {
            board[1].MarkBoard(colv,row);
            return true;
        }//For DevTest Skip

        System.out.println("You've Decided to do a Bomb Run");
        System.out.println("Do you want to do a horizontal(1) or vertical(0) bomb run?");

        // take in user input for a horizontal or vertical bomb run
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string

        //Checks for user input error
        while(!input.equalsIgnoreCase("0") && !input.equalsIgnoreCase("1")){
            System.out.println("Please enter either horizontal(1) or vertical(0) for a bomb run");
            input = sc.nextLine();    //reads string
        }

    //Takes in which row user wants to BombRun if they want horizontal, checks for bad input
        if(input.equalsIgnoreCase("1")){
            System.out.println("Type which row (1-10) you would like to target: ");
            input = sc.nextLine();
            int rowInput = Integer.parseInt(input); // set value to int

            try {
                // checking valid integer using parseInt() method
                rowInput = Integer.parseInt(input); // set value to int
            }
            catch (NumberFormatException e) { // throw error and get input again
            }
            //If bad input throws in loop
            while(rowInput < 1 || rowInput > 10){//Checking for user error
                System.out.println("Invalid row! Please enter a valid row (1-10):");
                input = sc.nextLine(); // Read user input
                try {
                    // checking valid integer using parseInt() method
                    rowInput = Integer.parseInt(input); // set value to int
                }
                catch (NumberFormatException e) { // throw error and get input again
                }
            }

            //Do Actual Stuff
            for (Board value : board) {
                if (value.getzValue() < 1) {
                    for(char i ='A'; i<'A'+value.getRowSize();i+=2) {
                        value.MarkBoard(i, rowInput);
                    }
                }
            }
            //
        }

    //Takes in which column user wants to BombRun if they want vertical, checks for bad input
        else{
            System.out.println("Type which column (A-J) you would like to target: ");
            input = sc.nextLine();
            input = input.toUpperCase(); // set to uppercase
            //Checking for bad input here
            while(input.toCharArray()[0] < 'A' || input.toCharArray()[0] > 'J'){//Checking for user error
                System.out.println("Invalid column! Please enter a valid column (A-J): ");
                input = sc.nextLine(); // Read user input
                input = input.toUpperCase(); // set to uppercase
            }
            //Do Actual Stuff
            char col=input.toCharArray()[0];
            for (Board value : board) {
                if (value.getzValue() < 1) {
                    for (int i = 0; i < value.getColumnSize(); i+=2) {
                        value.MarkBoard(col, i+1);
                    }
                }
            }
        }
        for (Board value : board) {
            value.performShow();
        }

        return true;
    }
}
