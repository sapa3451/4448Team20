package edu.colorado.team20;

import java.util.Random;

public final class ComputerPlayer extends Player{
    public ComputerPlayer(Board board) {
        super(board);
        placementBehavior = new RandomPlacement();
    }

    public int Shot(Board board, char col, int row, int turnNum) {
//        System.out.println("The computer is now taking their shot!");
//        System.out.println();
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

        //TODO: need to add captain's quarters to computer ships

        // add shot to map of shot decisions
        this.addShotFromTurn(turnNum, c+String.valueOf(n));

        return board.MarkBoard(c, n);
    }

}
