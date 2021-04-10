package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Player will initially start with this behavior

public final class CannonRandomShot implements ShotBehavior {

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

            //coordinates resulting random board space


            while (!board[1].CheckSpot(col, row)) {//while the randomly selected spot is not available
                col = (char) ('A' + randChar.nextInt(10));
                row = (randNum.nextInt(10) + 1);
            }

            //takes in only the first board, the surface board
            board[1].performMarkBoard(col, row);
        }
        /*This is where the AI gets "smart." This long list of checks, loops, and such is making sure that the computer is accurately and randomly
         * choosing its next informed shot. see comments below */
        else {
            List<Character> check = new ArrayList<>(); //this is a list that will keep track of whether or not the computer can no longer make an informed shot with the given cord
            boolean shot = false;
            while (shot == false) {
                if (check.contains('1') && check.contains('2') && check.contains('3') && check.contains('4')) {
                    return false; //this is saying that the cord must be removed from the stack
                }
                Random randNum = new Random();
                int direction = (randNum.nextInt(4) + 1); //random direction chosen
                if (direction == 1) {
                    if (col != 'J') { //making sure that the next shot wont go out of bounds, i.e. this direction goes one to the right, so col != J
                        if (board[1].GetPositionChar(col, row) == 'W') { //if it finds that it has hit a captains quarters, destroy the ship!
                            board[1].performMarkBoard(col, row);
                            return true;
                        }
                        if (board[1].CheckSpot((char) (col + 1), row)) { //checking to make sure the spot that will be shot at next is not already hit on the surface board
                            board[1].performMarkBoard((char) (col + 1), row); //checks pass, so now we can go an mark the board appropriately
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
                        if (board[1].GetPositionChar(col, row) == 'W') {
                            board[1].performMarkBoard(col, row);
                            return true;
                        }
                        if (board[1].CheckSpot(col, row + 1)) {
                            board[1].performMarkBoard(col, row + 1);
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
                        if (board[1].GetPositionChar(col, row) == 'W') {
                            board[1].performMarkBoard(col, row);
                            return true;
                        }
                        if (board[1].CheckSpot((char) (col - 1), row)) {
                            board[1].performMarkBoard((char) (col - 1), row);
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
                        if (board[1].GetPositionChar(col, row) == 'W') {
                            board[1].performMarkBoard(col, row);
                            return true;
                        }
                        if (board[1].CheckSpot(col, row - 1)) {
                            board[1].performMarkBoard(col, row - 1);
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
        return true;
    }
}
