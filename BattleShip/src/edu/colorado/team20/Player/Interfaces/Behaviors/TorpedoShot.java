package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.Scanner;

public class TorpedoShot implements ShotBehavior {

    //Implements the Torpedo Ability
    public boolean shot(Board[] board, char colv, int row) {
        if (colv != 'Z' && row != -1) {
            board[1].MarkBoard(colv,row);
            return true;
        }//For DevTest Skip

        char colVal = ' ';
        int rowVal = -1;

        boolean validIput = false;

        System.out.println("You've Decided to use a Torpedo Shot");
        System.out.println("You must now choose where launch your torpedo");

        //Gets user input for Column coordinate, checks for bad input
        System.out.println("Type which column (A-J) you would like to target: ");
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string

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

        String coords=String.valueOf(colVal)+String.valueOf(rowVal);

        //Checks IDs at surface and Underwater Board
        int UnderwaterID = board[2].getIDatCoord(colVal,rowVal);
        int SurfaceID = board[1].getIDatCoord(colVal,rowVal);
        int remainHP = -3;

    //First checks to see if there is a sub underwater and if so sinks it
        if(UnderwaterID!=0){
//            while(remainHP>0 && remainHP != -3){
//                remainHP=board[2].updateGamePieceOnHit(UnderwaterID);
//            }
            while(board[2].updateGamePieceOnHit(UnderwaterID)>0){
                //remainHP=board[2].updateGamePieceOnHit(UnderwaterID);
            }
            board[2].updateGamePieceChars(board[2].getGamePieceCoordinates(UnderwaterID));
            board[2].removeShip(board[2].getIDatCoord(colVal,rowVal)); //removes a ship as an observer when sunk
            board[2].performShow();
        }
    //If not checks if there's a ship at coord if so sinks it
        else if(SurfaceID!=0){
            while(board[1].updateGamePieceOnHit(SurfaceID)>0){
                //remainHP=board[2].updateGamePieceOnHit(UnderwaterID);
            }
            board[1].updateGamePieceChars(board[1].getGamePieceCoordinates(SurfaceID));
            board[1].removeShip(board[1].getIDatCoord(colVal,rowVal)); //removes a ship as an observer when sunk
            board[1].performShow();
        }
    //If neither marks both boards to show nothing there
       else{
           board[2].MarkBoard(colVal,rowVal);
           board[1].MarkBoard(colVal,rowVal);
        }
        return true;
    }


}
