package edu.colorado.team20;

import java.util.Random;

public final class ComputerPlayer extends Player{
    public ComputerPlayer(Board board) {
        super(board);
        placementBehavior = new RandomPlacement();
    }

    public void Shot(Board board, char col, int row) {
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

        if (board.MarkBoard(c, n) != 0) {
            // ship has been hit
            // TODO: we need to search through ships by id and mark the health of that ship down
            // TODO: we need to also check if this spot was a captain's quarters
        }
    }

}
