package edu.colorado.team20.GamePiece;

/**
 * Description: class that extends GamePiece to define Destroyer game piece with
 * specified attributes and constructor
 */
public class Destroyer extends GamePiece {
    public Destroyer() {
        super();
        this.numOccupiedBoardCells=3;
        this.totShipHealth = this.numOccupiedBoardCells+1;
        this.name="destroyer";
        this.captainQHealth = 2; //Destroyer has 2 health for CQ
        this.quartersSpot = 2;
        this.goesUnderwater = false;
        this.goesInAir = false;
    }
}
