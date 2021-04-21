package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.ShowBehavior;


/**
 * Description: This behavior, using the strategy pattern, implements the show behavior for the board. Here, this is the hidden show.
 * This is used when a human is playing against a computer. This will print all boards that has its un-hit ships hidden but all hits and misses shown.
 * E = Empty
 * H = Hit
 * X = Miss
 * W = Weak CQ
 * D = Destroyed
 * S = Ship
 * P = Plane
 * Q = CQ
 * [~] = Underwater
 * [ ] = Surface
 * [*] = Air
 */
public class HiddenShow implements ShowBehavior {
    public void show (Board board) {
        // create arrays to hold board rows and columns
        if (board.getzValue() > 0) {
            System.out.println("**********COMPUTER AIR BOARD**********");
        }
        else if (board.getzValue() < 0) {
            System.out.println("**********COMPUTER UNDERWATER BOARD**********");
        }
        else if (board.getzValue() == 0) {
            System.out.println("**********COMPUTER SURFACE BOARD**********");
        }
        char[] col = new char[board.getColumnSize()*3+2];
        int [] row = new int[board.getRowSize()];
        char start = 'A';
        col[0] = ' ';
        for (int i = 1; i < board.getColumnSize()*3-1; i = i + 3) {
            col[i+1] = ' ';
            col[i+2] = ' ';
            col[i+3] = start;
            start += 1;
        }
        for (int i = 0; i < board.getRowSize(); i++) {
            row[i] = i+1;
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
                if (board.getBoard()[i][j] == 'D') {
                    System.out.print("[D]");
                }
                else if (board.getBoard()[i][j] == 'X') {
                    System.out.print("[X]");
                }
                else if (board.getBoard()[i][j] == 'H') {
                    System.out.print("[H]");
                }
                else if (board.getBoard()[i][j] == 'W') {
                    System.out.print("[W]");
                }
                else {
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
