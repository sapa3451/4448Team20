package edu.colorado.team20;

import java.util.Arrays;

public class Board {
    // thinking to have chars represent items on the board
        // S: ship
        // E: empty
        // D: destroyed ship
        // X: marks hit
    String[][] board = new String[10][10];

    // Board constructor
    Board() {
        char c = 'A';
        int r = 1;
        // what I changed here was setting our board to have the values (A1, A2, etc.) represent the space being empty.
        // When something is placed there, we can put "S","D", and so on. Having the values "A1", etc.
        // makes lookup much much easier when messing with the board.
        for (int i = 0; i < 10; i++) {
            c = 'A';
            for (int j = 0; j < 10; j++) {
                board[i][j] = c + String.valueOf(r);
                c += 1;
            }
            r += 1;
        }
    }

    // show the board
    // the big to-do I see here is figuring out how to create a board who's display is as nice as this one (I couldn't think of any ideas at the moment)
    // that is also storing our hits and space occupancy (why I created another show board type for the time being for testing)
    public void Show() {
        // create arrays to hold board rows and columns
        final char[] col = {' ', ' ', ' ', 'A', ' ', ' ', 'B', ' ', ' ', 'C', ' ', ' ', 'D', ' ', ' ', 'E', ' ', ' ', 'F', ' ', ' ', 'G', ' ', ' ', 'H', ' ', ' ', 'I', ' ', ' ', 'J'};
        final int[] row = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // print board
        for (int i = 0; i < 10; i++) {
            if (i == 0) { System.out.println(col); }

            // this conditional fixes number lining
            if (row[i] == 10) { System.out.print(row[i]); }
            else { System.out.print(row[i] + " "); }
            for (int j = 0; j < 10; j++) {
                System.out.print("[ ]");
            }
            System.out.println();
        }
        System.out.println();
    }

    // update the board
    public void MarkBoard(String input) {
        // finding the space on the board that is equal
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (input.equals(board[i][j])) {
                    board[i][j] = "X ";
                }
            }
        }
        //printing out board for test purposes
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
