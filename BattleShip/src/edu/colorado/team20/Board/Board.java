package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.LinearCoordinates;
import edu.colorado.team20.Board.Interfaces.BoardSubject;
import edu.colorado.team20.Board.Interfaces.CreateCoordinatesBehavior;
import edu.colorado.team20.Board.Interfaces.ShowBehavior;
import edu.colorado.team20.GamePiece.GamePiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Description: The Board class is where the majority of the responsibilities dealing with each board in the game happens. A board's size can be changed,
 * but is set at a 10x10. The Board class communicates with GamePiece upon hits, handles calls to show the board, as well as the main functions of
 * marking each board upon a hit whether it be a miss or hit and placing pieces at the start of the game. Each board also has a z value that indicates whether it
 * is above the sea, below the sea, or on the sea surface.
 */
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
    private CreateCoordinatesBehavior coordinatesBehavior;
    private int zValue;


    public Board() {
        setCreateCoordinatesBehavior(new LinearCoordinates()); //set coordinate behavior to regular

        this.board = new char[this.rowSize][this.columnSize];
        this.idBoard = new int[this.rowSize][this.columnSize];
        char[] alphas = new char[this.rowSize];

        // creating empty board and idBoard
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.columnSize; j++) {
                this.board[i][j] = 'E';
                this.idBoard[i][j] = 0; // 0 means no piece id is there (ids go from 1,2,...)
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
     * Description: This calls to the specific show behavior that is specified for a board. The show function makes use of the
     *              board array that each board has, that is marked with characters for their status
     * Params: none
     * Returns: none
     */
    public void performShow() {
        showBehavior.show(this); //strategy pattern, this calls to the interface and then to the specific class
    }

    public void setCreateCoordinatesBehavior(CreateCoordinatesBehavior coordBehavior) {
        coordinatesBehavior = coordBehavior;
    }

    /**
     * Description: This calls to the specific coordinates behavior that is specified for a board and the piece that is placed
     * Params: row where player wants piece, column where player wants piece, direction the player wants the piece, size of the piece
     * Returns: A string that contains the coordinates of the piece, with a ',' as the deliminator
     */
    public String performCreateCoordinates(int row, char col, int direction, int size) {
        return coordinatesBehavior.createShipCoordinates(row, col, direction, size, this.rowSize, this.columnSize);
    }

    public List<GamePiece> getFleet () {
        return fleet;
    }

    public HashMap<Integer, String> getCoordinatesMapping() {
        return gamePieceCoordinates;
    }

    /**
     * Description: This registers a piece into the list of pieces that need to be updated, using the observer pattern
     * Params: a game piece to be added
     * Returns: none
     */
    public void registerShip (GamePiece s) {
        fleet.add(s);
    } //use of observer strategy here, this adds pieces to be updated after each hit

    /**
     * Description: This removes a piece from the list of pieces that need to be updated once a  piece is destroyed ,as it no longer needs updates
     * Params: id of the destroyed  piece
     * Returns: none
     */
    public void removeShip(int id) { //use of observer strategy here, this removes  pieces after they are sunk to longer recieve any updates
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
     * Description: This updates a piece on a normal hit, with a damage of 1, and calls the specific game piece to then update their health
     * Params: a  pieces id to be updated
     * Returns: the new health of the  piece
     */
    public int updateGamePieceOnHit(int id) { //sending updates to pieces when they get hit
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
     * Description: This updates a piece on a captain's quarters hit, with a damage of 1, and calls the specific game piece to then update their health
     *              and captains quarters health
     * Params: a pieces id to be updated
     * Returns: the new health of the piece
     */
    public int updateGamePieceOnCQHit(int id) { //sending updates to pieces when they get hit in the CQ
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
     * Description: This will check and see if a spot has already been marked, if yes, true, if no ,false
     * Params: column and row to be checked
     * Returns: true or false
     */
    public boolean CheckSpot(char col, int row) {
        char position = board[row-1][alphaMap.get(col)];// subtract one from row because indexing of array
        return position != 'X' && position != 'D' && position != 'H';
    }

    /**
     * Description: This function is where the marking of the board takes place upon each shot fired. If handles all hits, misses, and CQ hits.
     *              This uses the board array that is specified for the board to add the markings to that array.
     *              E = Empty
     *              H = Hit
     *              X = Miss
     *              W = Weak CQ
     *              D = Destroyed
     * Params: the column and row to be marked
     * Returns: none
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
        else { // decision was a piece --> mark as D
            this.getBoard()[row-1][this.getAlphaMap().get(col)] = 'D'; // subtract one from row because indexing of array
        }
        int id = this.getIdBoard()[row-1][this.getAlphaMap().get(col)];
        if (id != 0 && positionChar == 'Q' || positionChar == 'W') { // captainsQ got hit
            if (this.updateGamePieceOnCQHit(id) == 0) { // need to check if captainsQ is 0 health
                // update the this.to sink whole piece
                this.updateGamePieceChars(this.getGamePieceCoordinates(id));
                this.removeShip(id); //removes a piece as an observer when sunk
            }
        }
        else if (id != 0){
            if (this.updateGamePieceOnHit(id) == 0) {
                this.updateGamePieceChars(this.getGamePieceCoordinates(id));
                this.removeShip(id); //removes a piece as an observer when sunk
            }
        }
    }

    /**
     * Description: This function calls the the specified coordinate behavior that each board gets for each piece. Once coordinates are created,
     *              It will make sure that there is not a piece placed there and that it can fit on the given board. If those pass, we then add the piece
     *              to our gamePieceCoordinates structure, that has the id of each piece as well as marks the piece on the board array for each given
     *              board. It also calculates the captains quarters, and marks it with a Q. If placement was successful, it returns true, if not, false.
     * Params: the pieces id to be placed, the row and column where it is to be placed, the direction it is to be placed, the size of the piece,
     *         and the position of the captains quarters relative to its size.
     * Returns: true or false
     */
    public boolean SetGamePiecePos(int id, int row, char col, int direction, int size, int quartersPos) {
        char positionChar = board[row-1][alphaMap.get(col)];

        if (positionChar != 'E') { //Checks if piece is already at that location
            return false;
        }
        else {
            // get coordinates for shape of piece
            // IMPORTANT: coordinates already takes care of zero indexing
            String coordinates = this.performCreateCoordinates(row, col, direction, size);
            String captainsQ = "";
            if (coordinates.equals("NULL")) { return false; } // piece doesn't fit on board for col/row

            if (coordinates.contains("-")) {
                String[] positions = coordinates.split("-");
                coordinates = positions[0];
                captainsQ = positions[1];
            }

            char coordCol;
            int coordRow;

            // check if any pieces are already placed there
            for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3,"
                coordCol = coordinates.charAt(i);
                coordRow = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
                if(board[coordRow][alphaMap.get(coordCol)] != 'E') {
                    return false;
                }
            }

            gamePieceCoordinates.put(id, coordinates); // add current piece coordinates to map with id

            // update the board with piece
            for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3"
                coordCol = coordinates.charAt(i);
                coordRow = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
                this.board[coordRow][alphaMap.get(coordCol)] = 'S';
                this.idBoard[coordRow][alphaMap.get(coordCol)] = id;
            }

            // place the caprain's quarters on board and place coordinate in pieceCapQ mapping
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
     * Description: This function will update the board when a piece is completely destroyed by a hit, marking the board array with D where the piece.
     *              This would happen, for instance, when a captains quarters was hit twice.
     * Params: a string of coordinates where the piece was
     * Returns: none
     */

    //Takes in a Coord and returns the ID at that position
    public int getIDatCoord(char col,int row){

        int numCol=alphaMap.get(col);
        return this.idBoard[row-1][numCol];
    }

    // function to mark ship as all destroyed when captains' quarters destroyed

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
    public String getGamePieceCoordinates(int id) { // return specific piece coordinates
        return this.gamePieceCoordinates.get(id);
    }
}
