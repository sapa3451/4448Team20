package edu.colorado.team20;

import edu.colorado.team20.Board;

import java.util.Random;

public final class RandomShot implements ShotBehavior{

    public void shot(Board board, char col, int row, int turnNum) {
        System.out.println("The computer is now taking their shot!");
        System.out.println();
        Random randChar = new Random();

        //getting a random column
        col = (char) ('A' + randChar.nextInt(10));

        Random randNum = new Random();

        //getting a random row
        row = (randNum.nextInt(10) + 1);

        //coordinates resulting random board space


        while(!board.CheckSpot(col,row))
        {//while the randomly selected spot is not available
            col = (char) ('A' + randChar.nextInt(10));
            row = (randNum.nextInt(10) + 1);
        }

        //TODO: need to add captain's quarters to computer ships

        // add shot to map of shot decision;

        board.MarkBoard(col, row);
    }
}
