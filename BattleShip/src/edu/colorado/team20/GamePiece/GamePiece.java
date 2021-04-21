package edu.colorado.team20.GamePiece;

import edu.colorado.team20.GamePiece.Interfaces.GamePieceObserver;

/**
 * Description: abstract class that defines methods that game pieces should have
 * as well as implementing the Observer pattern interface methods that are required
 */
public abstract class GamePiece implements GamePieceObserver {
    protected int numOccupiedBoardCells;
    protected int totShipHealth;
    protected int captainQHealth;
    protected String name;
    protected int id;
    protected boolean sunk;
    protected boolean goesUnderwater;
    protected boolean goesInAir;
    protected int quartersSpot; // int to tell captainsQ spacing for boat size


    public GamePiece() {
        this.sunk = false;
    }

    public void setId(int ID) { this.id = ID; }

    public boolean canbeUnderwater() {
        return goesUnderwater;
    }

    public boolean canbeInAir() {
        return goesInAir;
    }

    public int getId() { return this.id; }

    public int getSize () {
        return this.numOccupiedBoardCells;
    }

    public String getName () {
        return this.name;
    }

    public int getQuartersSpotInt() { return this.quartersSpot; }

    public int getTotShipHealth() { return this.totShipHealth; }

    /**
     * Description: method that updates the current game piece's health using observer pattern
     * Params: damage value that is taken from the game piece's health
     * Returns: boolean value to show if game piece still has health or is destroyed
     */
    public boolean update(int damage) { //Use of the observer strategy here, function in ShipObservers
        this.totShipHealth = this.totShipHealth - damage;
        if (this.totShipHealth == 0) {
            this.sunk = true;
            return true;
        } // ship destroyed
        return false; // ship still has life
    }

    public int getCaptainQHealth() { return this.captainQHealth; }

    /**
     * Description: method that updates the current game piece's captain's Q health using the obeserver pattern
     * Params: damage value that is subtracted from the game piece's health
     * Returns: boolean value to show if game piece still has health or is destoryed
     */
    public boolean updateCQ(int damage) { //Use of the observer strategy here, function in ShipObservers
        this.captainQHealth = this.captainQHealth - damage;
        this.totShipHealth = this.totShipHealth - damage;
        if (this.captainQHealth == 0) {
            // set tot health to 0
            this.update(this.totShipHealth);
            this.sunk = true;
            return true; // return true --> ship is destroyed
        }
        return false; // return false --> ship still has health
    }

    public boolean checkSunk() {
        return sunk;
    }
}

