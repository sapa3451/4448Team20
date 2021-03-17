package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShipCoordinates;
import edu.colorado.team20.Board.Interfaces.Behaviors.SurfaceMark;
import edu.colorado.team20.Board.Interfaces.BoardSubject;
import edu.colorado.team20.Board.Interfaces.CreateShipCoordinatesBehavior;
import edu.colorado.team20.Board.Interfaces.MarkBehavior;
import edu.colorado.team20.Board.Interfaces.ShowBehavior;
import edu.colorado.team20.Ship.Ship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Board implements BoardSubject {
    protected final char[][] board;
    protected int[][] idBoard;
    protected final HashMap<Character, Integer> alphaMap = new HashMap<>();
    protected final int rowSize = 10;
    protected final int columnSize = 10;
    protected HashMap<Integer, String> shipCoordinates = new HashMap<>();
    protected HashMap<Integer, String> shipCapQPos = new HashMap<>();
    protected List<Ship> fleet;
    ShowBehavior showBehavior;
    CreateShipCoordinatesBehavior shipCoordinatesBehavior;
    MarkBehavior markBehavior;


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
        fleet = new ArrayList<Ship>();
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

    public List<Ship> getFleet () {
        return fleet;
    }

    public HashMap<Integer, String> getCoordinatesMapping(int id) {
        return shipCoordinates;
    }

    public void registerShip (Ship s) {
        fleet.add(s);
    } //use of observer strategy here, this adds ships to be updated after each hit

    public void removeShip(int id) { //use of observer strategy here, this removes ships after they are sunk to longer recieve any updates
        for (Ship ship : fleet) {
            if (id == ship.getId()) {
                fleet.remove(ship);
                break;
            }
        }
        return;
    }

    public HashMap<Character, Integer> getAlphaMap() {
        return this.alphaMap;
    }

    public int updateShipOnHit(int id) { //sending updates to ships when they get hit
        int health = -1;

        for (Ship ship : fleet){
            if (id == ship.getId()) {
                ship.update(1);
            }
        }
        return health;
    }

    public int updateShipOnCQHit(int id) { //sending updates to ships when they get hit in the CQ
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

    public void performMarkBoard(char col, int row){
        markBehavior.MarkBoard(this, col, row);
    }

    public boolean SetShipPos(int id, int row, char col, int direction, int size, int quartersPos) {
        // TODO: need to figure out how we are going to place submarine since not one row/col
        char positionChar = board[row-1][alphaMap.get(col)];

        if (positionChar != 'E') { //Checks if ship is already at that location
            System.out.println("Ship already placed here!");
            return false;
        }
        else {
            // get coordinates for shape of ship
            // IMPORTANT: coordinates already takes care of zero indexing
            String coordinates = this.performCreateShipCoordinates(row, col, direction, size);
            String captainsQ = "";
            // TODO: remove after testing done
            System.out.println("Coordinates: " + coordinates);
            if (coordinates == "NULL") { return false; } // ship doesn't fit on board for col/row

            if (coordinates.contains("-")) {
                String[] positions = coordinates.split("-");
                coordinates = positions[0];
                captainsQ = positions[1];
            }

            char coordCol = ' ';
            int coordRow = -1;

            // check if any ships are already placed there
            for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3,"
                coordCol = coordinates.charAt(i);
                coordRow = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
                if(board[coordRow][alphaMap.get(coordCol)] != 'E') {
                    System.out.println("Ship already placed here!");
                    return false;
                }
            }

            shipCoordinates.put(id, coordinates); // add current ship coordinates to map with id

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
                shipCapQPos.put(id, Qcol + String.valueOf(Qrow)); // add captain's quarter's to map
            }
            else {
                //TODO: can we think of a better way to handle regular ships captainsQ
                int QinCoordinates = 3 * (quartersPos-1); // multiply by 3 and subtract 1 for coordinate of captainsQ0
                char Qcol = coordinates.charAt(QinCoordinates); // get char for col
                int Qrow = Integer.parseInt(String.valueOf(coordinates.charAt(QinCoordinates+1))); // add one to get row value
                board[Qrow][alphaMap.get(Qcol)] = 'Q'; // mark captain's Q on board
                shipCapQPos.put(id, Qcol + String.valueOf(Qrow)); // add captain's quarter's to map
            }

        }
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
    public void updateShipChars(String coordinates) {
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

    public String getShipCaptainQPos(int id) { return this.shipCapQPos.get(id); }
    public String getShipCoordinates(int id) { // return specific ship coordinates
        return this.shipCoordinates.get(id);
    }
}
