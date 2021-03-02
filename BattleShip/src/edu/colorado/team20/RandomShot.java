package edu.colorado.team20;

import java.util.Random;

public final class RandomShot implements ShotBehavior{

    public void makeShot(Board board, char col, int row) {
        Random randChar = new Random();

        //getting a random column
        char c = (char) ('A' + randChar.nextInt(10));

        Random randNum = new Random();

        //getting a random row
        int n = (randNum.nextInt(10) + 1);

        //coordinates resulting random board space


        while(!board.CheckSpot(c,n))
        {//while the randomly selected spot is not available
            c = (char) ('A' + randChar.nextInt(10));
            n = (randNum.nextInt(10) + 1);
        }
        board.MarkBoard(c, n);
    }
}
