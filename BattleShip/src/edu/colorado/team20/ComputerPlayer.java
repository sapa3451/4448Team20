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

        String nConvert=String.valueOf(n-1);
        return c+nConvert;
    }

    public String RandShipPlacement(Ship compShip){

        //getting a random column
        Random randChar = new Random();
        char c = (char) ('A' + randChar.nextInt(10));

        //getting a random row
        Random randNum = new Random();
        int n = (randNum.nextInt(10) + 1);

        //setting vertical/horizontal (1 horizontal,0 vertical)
        Random randOrient = new Random();
        int orientation =(randOrient.nextInt(2));

        compShip.setColumnAndRow(c,n,orientation);

        String nConvert=String.valueOf(n-1);
        String oConvert=String.valueOf(orientation);
        //Returns coordinates and orientation for testing
        return c+nConvert+oConvert;
    }

}
