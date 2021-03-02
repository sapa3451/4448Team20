package edu.colorado.team20;

import javafx.util.Pair;
import edu.colorado.team20.Ship;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public final class ComputerBoard extends Board {

    public ComputerBoard(Ship[] fleet) {
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

    // TODO: Need to create a function for sonar pulse
    // TODO: need to move this function to its own file as it will be a behavior for an interface
    public void ShowSonarPulse(char decisionCol, int decisionRow) {
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

        // need to check if sonar pulse is out of bounds for certain directions
        // adding available positions to a queue
        Queue<Pair<Character, Integer>> positions = new LinkedList<Pair<Character, Integer>>();

        // check if 2 up from midpoint is available
        if ((decisionRow-2)-1 > -1) {
            positions.add(new Pair(decisionCol, decisionRow-2));
        }
        // check one up and one left
        if (alphaMap.get(decisionCol)-1 > -1 && (decisionRow-1)-1 > -1) {
            positions.add(new Pair((char) (((int) decisionCol)-1), decisionRow-1));
        }
        // check one up
        if ((decisionRow-1)-1 > -1) {
            positions.add(new Pair(decisionCol, decisionRow-1));
        }
        // check one up and one right
        if (alphaMap.get(decisionCol)+1 < this.getColumnSize() && (decisionRow-1)-1 > -1) {
            positions.add(new Pair((char) (((int) decisionCol)+1), decisionRow-1));
        }
        // check 2 to the left
        if (alphaMap.get(decisionCol)-2 > -1) {
            positions.add(new Pair((char) (((int) decisionCol)-2), decisionRow));
        }
        // check 1 to the left
        if (alphaMap.get(decisionCol)-1 > -1) {
            positions.add(new Pair((char) (((int) decisionCol)-1), decisionRow));
        }
        // add the midpoint
        positions.add(new Pair(decisionCol, decisionRow));
        // check 1 to the right
        if (alphaMap.get(decisionCol)+1 < this.getColumnSize()) {
            positions.add(new Pair((char) (((int) decisionCol)+1), decisionRow));
        }
        // check 2 to the right
        if (alphaMap.get(decisionCol)+2 < this.getColumnSize()) {
            positions.add(new Pair((char) (((int) decisionCol)+2), decisionRow));
        }
        // check one down and one left
        if (alphaMap.get(decisionCol)-1 > -1 && (decisionRow+1)-1 < this.getRowSize()) {
            positions.add(new Pair((char) (((int) decisionCol)-1), decisionRow+1));
        }
        // check one down
        if ((decisionRow+1)-1 < this.getRowSize()) {
            positions.add(new Pair(decisionCol, decisionRow+1));
        }
        // check one down and one right
        if (alphaMap.get(decisionCol)+1 < this.getColumnSize() && (decisionRow+1)-1 < this.getRowSize()) {
            positions.add(new Pair((char) (((int) decisionCol)+1), decisionRow+1));
        }
        // check 2 down
        if ((decisionRow+2)-1 < this.getRowSize()) {
            positions.add(new Pair(decisionCol, decisionRow+2));
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
                if (!positions.isEmpty() && alphaMap.get(positions.peek().getKey()) == j && positions.peek().getValue() == i) { // check to see if area is part of the radar pulse
                    positions.remove(); // remove the position
                    if (this.board[i][j] == 'X') {
                        System.out.print("[X]");
                    }
                    else if (this.board[i][j] == 'S' || this.board[i][j] == 'Q') {
                        System.out.print("[S]");
                    }
                    else {
                        System.out.print("[*]");
                    }
                }
                else { // just display normal information
                    if (this.board[i][j] == 'X') {
                        System.out.print("[X]");
                    }
                    else {
                        System.out.print("[ ]");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
