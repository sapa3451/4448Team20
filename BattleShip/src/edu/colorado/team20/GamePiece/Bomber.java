package edu.colorado.team20.GamePiece;

/**
 * Description: class that extends GamePiece to define Bomber game piece with
 * specified attributes and constructor
 */
public class Bomber extends GamePiece {

    public Bomber() {
        super();
        this.numOccupiedBoardCells=5;
        this.totShipHealth = 2; // bomber only has 2 life health
        this.name="bomber";
        this.captainQHealth = 1;
        this.quartersSpot = 0;
        this.goesUnderwater = false;
        this.goesInAir = true;
    }
}
