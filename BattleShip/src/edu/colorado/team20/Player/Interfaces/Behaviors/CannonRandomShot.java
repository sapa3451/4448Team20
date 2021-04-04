package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

import java.util.Random;

//Player will initially start with this behavior

public final class CannonRandomShot implements ShotBehavior {

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

        //takes in only the first board, the surface board
        board[1].performMarkBoard(col, row);
    }
}
