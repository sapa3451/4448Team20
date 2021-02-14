package edu.colorado.team20;

import java.util.Random;

public class ComputerPlayer {

    //computer player random turn
    public String RandomShot() {
        Random randChar = new Random();

        //getting a random column
        char c = (char) ('A' + randChar.nextInt(10));

        Random randNum = new Random();

        //getting a random row
        String n = String.valueOf(randNum.nextInt(10) + 1);

        //resulting random board space
        String result = c+n;

        return result;
    }

}
