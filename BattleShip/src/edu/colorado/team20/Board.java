package edu.colorado.team20;

import edu.colorado.team20.Ship;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Board implements BoardSubject{
    protected final char[][] board;
    protected int[][] idBoard;
    protected final HashMap<Character, Integer> alphaMap = new HashMap<>();
    protected final int rowSize = 10;
    protected final int columnSize = 10;
    protected HashMap<Integer, String> startPos = new HashMap<>();
    protected HashMap<Integer, String> shipCapQPos = new HashMap<>();
    protected List<Ship> fleet;
    ShowBehavior showBehavior;

    public Board() {
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
        fleet = new ArrayList<Ship>();
    }

    public char[][] getBoard() {
        return this.board;
    }

    public void setShowBehavior (ShowBehavior sb) {
        showBehavior = sb;
    }

    public void performShow() {
        showBehavior.show(this);
    }

    public void registerShip (Ship s) {
        fleet.add(s);
    }

    public void removeShip(Ship s) {
        fleet.remove(s);
    }

    public int updateShipOnHit(int id) {
        int health = -1;

        for (Ship ship : fleet){
            if (id == ship.getId()) {
                ship.update(1);
            }
        }
        return health;
    }

    public int updateShipOnCQHit(int id) {
        int health = -1;

        for (Ship ship : fleet) {
            if (id == ship.getId()) {
                ship.updateCQ(1);
                health = ship.getTotShipHealth();
            }
        }
        return health;
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
    // show the opponent the player's board
    // shows opponent what spots they hit and destroyed ships

    // function to check if spot was already shot at
    public boolean CheckSpot(char col, int row) {
        char position = board[row-1][alphaMap.get(col)];// subtract one from row because indexing of array
        return position != 'X' && position != 'D';
    }

    // function to mark board after a shot
    // returns id of ship if it has been hit (-1 if no ship hit)
    public void MarkBoard(char col, int row) {
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
        int id = this.idBoard[row-1][alphaMap.get(col)];
        if (id != 0 && positionChar == 'Q' || positionChar == 'W') { // captainsQ got hit
            if (updateShipOnCQHit(id) == 0) { // need to check if captainsQ is 0 health
                // update the board to sink whole ship
                String s = startPos.get(id);
                int y = 0;
                if (s.length() == 4) {
                    y = 9;
                }
                else {
                    y = Integer.parseInt(String.valueOf(s.charAt(1)));
                }
                updateShipChars(s.charAt(0), y, fleet.get(id - 1).getSize(), Integer.parseInt(String.valueOf(s.charAt(2))));
            }
        }
        else if (id != 0){
            if (updateShipOnHit(id) == 0) {
                String s = startPos.get(id);
                int y = 0;
                if (s.length() == 4) {
                    y = 9;
                }
                else {
                    y = Integer.parseInt(String.valueOf(s.charAt(1)));
                }
                updateShipChars(s.charAt(0), y, fleet.get(id - 1).getSize(), Integer.parseInt(String.valueOf(s.charAt(2))));
            }
        }
        this.performShow();
    }

    public boolean SetShipPos(int id, int row, char col, int direction, int size) {
        char positionChar = board[row-1][alphaMap.get(col)];
        int quarters = size/2;
        if (size == 2) {
            quarters = 0;
        }
        int rowC = row;
        char colC = col;
        if (positionChar != 'E') {
            System.out.println("Ship already placed here!");
            return false;
        }//Checks if ship is already at that location
        else {
            if (direction == 1) { // horizontal

                char indexCol=col;
                for (int i = 0; i < size; i++) {
                    if(board[row - 1][alphaMap.get(indexCol)] != 'E'){
                        System.out.println("Ship already placed here!");
                        return false;
                    }
                    indexCol += 1;
                }//This checks all the values where ship would be placed and
                //makes sure no ships are already placed there in advanced

                startPos.put(id, col+String.valueOf(row-1)+1); // add start position to map
                for (int i = 0; i < size; i++) {
                    board[row - 1][alphaMap.get(col)] = 'S';
                    idBoard[row - 1][alphaMap.get(col)] = id;
                    col += 1;
                }
                char o = (char) (colC + quarters);
                board[rowC - 1][alphaMap.get(o)] = 'Q';
                shipCapQPos.put(id, o+String.valueOf(rowC - 1)); // add captain's quarter's to map
            }
            else { // vertical

                for (int i = row; i < size+row; i++) {
                    if(board[i - 1][alphaMap.get(col)] != 'E'){
                        System.out.println("Ship already placed here!");
                        return false;
                    }
                }//This checks all the values where ship would be placed and
                //makes sure no ships are already placed there in advanced

                startPos.put(id, col+String.valueOf(row-1)+0); // add start position to map
                for (int i = 0; i < size; i++) {
                    board[row - 1][alphaMap.get(col)] = 'S';
                    idBoard[row - 1][alphaMap.get(col)] = id;
                    row += 1;
                }
                board[rowC - 1 + quarters][alphaMap.get(colC)] = 'Q';
                shipCapQPos.put(id, colC+String.valueOf(rowC + quarters - 1)); // add captain's quarter's to map
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
}
