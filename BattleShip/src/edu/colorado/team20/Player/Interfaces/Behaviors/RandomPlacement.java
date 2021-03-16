package edu.colorado.team20.Player.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;

import java.util.Random;

public class RandomPlacement implements PlacementBehavior {
    public void place (int id, Board board, int size, int quartersPos) {
        boolean placed = false;
        while (!placed) {
            Random randOrient = new Random();
            int orientation = (randOrient.nextInt(2));
            int n = board.getRowSize();
            int nCheck = n - 3;
            char c = (char) ('A' + board.getRowSize() - 1);
            char cCheck = (char) (c - size+1);
            placed = isPlaced(id, orientation, n, nCheck, c, cCheck, size, quartersPos, board);
        }
    }

    private boolean isPlaced(int id, int orientation, int n, int nCheck, char c, char cCheck, int size, int quartersPos, Board board) {
        boolean placed;
        if (orientation == 1) {
            while (c > cCheck) {
                //getting a random column
                Random randChar = new Random();
                c = (char) ('A' + randChar.nextInt(10));

                //getting a random row
                Random randNum = new Random();
                n = (randNum.nextInt(10) + 1);
            }
        } else {
            while (n > nCheck) {
                //getting a random column
                Random randChar = new Random();
                c = (char) ('A' + randChar.nextInt(10));

                //getting a random row
                Random randNum = new Random();
                n = (randNum.nextInt(10) + 1);
            }
        }
        placed = board.SetShipPos(id, n, c, orientation, size, quartersPos);
        return placed;
    }
}
