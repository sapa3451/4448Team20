package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.ShowBehavior;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: This behavior, using the strategy pattern, implements the show behavior for the board. Here, this is the sonar show.
 * This is used for a player when they use the sonar shot. It will show the ships in teh specified location on all boards.
 */
public class SonarBoardShow implements ShowBehavior {
    final int row;
    final char col;
    public SonarBoardShow(char c, int i) {
        this.row = i - 1; // subtract one for zero indexing
        this.col = c;
    }
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
        char[] columns = new char[board.getColumnSize()*3+2];
        int [] rows = new int[board.getRowSize()];
        char start = 'A';
        columns[0] = ' ';
        for (int i = 1; i < board.getColumnSize()*3-1; i = i + 3) {
            columns[i+1] = ' ';
            columns[i+2] = ' ';
            columns[i+3] = start;
            start += 1;
        }
        for (int i = 0; i < board.getRowSize(); i++) {
            rows[i] = i+1;
        }

        // need to check if sonar pulse is out of bounds for certain directions
        // adding available positions to a queue
        Queue<Pair<Character, Integer>> positions = new LinkedList<>();

        // check if 2 up from midpoint is available
        if ((this.row-2) > -1) {
            positions.add(new Pair(this.col, this.row-2));
        }
        // check one up and one left
        if (board.getAlphaMap().get(this.col)-1 > -1 && (this.row-1) > -1) {
            positions.add(new Pair((char) (((int) this.col)-1), this.row-1));
        }
        // check one up
        if ((this.row-1) > -1) {
            positions.add(new Pair(this.col, this.row-1));
        }
        // check one up and one right
        if (board.getAlphaMap().get(this.col)+1 < board.getColumnSize() && (this.row-1) > -1) {
            positions.add(new Pair((char) (((int) this.col)+1), this.row-1));
        }
        // check 2 to the left
        if (board.getAlphaMap().get(this.col)-2 > -1) {
            positions.add(new Pair((char) (((int) this.col)-2), this.row));
        }
        // check 1 to the left
        if (board.getAlphaMap().get(this.col)-1 > -1) {
            positions.add(new Pair((char) (((int) this.col)-1), this.row));
        }
        // add the midpoint
        positions.add(new Pair(this.col, this.row));
        // check 1 to the right
        if (board.getAlphaMap().get(this.col)+1 < board.getColumnSize()) {
            positions.add(new Pair((char) (((int) this.col)+1), this.row));
        }
        // check 2 to the right
        if (board.getAlphaMap().get(this.col)+2 < board.getColumnSize()) {
            positions.add(new Pair((char) (((int) this.col)+2), this.row));
        }
        // check one down and one left
        if (board.getAlphaMap().get(this.col)-1 > -1 && (this.row+1) < board.getRowSize()) {
            positions.add(new Pair((char) (((int) this.col)-1), this.row+1));
        }
        // check one down
        if ((this.row+1) < board.getRowSize()) {
            positions.add(new Pair(this.col, this.row+1));
        }
        // check one down and one right
        if (board.getAlphaMap().get(this.col)+1 < board.getColumnSize() && (this.row+1) < board.getRowSize()) {
            positions.add(new Pair((char) (((int) this.col)+1), this.row+1));
        }
        // check 2 down
        if ((this.row+2) < board.getRowSize()) {
            positions.add(new Pair(this.col, this.row+2));
        }


        // print board
        for (int i = 0; i < board.getRowSize(); i++) {
            if (i == 0) {
                System.out.println(columns);
            }

            // this conditional fixes number lining
            if (rows[i] >= 10) {
                System.out.print(rows[i]);
            } else {
                System.out.print(rows[i] + " ");
            }
            for (int j = 0; j < board.getColumnSize(); j++) {
                // check to see if area is part of the radar pulse
                if (!positions.isEmpty() && board.getAlphaMap().get(positions.peek().getKey()) == j && positions.peek().getValue() == i) {
                    positions.remove(); // remove the position
                    if (board.getBoard()[i][j] == 'X') {
                        System.out.print("[X]");
                    }
                    else if (board.getBoard()[i][j] == 'S' || board.getBoard()[i][j] == 'Q') {
                        System.out.print("[S]");
                    }
                    else if (board.getBoard()[i][j] == 'W') {
                        System.out.print("[W]");
                    }
                    else if (board.getBoard()[i][j] == 'H') {
                        System.out.print("[H]");
                    }
                    else if (board.getBoard()[i][j] == 'D') {
                        System.out.print("[D]");
                    }
                    else {
                        System.out.print("[*]");
                    }
                }
                else { // just display normal information
                    if (board.getBoard()[i][j] == 'X') {
                        System.out.print("[X]");
                    }
                    else if (board.getBoard()[i][j] == 'W') {
                        System.out.print("[W]");
                    }
                    else if (board.getBoard()[i][j] == 'H') {
                        System.out.print("[H]");
                    }
                    else if (board.getBoard()[i][j] == 'D') {
                        System.out.print("[D]");
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
            }
            System.out.println();
        }
        System.out.println();
    }
}
