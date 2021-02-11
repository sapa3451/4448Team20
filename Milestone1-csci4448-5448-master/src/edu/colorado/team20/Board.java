package edu.colorado.team20;

public class Board {
    // thinking to have chars represent items on the board
        // S: ship
        // E: empty
        // D: destroyed ship
        // X: marks hit
    char[][] board = new char[10][10];

    // Board constructor
    Board() {
        // set all the positions in board to empty (E)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = 'E';
            }
        }
    }

    // show the board
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
    }

}
