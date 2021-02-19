package edu.colorado.team20;

import java.util.Random;

public class ComputerPlayer {

    //computer player random turn
    public String RandomShot(Board board) {
        Random randChar = new Random();

        //getting a random column
        char c = (char) ('A' + randChar.nextInt(10));

        Random randNum = new Random();

        //getting a random row
        int n = (randNum.nextInt(10) + 1);

        //coordinates resulting random board space


        while(!board.MarkBoard(c,n))
        {//while the randomly selected spot is not available
            c = (char) ('A' + randChar.nextInt(10));
            n = (randNum.nextInt(10) + 1);
        }

        String nConvert=String.valueOf(n);
        return c+nConvert;
    }

    public void PlaceShips(Board board){

    }

}
