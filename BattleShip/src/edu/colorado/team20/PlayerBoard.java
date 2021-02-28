package edu.colorado.team20;

import java.util.HashMap;

public final class PlayerBoard extends Board {

    public PlayerBoard(Ship[] fleet) {
        super(fleet);
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
        Show();
        return true;
    }
    // TODO: Need to create a function for sonar radar
}
