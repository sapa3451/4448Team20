package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.LinearCoordinates;
import edu.colorado.team20.Board.Interfaces.BoardSubject;
import edu.colorado.team20.Board.Interfaces.CreateShipCoordinatesBehavior;
import edu.colorado.team20.Board.Interfaces.ShowBehavior;
import edu.colorado.team20.GamePiece.GamePiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board implements BoardSubject {
    private final char[][] board;
    private final int[][] idBoard;
    private final HashMap<Character, Integer> alphaMap = new HashMap<>();
    private final int rowSize = 10;
    private final int columnSize = 10;
    private final HashMap<Integer, String> gamePieceCoordinates = new HashMap<>();
    private final HashMap<Integer, String> gamePieceCapQPos = new HashMap<>();
    private final List<GamePiece> fleet;
    private ShowBehavior showBehavior;
    private CreateShipCoordinatesBehavior shipCoordinatesBehavior;
    private int zValue;


    public Board() {
        setCreateShipCoordinatesBehavior(new LinearCoordinates()); //set coordinate behavior to regular

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
        fleet = new ArrayList<>();
        zValue = 0;
    }

    public int getzValue() {
        return zValue;
    }

    public void setzValue(int zValue) {
        this.zValue = zValue;
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

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void performShow() {
        showBehavior.show(this); //strategy pattern, this calls to the interface and then to the specific class
    }

    public void setCreateShipCoordinatesBehavior (CreateShipCoordinatesBehavior coordBehavior) {
        shipCoordinatesBehavior = coordBehavior;
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public String performCreateShipCoordinates(int row, char col, int direction, int size) {
        return shipCoordinatesBehavior.createShipCoordinates(row, col, direction, size, this.rowSize, this.columnSize);
    }

    public List<GamePiece> getFleet () {
        return fleet;
    }

    public HashMap<Integer, String> getCoordinatesMapping() {
        return gamePieceCoordinates;
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void registerShip (GamePiece s) {
        fleet.add(s);
    } //use of observer strategy here, this adds ships to be updated after each hit

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void removeShip(int id) { //use of observer strategy here, this removes ships after they are sunk to longer recieve any updates
        for (GamePiece gamePiece : fleet) {
            if (id == gamePiece.getId()) {
                fleet.remove(gamePiece);
                break;
            }
        }
    }

    public HashMap<Character, Integer> getAlphaMap() {
        return this.alphaMap;
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public int updateGamePieceOnHit(int id) { //sending updates to ships when they get hit
        int health = -1;

        for (GamePiece gamePiece : fleet){
            if (id == gamePiece.getId()) {
                gamePiece.update(1);
                health = gamePiece.getTotShipHealth();
            }
        }
        return health;
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
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

    /**
     * Description:
     * Params:
     * Returns:
     */
    public boolean CheckSpot(char col, int row) {
        char position = board[row-1][alphaMap.get(col)];// subtract one from row because indexing of array
        return position != 'X' && position != 'D' && position != 'H';
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void MarkBoard(char col, int row){
        // call check spot in the beginning to check if spot is valid
        char positionChar = this.getBoard()[row-1][this.getAlphaMap().get(col)];

        if (positionChar == 'E') { // if shot decision was empty --> mark as X
            this.getBoard()[row-1][this.getAlphaMap().get(col)] = 'X'; // subtract one from row because indexing of array
        }
        else if (positionChar == 'Q') {
            this.getBoard()[row-1][this.getAlphaMap().get(col)] = 'W'; // if gets hit more than once keep at W
        }
        else if (positionChar == 'H') {
            this.getBoard()[row-1][this.getAlphaMap().get(col)] = 'H'; // if gets hit more than once keep at W
        }
        else if (positionChar == 'X') {
            this.getBoard()[row-1][this.getAlphaMap().get(col)] = 'X'; // if gets hit more than once keep at W
        }
        else if (positionChar == 'S') {
            this.getBoard()[row-1][this.getAlphaMap().get(col)] = 'H'; // if gets hit more than once keep at W
        }
        else { // decision was a ship --> mark as D
            this.getBoard()[row-1][this.getAlphaMap().get(col)] = 'D'; // subtract one from row because indexing of array
        }
        int id = this.getIdBoard()[row-1][this.getAlphaMap().get(col)];
        if (id != 0 && positionChar == 'Q' || positionChar == 'W') { // captainsQ got hit
            if (this.updateGamePieceOnCQHit(id) == 0) { // need to check if captainsQ is 0 health
                // update the this.to sink whole ship
                this.updateGamePieceChars(this.getGamePieceCoordinates(id));
                this.removeShip(id); //removes a ship as an observer when sunk
            }
        }
        else if (id != 0){
            if (this.updateGamePieceOnHit(id) == 0) {
                this.updateGamePieceChars(this.getGamePieceCoordinates(id));
                this.removeShip(id); //removes a ship as an observer when sunk
            }
        }
        this.performShow();
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public boolean SetGamePiecePos(int id, int row, char col, int direction, int size, int quartersPos) {
        char positionChar = board[row-1][alphaMap.get(col)];

        if (positionChar != 'E') { //Checks if ship is already at that location
            return false;
        }
        else {
            // get coordinates for shape of ship
            // IMPORTANT: coordinates already takes care of zero indexing
            String coordinates = this.performCreateShipCoordinates(row, col, direction, size);
            String captainsQ = "";
            if (coordinates.equals("NULL")) { return false; } // ship doesn't fit on board for col/row

            if (coordinates.contains("-")) {
                String[] positions = coordinates.split("-");
                coordinates = positions[0];
                captainsQ = positions[1];
            }

            char coordCol;
            int coordRow;

            // check if any ships are already placed there
            for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3,"
                coordCol = coordinates.charAt(i);
                coordRow = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
                if(board[coordRow][alphaMap.get(coordCol)] != 'E') {
                    return false;
                }
            }

            gamePieceCoordinates.put(id, coordinates); // add current ship coordinates to map with id

            // update the board with ship
            for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3"
                coordCol = coordinates.charAt(i);
                coordRow = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
                this.board[coordRow][alphaMap.get(coordCol)] = 'S';
                this.idBoard[coordRow][alphaMap.get(coordCol)] = id;
            }

            // place the caprain's quarters on board and place coordinate in shipCapQ mapping
            if (!captainsQ.isEmpty()) {
                char Qcol = captainsQ.charAt(0);
                int Qrow = Integer.parseInt(String.valueOf(captainsQ.charAt(1)));
                board[Qrow][alphaMap.get(Qcol)] = 'Q'; // mark captain's Q on board
                gamePieceCapQPos.put(id, Qcol + String.valueOf(Qrow)); // add captain's quarter's to map
            }
            else {
                int QinCoordinates = 3 * (quartersPos-1); // multiply by 3 and subtract 1 for coordinate of captainsQ0
                char Qcol = coordinates.charAt(QinCoordinates); // get char for col
                int Qrow = Integer.parseInt(String.valueOf(coordinates.charAt(QinCoordinates+1))); // add one to get row value
                board[Qrow][alphaMap.get(Qcol)] = 'Q'; // mark captain's Q on board
                gamePieceCapQPos.put(id, Qcol + String.valueOf(Qrow)); // add captain's quarter's to map
            }

        }
        return true;
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void updateGamePieceChars(String coordinates) {
        // IMPORTANT: string row value already has zero index (do not minus 1 from row)!

        // parse the coordinates in the String
        char col;
        int row;
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
