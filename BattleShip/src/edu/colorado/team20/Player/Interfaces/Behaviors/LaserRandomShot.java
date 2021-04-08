package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.Random;

//This is the start of the interface for the laser
//TODO: before any further implementation, underwater board must be implemented

public class LaserRandomShot implements ShotBehavior {
    public void shot(Board[] board, char col, int row) {
        System.out.println("The computer is now taking their shot!");
        System.out.println();
        Random randChar = new Random();

        //getting a random column
        col = (char) ('A' + randChar.nextInt(10));

        Random randNum = new Random();

        //getting a random row
        row = (randNum.nextInt(10) + 1);

        //coordinates resulting random board space


        while(!board[0].CheckSpot(col,row))
        {//while the randomly selected spot is not available
            col = (char) ('A' + randChar.nextInt(10));
            row = (randNum.nextInt(10) + 1);
        }

        //TODO: need to add captain's quarters to computer ships

        // add shot to map of shot decision;

        //a laser will mark all available boards, as below
        for (Board value : board) {
            if (value.getzValue() > -5) {
                value.performMarkBoard(col, row);
            }
        }
    }
}
