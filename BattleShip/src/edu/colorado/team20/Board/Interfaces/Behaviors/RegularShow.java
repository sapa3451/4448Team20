package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.ShowBehavior;


/**
 * Description: This behavior, using the strategy pattern, implements the show behavior for the board. Here, this is the regular show.
 * This is used for a player to see their own board, it shows all ships, hits, and misses on every board.
 * E = Empty
 * H = Hit
 * X = Miss
 * W = Weak CQ
 * Q = CQ
 * D = Destroyed
 * S = Ship
 * P = Plane
 * [~] = Underwater
 * [ ] = Surface
 * [*] = Air
 */
public class RegularShow implements ShowBehavior {
    public void show(Board board) {
        // create arrays to hold board rows and columns
        if (board.getzValue() > 0) {
            System.out.println("**********PLAYER AIR BOARD**********");
        }
        else if (board.getzValue() < 0) {
            System.out.println("**********PLAYER UNDERWATER BOARD**********");
        }
        else if (board.getzValue() == 0) {
            System.out.println("**********PLAYER SURFACE BOARD**********");
        }
        char[] col = new char[board.getColumnSize() * 3 + 2];
        int[] row = new int[board.getRowSize()];
        char start = 'A';
        col[0] = ' ';
        for (int i = 1; i < board.getColumnSize() * 3 - 1; i = i + 3) {
            col[i + 1] = ' ';
            col[i + 2] = ' ';
            col[i + 3] = start;
            start += 1;
        }
        for (int i = 0; i < board.getRowSize(); i++) {
            row[i] = i + 1;
        }

        // print board
        for (int i = 0; i < board.getRowSize(); i++) {
            if (i == 0) {
                System.out.println(col);
            }

            // this conditional fixes number lining
            if (row[i] >= 10) {
                System.out.print(row[i]);
            } else {
                System.out.print(row[i] + " ");
            }
            for (int j = 0; j < board.getColumnSize(); j++) {
                if (board.getBoard()[i][j] == 'X') {
                    System.out.print("[X]");
                } else if (board.getBoard()[i][j] == 'S') { // P for plane
                    if (board.getzValue() > 0) {
                        System.out.print("[P]");
                    }
                    else {
                        System.out.print("[S]");
                    }
                } else if (board.getBoard()[i][j] == 'Q') {
                    System.out.print("[Q]");
                } else if (board.getBoard()[i][j] == 'D') {
                    System.out.print("[D]");
                } else if (board.getBoard()[i][j] == 'W') {
                    System.out.print("[W]");
                } else if (board.getBoard()[i][j] == 'H') {
                    System.out.print("[H]");
                } else {
                    if (board.getzValue() > 0) {
                        System.out.print("[^]");
                    }
                    else if (board.getzValue() < 0) {
                        System.out.print("[~]");
                    }
                    else if (board.getzValue() == 0) {
                        System.out.print("[ ]");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
