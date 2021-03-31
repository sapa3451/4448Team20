package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShipCoordinates;
import edu.colorado.team20.Board.Interfaces.BoardSubject;
import edu.colorado.team20.Board.Interfaces.CreateShipCoordinatesBehavior;
import edu.colorado.team20.Board.Interfaces.MarkBehavior;
import edu.colorado.team20.Board.Interfaces.ShowBehavior;
import edu.colorado.team20.GamePiece.GamePiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Board implements BoardSubject {
    protected final char[][] board;
    protected int[][] idBoard;
    protected final HashMap<Character, Integer> alphaMap = new HashMap<>();
    protected final int rowSize = 10;
    protected final int columnSize = 10;
    protected HashMap<Integer, String> gamePieceCoordinates = new HashMap<>();
    protected HashMap<Integer, String> gamePieceCapQPos = new HashMap<>();
    protected List<GamePiece> fleet;
    ShowBehavior showBehavior;
    CreateShipCoordinatesBehavior shipCoordinatesBehavior;
    MarkBehavior markBehavior;
    protected int id;


    public Board() {
        setCreateShipCoordinatesBehavior(new RegularShipCoordinates()); //set coordinate behavior to regular

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
        fleet = new ArrayList<GamePiece>();
    }

    public char[][] getBoard() {
        return this.board;
    }

    public int[][] getIdBoard() {
        return this.idBoard;
    }

    public void setShowBehavior (ShowBehavior sb) {
        showBehavior = sb;
    }

    public void performShow() {
        showBehavior.show(this); //strategy pattern, this calls to the interface and then to the specific class
    }

    public void setCreateShipCoordinatesBehavior (CreateShipCoordinatesBehavior coordBehavior) {
        shipCoordinatesBehavior = coordBehavior;
    }

    public String performCreateShipCoordinates(int row, char col, int direction, int size) {
        return shipCoordinatesBehavior.createShipCoordinates(row, col, direction, size, this.rowSize, this.columnSize);
    }

    public List<GamePiece> getFleet () {
        return fleet;
    }

    public HashMap<Integer, String> getCoordinatesMapping(int id) {
        return gamePieceCoordinates;
    }

    public void registerShip (GamePiece s) {
        fleet.add(s);
    } //use of observer strategy here, this adds ships to be updated after each hit

    public void removeShip(int id) { //use of observer strategy here, this removes ships after they are sunk to longer recieve any updates
        for (GamePiece gamePiece : fleet) {
            if (id == gamePiece.getId()) {
                fleet.remove(gamePiece);
                break;
            }
        }
        return;
    }

    public HashMap<Character, Integer> getAlphaMap() {
        return this.alphaMap;
    }

    public int updateGamePieceOnHit(int id) { //sending updates to ships when they get hit
        int health = -1;

        for (GamePiece gamePiece : fleet){
            if (id == gamePiece.getId()) {
                gamePiece.update(1);
            }
        }
        return health;
    }

    public int updateGamePieceOnCQHit(int id) { //sending updates to ships when they get hit in the CQ
        int health = -1;

        for (GamePiece gamePiece : fleet) {
            if (id == gamePiece.getId()) {
                gamePiece.updateCQ(1);
                health = gamePiece.getTotShipHealth();
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

    public void performMarkBoard(char col, int row){
        markBehavior.MarkBoard(this, col, row);
    }

    public abstract boolean SetGamePiecePos(int id, int row, char col, int direction, int size, int quartersPos);

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
    public void updateGamePieceChars(String coordinates) {
        // IMPORTANT: string row value already has zero index (do not minus 1 from row)!

        // parse the coordinates in the String
        char col = ' ';
        int row = -1;
        for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3"
            col = coordinates.charAt(i);
            row = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
            this.board[row][alphaMap.get(col)] = 'D';
        }
    }

    public String getGamePieceCaptainQPos(int id) { return this.gamePieceCapQPos.get(id); }
    public String getGamePieceCoordinates(int id) { // return specific ship coordinates
        return this.gamePieceCoordinates.get(id);
    }
}
