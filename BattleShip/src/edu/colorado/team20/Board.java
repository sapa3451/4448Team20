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
    final char boardType; // this can either be 'C' or 'P'

    // Board constructor
    public Board(char type) {
        if (type == 'P') {
            this.boardType = 'P';
        } else {
            this.boardType = 'C';
        }

        // setting the board as empty
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.board[i][j] = 'E';
            }
        }

        // making the alphaMap hashmap
        final char[] alphas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < alphas.length; i++) {
            // map ints to alphas
            this.alphaMap.put(alphas[i], i);
        }
    }

    public char[][] getBoard () {
        return board;
    }

    // show the board
    // the big to-do I see here is figuring out how to create a board who's display is as nice as this one (I couldn't think of any ideas at the moment)
    // that is also storing our hits and space occupancy (why I created another show board type for the time being for testing)
    public void Show(Board board) {
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
                if (board.getBoard()[i][j] == 'X') {
                    System.out.print("[X]");
                }
                else if (board.getBoard()[i][j] == 'S') {
                    System.out.print("[S]");
                }
                else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // method to check if the spot that was chosen is valid for decision
    public boolean CheckSpot(char col, int row) {
        char position = board[row-1][alphaMap.get(col)];; // subtract one from row because indexing of array
        if (position != 'X' && position != 'D') {
            return true;
        }
        return false;
    }

    // update the board
    public boolean MarkBoard(char col, int row) {
        // call check spot in the beginning to check if spot is valid
        if (this.CheckSpot(col, row)) {
            char positionChar = board[row-1][alphaMap.get(col)];;

            if (positionChar == 'E') { // if shot decision was empty --> mark as X
                board[row-1][alphaMap.get(col)] = 'X'; // subtract one from row because indexing of array

                // print out information according to whose board is being checked
                if (this.boardType == 'P') { // checking the player board (was computer's shot)
                    System.out.println("Your opponent missed their shot!");
                } else { // checking the computer board (was player's shot)
                    System.out.println("You missed!");
                }
            }
            else { // decision was a ship --> mark as D
                board[row-1][alphaMap.get(col)] = 'D'; // subtract one from row because indexing of array

                // print out information according to whose board is being checked
                if (this.boardType == 'P') { // checking the player board (was computer's shot)
                    System.out.println("Your opponent hit one of you ships!");
                }
                else { // checking the computer board (was player's shot)
                    System.out.println("You hit one of your opponent's ship!");
                }
            }
            return true; // board was marked correctly
        }
        return false; // board was not marked
    }

    // function to help with testing to get what the char is in the col/row position on board
    public char GetPositionChar(char col, int row) {
        return board[row-1][alphaMap.get(col)];
    }

    // function to set ship positioning on board
    public void SetShipPos(Ship ship, Board board) {
        // TODO: Need to wait till Ships selection is implemented in order to place the ships on the board
        // need to take in Ship object to set positions for ships
        char[] col = ship.getColumn();
        int[] row = ship.getRow();

        // set ship on the board
        for (int i = 0; i < ship.getShipSize(); i++) {
            this.board[row[i]-1][alphaMap.get(col[i])] = 'S';
        }
    }

}
