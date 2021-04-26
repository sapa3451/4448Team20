package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description: Used by CP after sinking first ship
 * Takes a shot at the board at every every elevation, at random coord, unless previous shot was a hit. If so randomly shoots one space
 * away from previous shot.
 * "Upgrade" Shot Type for CP
 */
public class LaserRandomShot implements ShotBehavior {
    /**
     * Description: Marks board at every elevation either at random location, or 1 off from previous shot that was a hit
     * or passed coords for dev test, then shows updated board
     * Params: a set of boards at different z elevations to mark, and the column and row coords for dev test shot
     * Returns: true
     */
    public boolean shot(Board[] board, char col, int row) {
        if (col == 'Z' && row == -1) {
            System.out.println("The computer is now taking their shot!");
            System.out.println();
            Random randChar = new Random();

            //getting a random column
            col = (char) ('A' + randChar.nextInt(10));

            Random randNum = new Random();

            //getting a random row
            row = (randNum.nextInt(10) + 1);

            //if the spot has been hit on all the boards, get a new random shot
            boolean allSpotsAlreadyHit = true;
            for (Board value : board) {
                if (value.CheckSpot(col, row)) {
                    allSpotsAlreadyHit = false;
                }
            }
            //checking for the random correct shot here
            while (allSpotsAlreadyHit) {//while the randomly selected spot is not available
                col = (char) ('A' + randChar.nextInt(10));
                row = (randNum.nextInt(10) + 1);
                for (Board value : board) {
                    if (value.CheckSpot(col, row)) {
                        allSpotsAlreadyHit = false;
                    }
                }
            }


            //a laser will mark all available boards, as below
            for (Board value : board) {
                if (value.getzValue() > -5) {
                    value.MarkBoard(col, row);
                }
            }
        }
        /*This is where the AI gets "smart." This long list of checks, loops, and such is making sure that the computer is accurately and randomly
        * choosing its next informed shot. see comments below */
        else {
            System.out.println("The computer is now taking their shot!");
            System.out.println();
            List<Character> check = new ArrayList<>(); //this is a list that will keep track of whether or not the computer can no longer make an informed shot with the given cord
            boolean shot = false;
            while (!shot) {
                if (check.contains('1') && check.contains('2') && check.contains('3') && check.contains('4')) {
                    return false; //this is saying that the cord must be removed from the stack
                }
                Random randNum = new Random();
                int direction = (randNum.nextInt(4) + 1); //random direction chosen
                if (direction == 1) {
                    if (col != 'J') { //making sure that the next shot wont go out of bounds, i.e. this direction goes one to the right, so col != J
                        for (Board value : board) { //if it finds that it has hit a captains quarters, destroy the ship!
                            if (value.GetPositionChar(col,row) == 'W') {
                                value.MarkBoard(col, row);
                                return true;
                            }
                        }
                        boolean allSpotsAlreadyHit = true;
                        for (Board value : board) { //checking to make sure the spot that will be shot at next is not already hit on all three boards
                            if (value.CheckSpot((char) (col + 1), row)) {
                                allSpotsAlreadyHit = false;
                            }
                        }
                        if (!allSpotsAlreadyHit) { //checks pass, so now we can go an mark all the boards appropriately
                            for (Board value : board) {
                                if (value.getzValue() > -5) {
                                    value.MarkBoard((char) (col + 1), row);
                                }
                            }
                            shot = true; //telling the loop that we performed a successful shot to return true
                        } else {
                            check.add('1'); //here is where we add to the list if this direction fails, if all directions fail, it returns
                        }
                    }
                    else {
                        check.add('1'); //here is where we add to the list if this direction fails, if all directions fail, it returns
                    }
                }
                /* Every if statement below has the same exact logic as this first one above, refer to comments above for understanding*/
                else if (direction == 2) {
                    if (row != 10) {
                        for (Board value : board) {
                            if (value.GetPositionChar(col,row) == 'W') {
                                value.MarkBoard(col, row);
                                return true;
                            }
                        }
                        boolean allSpotsAlreadyHit = true;
                        for (Board value : board) {
                            if (value.CheckSpot(col, row + 1)) {
                                allSpotsAlreadyHit = false;
                            }
                        }
                        if (!allSpotsAlreadyHit) {
                            for (Board value : board) {
                                if (value.getzValue() > -5) {
                                    value.MarkBoard(col, row + 1);
                                }
                            }
                            shot = true;
                        } else {
                            check.add('2');
                        }
                    }
                    else {
                        check.add('2');
                    }
                }
                else if (direction == 3) {
                    if (col != 'A') {
                        for (Board value : board) {
                            if (value.GetPositionChar(col,row) == 'W') {
                                value.MarkBoard(col, row);
                                return true;
                            }
                        }
                        boolean allSpotsAlreadyHit = true;
                        for (Board value : board) {
                            if (value.CheckSpot((char) (col - 1), row)) {
                                allSpotsAlreadyHit = false;
                            }
                        }
                        if (!allSpotsAlreadyHit) {
                            for (Board value : board) {
                                if (value.getzValue() > -5) {
                                    value.MarkBoard((char) (col - 1), row);
                                }
                            }
                            shot = true;
                        } else {
                            check.add('3');
                        }
                    }
                    else {
                        check.add('3');
                    }
                }
                else if (direction == 4) {
                    if (row != 1) {
                        for (Board value : board) {
                            if (value.GetPositionChar(col,row) == 'W') {
                                value.MarkBoard(col, row);
                                return true;
                            }
                        }
                        boolean allSpotsAlreadyHit = true;
                        for (Board value : board) {
                            if (value.CheckSpot(col, row - 1)){
                                allSpotsAlreadyHit = false;
                            }
                        }
                        if (!allSpotsAlreadyHit) {
                            for (Board value : board) {
                                if (value.getzValue() > -5) {
                                    value.MarkBoard(col, row - 1);
                                }
                            }
                            shot = true;
                        } else {
                            check.add('4');
                        }
                    }
                    else {
                        check.add('4');
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
