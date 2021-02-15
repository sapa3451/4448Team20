package edu.colorado.team20;

import java.util.Arrays;
import java.util.HashMap;

public class Board {
    // thinking to have chars represent items on the board
        // S: ship
        // E: empty
        // D: destroyed ship
        // X: marks hit
    char[][] board = new char[10][10];
    // create a hashmap for alphabet lookup to integer value for board
    final HashMap<Character, Integer> alphaMap = new HashMap<Character, Integer>();

    // Board constructor
    Board() {
        // setting the board as empty
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = 'E';
            }
        }

        // making the alphaMap hashmap
        final char[] alphas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < alphas.length; i++) {
            // map ints to alphas
            alphaMap.put(alphas[i], i);
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
            if (i == 0) {
                System.out.println(col);
            }

            // this conditional fixes number lining
            if (row[i] == 10) {
                System.out.print(row[i]);
            } else {
                System.out.print(row[i] + " ");
            }
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'X') {
                    System.out.print("[X]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // method to check if the spot that was chosen is valid for decision
    boolean CheckSpot(char col, int row) {
        char position = board[alphaMap.get(col)][row-1]; // subtract one from row because indexing of array
        if (position != 'X' && position != 'D') {
            return true;
        }
        return false;

        // may need to have Ship work with this class to show that a Ship has been hit or not and mark in Ship class

    }

    // update the board
    public void MarkBoard(char col, int row) {
        // make a map that associates alphabet letter to column value
        board[alphaMap.get(col)][row-1] = 'X'; // subtract one from row because indexing of array

        // need to work with Ship class to know when to display sunken ship on the board
    }

}
