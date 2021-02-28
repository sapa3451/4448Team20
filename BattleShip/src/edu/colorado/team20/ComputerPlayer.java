package edu.colorado.team20;

import java.util.Random;

public final class ComputerPlayer implements IPlayer {
    private final Board board;

    public ComputerPlayer(Board board) {
        this.board = board;
    }


    public Board getBoard () {
        return board;
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
        board.MarkBoard(c, n);
    }

    public void placeBattleship () {
        boolean placed = false;
        while (!placed) {
            Random randOrient = new Random();
            int orientation = (randOrient.nextInt(2));
            int n = this.board.getRowSize();
            int nCheck = n - 3;
            char c = (char) ('A' + this.board.getRowSize() - 1);
            char cCheck = (char) (c - 3);
            placed = isPlaced(orientation, n, nCheck, c, cCheck, 4);
        }
    }

    public void placeMinesweeper () {
        boolean placed = false;
        while (!placed) {
            Random randOrient = new Random();
            int orientation = (randOrient.nextInt(2));
            int n = this.board.getRowSize();
            int nCheck = n - 3;
            char c = (char) ('A' + this.board.getRowSize() - 1);
            char cCheck = (char) (c - 3);
            placed = isPlaced(orientation, n, nCheck, c, cCheck, 2);
        }
    }

    public void placeDestroyer (){
        boolean placed = false;
        while (!placed) {
            Random randOrient = new Random();
            int orientation = (randOrient.nextInt(2));
            int n = this.board.getRowSize();
            int nCheck = n - 3;
            char c = (char) ('A' + this.board.getRowSize() - 1);
            char cCheck = (char) (c - 3);
            placed = isPlaced(orientation, n, nCheck, c, cCheck, 3);
        }
    }

    private boolean isPlaced(int orientation, int n, int nCheck, char c, char cCheck, int size) {
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
        placed = this.board.SetShipPos(n, c, orientation, size);
        return placed;
    }
}
