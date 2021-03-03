package edu.colorado.team20;

import edu.colorado.team20.Ship;

import java.util.HashMap;

public abstract class Board {
    protected final char[][] board;
    protected int[][] idBoard;
    protected final HashMap<Character, Integer> alphaMap = new HashMap<>();
    protected final int rowSize = 10;
    protected final int columnSize = 10;
    protected Ship[] fleet = new Ship[3]; // do we need this still??
    protected HashMap<Integer, String> startPos = new HashMap<>();
    protected HashMap<Integer, String> shipCapQPos = new HashMap<>();


    public Board(Ship[] fleet) {
        this.board = new char[this.rowSize][this.columnSize];
        this.idBoard = new int[this.rowSize][this.columnSize];
        char[] alphas = new char[this.rowSize];

        // creating empty board and idBoard
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.columnSize; j++) {
                this.board[i][j] = 'E';
                this.idBoard[i][j] = 0; // 0 means no ship id is there (ids go from 1,2,...)
                alphas[i] = (char) ('A' + i);
            }
        }

        // mapping ints to alphas
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

    // show the placement of the player's ships
    public void ShowBoardToPlayer() {
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
                if (this.board[i][j] == 'X') {
                    System.out.print("[X]");
                }
                else if (this.board[i][j] == 'S') {
                    System.out.print("[S]");
                }
                else if (this.board[i][j] == 'Q') {
                    System.out.print("[Q]");
                }
                else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // show the opponent the player's board
    // shows opponent what spots they hit and destroyed ships
    public void ShowBoardToOpponent() {
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

    // function to check if spot was already shot at
    public boolean CheckSpot(char col, int row) {
        char position = board[row-1][alphaMap.get(col)];// subtract one from row because indexing of array
        return position != 'X' && position != 'D';
    }

    // function to mark board after a shot
    // returns id of ship if it has been hit (-1 if no ship hit)
    public int MarkBoard(char col, int row) {
        // call check spot in the beginning to check if spot is valid
        char positionChar = board[row-1][alphaMap.get(col)];

        if (positionChar == 'E') { // if shot decision was empty --> mark as X
            board[row-1][alphaMap.get(col)] = 'X'; // subtract one from row because indexing of array
        }
        else if (positionChar == 'Q') {
            board[row-1][alphaMap.get(col)] = 'W'; // if gets hit more than once keep at W
        }
        else { // decision was a ship --> mark as D
            board[row-1][alphaMap.get(col)] = 'D'; // subtract one from row because indexing of array
        }
        this.ShowBoardToOpponent(); // show the updated board
        return (this.idBoard[row-1][alphaMap.get(col)] != 0 ? this.idBoard[row-1][alphaMap.get(col)] : -1);
    }

    public boolean SetShipPos(int id, int row, char col, int direction, int size) {
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
            if (direction == 1) { // horizontal
                startPos.put(id, col+String.valueOf(row-1)+1); // add start position to map
                for (int i = 0; i < size; i++) {
                    board[row - 1][alphaMap.get(col)] = 'S';
                    idBoard[row - 1][alphaMap.get(col)] = id;
                    col += 1;
                }
                char o = (char) (colC + quarters);
                board[rowC - 1][alphaMap.get(o)] = 'Q';
                shipCapQPos.put(id, o+String.valueOf(rowC - 1)+1); // add captain's quarter's to map
            }
            else { // vertical
                startPos.put(id, col+String.valueOf(row)+0); // add start position to map
                for (int i = 0; i < size; i++) {
                    board[row - 1][alphaMap.get(col)] = 'S';
                    idBoard[row - 1][alphaMap.get(col)] = id;
                    row += 1;
                }
                board[rowC - 1 + quarters][alphaMap.get(colC)] = 'Q';
                shipCapQPos.put(id, colC+String.valueOf(rowC - 1 + quarters)+1); // add captain's quarter's to map
            }

        }
        // TODO: find a way to call Show function so can make SetShipPos
        showIdBoard();
        return true;
    }

    // this is just for testing purposes to show idboard
    public void showIdBoard() {
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
                if (this.idBoard[i][j] == 0) {
                    System.out.print("[ ]");
                }
                else {
                    System.out.print("[" + this.idBoard[i][j] + "]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // function to mark ship as all destroyed when captains' quarters destroyed
    public void updateShipChars(char col, int row, int size, int direction) {
        if (direction == 1) { // direction is
            for (int i = 0; i < size; i++) { // horizontal
                board[row][alphaMap.get(col)] = 'D';
                col += 1;
            }
        }
        else {
            for (int i = 0; i < size; i++) { // vertical
                board[row][alphaMap.get(col)] = 'D';
                row += 1;
            }
        }
    }

    public String getShipCaptainQPos(int id) { return this.shipCapQPos.get(id); }
    public String getShipStartPos(int id) { return this.startPos.get(id); }

    public abstract void ShowSonarPulse(char c, int i);
}
