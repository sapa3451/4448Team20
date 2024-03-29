package edu.colorado.team20.GamePiece;

/**
 * Description: class that extends GamePiece to define Submarine game piece with
 * specified attributes and constructor
 */
public class Submarine extends GamePiece {

    public Submarine() {
        super();
        this.numOccupiedBoardCells=5;
        this.totShipHealth = this.numOccupiedBoardCells+1;
        this.name="submarine";
        this.captainQHealth = 2; //Submarine has 2 health for CQ
        this.quartersSpot = 5;
        this.goesUnderwater = true;
        this.goesInAir = false;
    }
}
