package edu.colorado.team20;

import java.util.HashMap;

public abstract class Board {
    protected final char[][] board;
    protected final HashMap<Character, Integer> alphaMap = new HashMap<>();
    protected final int rowSize = 10;
    protected final int columnSize = 10;
    protected Ship[] fleet = new Ship[3];


    public Board(Ship[] fleet) {
        this.board = new char[this.rowSize][this.columnSize];
        char[] alphas = new char[this.rowSize];
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.columnSize; j++) {
                this.board[i][j] = 'E';
                alphas[i] = (char) ('A' + i);
            }
        }
        for (int i = 0; i < alphas.length; i++) {
            // map ints to alphas
            this.alphaMap.put(alphas[i], i);
        }
        this.fleet = fleet;
    }


    public int getRowSize () {
        return rowSize;
    }

    public int getColumnSize () {
        return columnSize;
    }

    public char GetPositionChar(char col, int row) {
        return board[row-1][alphaMap.get(col)];
    }

    public abstract void Show();

    public boolean CheckSpot(char col, int row) {
        char position = board[row-1][alphaMap.get(col)];// subtract one from row because indexing of array
        return position != 'X' && position != 'D';
    }

    public void MarkBoard(char col, int row) {
        // call check spot in the beginning to check if spot is valid
        if (this.CheckSpot(col, row)) {
            char positionChar = board[row-1][alphaMap.get(col)];

            if (positionChar == 'E') { // if shot decision was empty --> mark as X
                board[row-1][alphaMap.get(col)] = 'X'; // subtract one from row because indexing of array
            }
            else if (positionChar == 'Q') {
                board[row-1][alphaMap.get(col)] = 'S';
            }
            else { // decision was a ship --> mark as D
                board[row-1][alphaMap.get(col)] = 'D'; // subtract one from row because indexing of array
            }
            this.Show(); // show the updated board
        }
    }
    public abstract boolean SetShipPos(int row, char col, int direction, int size);
}
