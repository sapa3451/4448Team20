package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.Scanner;

/**
 * Description: A shot behavior that gets used when place decides to use a special shot
 * Ability for if player chooses an extra submarine. Shoots at single position, if ship there its sunk instantly
 * based on user input. Hits surface or underwater board only hitting one and hit an underwater ship first
 */
public class TorpedoShot implements ShotBehavior {

    /**
     * Description: Takes in user input for coords of torpedo target, then checks ID at coord to see if ship there if
     * so it instantly sets ship health to 0, marks ship as destroyed and updates boards
     * Params: a set of boards at different z elevations, and the column and row coords for dev test
     * Returns: true
     */
    public boolean shot(Board[] board, char colv, int row) {
        if (colv != 'Z' && row != -1) {
            board[1].MarkBoard(colv,row);
            return true;
        }//For DevTest Skip

        char colVal;
        int rowVal;

        System.out.println("You've Decided to use a Torpedo Shot");
        System.out.println("You must now choose where launch your torpedo");

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

        //Checks IDs at surface and Underwater Board
        int UnderwaterID = 0;
        int SurfaceID = 0;
        for (Board value : board) {
            if (value.getzValue() < 0) {
                UnderwaterID = value.getIDatCoord(colVal,rowVal);
            }
        }
        for (Board value : board) {
            if (value.getzValue() == 0) {
                SurfaceID = value.getIDatCoord(colVal,rowVal);
            }
        }

    //First checks to see if there is a sub underwater and if so sinks it
        if(UnderwaterID!=0){
            for (Board value : board) {
                if (value.getzValue() < 0) {
                    value.updateGamePieceChars(value.getGamePieceCoordinates(UnderwaterID));
                    value.removeShip(value.getIDatCoord(colVal,rowVal)); //removes a ship as an observer when sunk
                }
            }
        }
    //If not checks if there's a ship at coord if so sinks it
        else if(SurfaceID!=0){
            for (Board value : board) {
                if (value.getzValue() == 0) {
                    value.updateGamePieceChars(value.getGamePieceCoordinates(SurfaceID));
                    value.removeShip(value.getIDatCoord(colVal,rowVal)); //removes a ship as an observer when sunk
                }
            }
        }
    //If neither marks both boards to show nothing there
       else{
            for (Board value : board) {
                if (value.getzValue() < 1) {
                    value.MarkBoard(colVal, rowVal);
                }
            }
        }
        for (Board value : board) {
            value.performShow();
        }
        return true;
    }


}
