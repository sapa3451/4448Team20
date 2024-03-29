package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import java.util.Scanner;

/**
 * Description: A shot behavior that gets used when place decides to use a special shot
 * Ability for if player chooses an extra battleship. Makes shot for 3x3 X shape
 * based on user input. Hits surface and air board
 */
public class CannonBarrage implements ShotBehavior {
    /**
     * Description: Takes in user input for center of barrage, then marks board accordingly
     * Params: a set of boards at different z elevations, and the column and row coords for dev test
     * Returns: true and assumes shot sucessful
     */
    public boolean shot(Board[] board, char colv,  int row) {

        if (colv != 'Z' && row != -1) {
            board[1].MarkBoard(colv,row);
            return true;
        }//For DevTest Skip

        char colVal;
        int rowVal;

        System.out.println("You've Decided to use a Cannon Barrage");
        System.out.println("You must now choose where you want to center the barrage");

    //Gets user input for Column coordinate, checks for bad input
        System.out.println("Type which column (A-J) you would like to target: ");
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string
        input = input.toUpperCase(); // set to uppercase

        while(input.toCharArray()[0] < 'A' || input.toCharArray()[0] > 'J'){//Checking for user error
            System.out.println("Invalid column! Please enter a valid column (A-J): ");
            input = sc.nextLine(); // Read user input
        }
        colVal=input.toCharArray()[0];

    //Gets user input for Row coordinate, checks for bad input
        System.out.println("Type which row (1-10) you would like to target: ");
        input = sc.nextLine();
        int rowInput = -1;
        try {
            // checking valid integer using parseInt() method
            rowInput = Integer.parseInt(input); // set value to int
        }
        catch (NumberFormatException e) { // throw error and get input again
        }
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
        rowVal=rowInput;

    //Marks board, checks if going off board first
        for (Board value : board) {
            if (value.getzValue() < 1) {
                value.MarkBoard(colVal, rowVal);
            }
        }
        //Checks if might go off board to left
        if(colVal>'A'){
            colVal+=-1;
            if(rowVal>1) {
                for (Board value : board) {
                    if (value.getzValue() < 1) {
                       value.MarkBoard(colVal, rowVal - 1);
                    }
                }
            }
            if(rowVal<10) {
                for (Board value : board) {
                    if (value.getzValue() < 1) {
                        value.MarkBoard(colVal, rowVal + 1);
                    }
                }
            }
            colVal+=1;
        }
        //Checks if might go off board to right
        if(colVal<'J'){
            colVal+=1;
            if(rowVal>1){
                for (Board value : board) {
                    if (value.getzValue() < 1) {
                        value.MarkBoard(colVal, rowVal - 1);
                    }
                }
            }
            if(rowVal<10){
                for (Board value : board) {
                    if (value.getzValue() < 1) {
                        value.MarkBoard(colVal, rowVal + 1);
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
