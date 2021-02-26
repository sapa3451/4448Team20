package edu.colorado.team20;

import java.util.HashMap;

public final class ComputerBoard implements IBoard {
    private final char[][] board;
    private final HashMap<Character, Integer> alphaMap = new HashMap<>();
    private final int rowSize = 10;
    private final int columnSize = 10;
    // TODO: needing to add a C char to represent the captain's quarters for sonar radar
    // TODO: Find a way to show way how many times the captain's quarters has been hit to know if
    //  it is going to be a destroyed ship
    public ComputerBoard() {

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

    public void Show() {
        // create arrays to hold board rows and columns
        char[] col = new char[columnSize*3+2];
        int [] row = new int[rowSize];
        char start = 'A';
        col[0] = ' ';
        for (int i = 1; i < this.columnSize*3-1; i = i + 3) {
            col[i+1] = ' ';
            col[i+2] = ' ';
            col[i+3] = start;
            start += 1;
        }
        for (int i = 0; i < this.rowSize; i++) {
            row[i] = i+1;
        }

        // print board
        for (int i = 0; i < this.rowSize; i++) {
            if (i == 0) {
                System.out.println(col);
            }

            // this conditional fixes number lining
            if (row[i] >= 10) {
                System.out.print(row[i]);
            } else {
                System.out.print(row[i] + " ");
            }
            for (int j = 0; j < this.columnSize; j++) {
                if (this.board[i][j] == 'D') {
                    System.out.print("[D]");
                }
                else if (this.board[i][j] == 'X') {
                    System.out.print("[X]");
                }
                else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

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
    public boolean SetShipPos(int row, char col, int direction, int size) {
        char positionChar = board[row-1][alphaMap.get(col)];
        int quarters = size/2;
        if (size == 2) {
            quarters = 0;
        }
        int rowC = row;
        char colC = col;
        if (positionChar == 'S') {
            System.out.println("Ship already placed here!");
            return false;
        }
        else {
            if (direction == 1) {
                for (int i = 0; i < size; i++) {
                    board[row - 1][alphaMap.get(col)] = 'S';
                    col += 1;
                }
                char o = (char) (colC + quarters);
                board[rowC - 1][alphaMap.get(o)] = 'Q';
            }
            else {
                for (int i = 0; i < size; i++) {
                    board[row - 1][alphaMap.get(col)] = 'S';
                    row += 1;
                }
                board[rowC - 1 + quarters][alphaMap.get(colC)] = 'Q';
            }
        }
        return true;
    }
    // TODO: Need to create a function for sonar radar

}
