package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.Scanner;

/**
 * Description:
 */
public class BombRun implements ShotBehavior{
    public boolean shot(Board[] board, char colv,  int row) {

        if (colv != 'Z' && row != -1) {
            board[1].MarkBoard(colv,row);
            return true;
        }//For DevTest Skip

        char colVal = ' ';
        int rowVal = -1;

        boolean validIput = false;

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
            for(char i ='A'; i<'A'+board[1].getRowSize();i+=2){

                board[1].MarkBoard(i,rowInput);
                board[1].MarkBoard(i,rowInput);
                board[2].MarkBoard(i,rowInput);

            }
            //
        }

    //Takes in which column user wants to BombRun if they want horizontal, checks for bad input
        else{
            System.out.println("Type which column (A-J) you would like to target: ");
            input = sc.nextLine();

            //Checking for bad input here
            while(input.toCharArray()[0] < 'A' || input.toCharArray()[0] > 'J'){//Checking for user error
                System.out.println("Invalid column! Please enter a valid column (A-J): ");
                input = sc.nextLine(); // Read user input
            }
            //Do Actual Stuff
            char col=input.toCharArray()[0];
            for(int i=1; i<board[1].getColumnSize();i+=2){

                board[1].MarkBoard(col,i);
                board[1].MarkBoard(col,i);
                board[2].MarkBoard(col,i);

            }
        }

        return true;
    }
}
